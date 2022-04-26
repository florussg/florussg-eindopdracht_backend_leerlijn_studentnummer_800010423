package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.RecordNotFoundException;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Car;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.CarRegistrationDocument;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CarRegistrationDocumentRepository;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarRegistrationDocumentService {

    @Autowired
    private CarRegistrationDocumentRepository carRegistrationDocumentRepository;

    @Autowired
    private CarRepository carRepository;

    //It is mandatory to change the value-path to your own system preferences in the application.properties
    @Value("${my.upload_location}")
    private Path storagePath;
    private String storageLocation;

    public CarRegistrationDocumentService(
            @Value("${my.upload_location}") String storageLocation) {
        storagePath = Paths.get(storageLocation).toAbsolutePath().normalize();

        this.storageLocation = storageLocation;

        try {
            Files.createDirectories(storagePath);
        } catch (IOException e) {
            throw new RuntimeException("Error in creating file directory");
        }
    }

    //File to save/upload has to be a PDF document!
    public String saveCarRegistrationDocument (MultipartFile dataFileName) {
        String fileName = StringUtils.cleanPath(dataFileName.getOriginalFilename());

        Path filePath = Paths.get(storagePath + "\\" + fileName);

        if (!dataFileName.getContentType().equals(String.valueOf(MediaType.APPLICATION_PDF))) {
            throw new RuntimeException("Wrong file type, only PDF allowed!");
        } else {

            try {
                Files.copy(dataFileName.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                saveUploadedCarRegistrationDocumentToDatabase(
                        fileName, String.valueOf(filePath));

            } catch (IOException e) {
                throw new RuntimeException("Error in saving the file");
            }

            return fileName;
        }
    }

    public List<String> showAllDownloadableCarRegistrationDocuments() {
        var list = new ArrayList<String>();
        File folder = new File(storageLocation);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String name = listOfFiles[i].getName();
                list.add(name);
            }
        }
        return list;
    }

    public Resource downloadCarRegistrationDocument (String fileName) {
        Path path = Paths.get(storageLocation).toAbsolutePath().resolve(fileName);

        Resource resource;

        try {

            resource = new UrlResource(path.toUri());

        } catch (MalformedURLException e) {
            throw new RuntimeException("Error in reading the Car Registration Document", e);
        }

         if (resource.exists() && resource.isReadable()) {
             return resource;
         } else {
             throw new RuntimeException("The Car Registration Document does not exist");
         }
    }

    public void saveUploadedCarRegistrationDocumentToDatabase (String fileName, String pathSaved) {
        CarRegistrationDocument saveToDatabase = new CarRegistrationDocument();
        saveToDatabase.setFileName(fileName);
        saveToDatabase.setPathSaved(pathSaved);

        Optional<CarRegistrationDocument> exists =  Optional.ofNullable(
                carRegistrationDocumentRepository.findByFileNameEquals(fileName));

        if (exists.isPresent()) {
            throw new RuntimeException("There is already a file with the same name!");
        } else {
            carRegistrationDocumentRepository.save(saveToDatabase);
        }
    }

    public long addCarToUploadedRegistrationDocument (String fileName, String licenseplateNumber) {
        Optional<CarRegistrationDocument> optionalCarRegistrationDocument =
                Optional.ofNullable(carRegistrationDocumentRepository.findByFileNameEquals(fileName));

        Optional<Car> optionalCar =
                carRepository.findCarByLicenseplateNumberContainingIgnoreCase(licenseplateNumber);

        if (!optionalCarRegistrationDocument.isPresent() && optionalCarRegistrationDocument.isEmpty()) {
            throw new RecordNotFoundException("No car registration document with this filename found!");
        }
        if (!optionalCar.isPresent() && optionalCar.isEmpty()) {
            throw new RecordNotFoundException("No car with this licenseplate number found!");
        }

        CarRegistrationDocument carRegistrationDocumentToEdit =
                optionalCarRegistrationDocument.get();
        Car carToAdd = optionalCar.get();

        carRegistrationDocumentToEdit.setCar(carToAdd);

        carRegistrationDocumentRepository.save(carRegistrationDocumentToEdit);
        return carToAdd.getId();
    }
}
