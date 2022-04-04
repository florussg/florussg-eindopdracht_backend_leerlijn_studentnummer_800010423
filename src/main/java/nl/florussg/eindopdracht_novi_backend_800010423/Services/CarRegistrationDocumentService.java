package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.RecordNotFoundException;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.CarRegistrationDocument;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CarRegistrationDocumentRepository;
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

    @Value("${my.upload_location}")
    private Path storagePath;
    private String storageLocation;

    public CarRegistrationDocumentService(@Value("${my.upload_location}") String storageLocation) {
        storagePath = Paths.get(storageLocation).toAbsolutePath().normalize();

        this.storageLocation = storageLocation;

        try {
            Files.createDirectories(storagePath);
        } catch (IOException e) {
            throw new RuntimeException("Error in creating file directory");
        }
    }

    public String saveCarRegistrationDocument (MultipartFile dataFileName) {

        String fileName = StringUtils.cleanPath(dataFileName.getOriginalFilename());

        Path filePath = Paths.get(storagePath + "\\" + fileName);

        if (!dataFileName.getContentType().equals(String.valueOf(MediaType.APPLICATION_PDF))) {
            throw new RuntimeException("Wrong file type, only PDF allowed!");
        } else {

            try {
                Files.copy(dataFileName.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
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


//    public CarRegistrationDocument uploadAndSaveScannedRegistrationDocument (
//            MultipartFile dataFileName) throws IOException {
//        String fileName = StringUtils.cleanPath(dataFileName.getOriginalFilename());
//                                                //TODO veranderen?: (Objects.requireNonNull(dataFileName.getOriginalFilename()));
//
//        CarRegistrationDocument carRegistrationDocument = new CarRegistrationDocument(
//                fileName, dataFileName.getBytes());
//                                                                                      //TODO dataFileName.getContentType()?
//        return carRegistrationDocumentRepository.save(carRegistrationDocument);
//    }


//    public CarRegistrationDocument getFileNameFromUploadedCarRegistrationDocument (String fileName) {
//
//        var fileNameFound = carRegistrationDocumentRepository.findByFileNameEquals(fileName);
//
//        return fileNameFound;
//    }
//
//    public CarRegistrationDocument downloadRegistrationDocument (long registrationDocumentId) {
//        Optional<CarRegistrationDocument> optionalCarRegistrationDocument =
//                carRegistrationDocumentRepository.findById(registrationDocumentId);
//        if (optionalCarRegistrationDocument.isPresent()) {
//            var foundDocument = optionalCarRegistrationDocument.get();
//            return foundDocument;
//        } else {
//            throw new RecordNotFoundException("No document found with this id");
//        }
//    }

    //TODO FLORUS verder gaan / afmaken!
    public long setCarToUploadedRegistrationDocument (long carId) {
        return carId;
    }


}
