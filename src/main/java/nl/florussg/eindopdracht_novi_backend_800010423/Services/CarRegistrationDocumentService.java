package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.CarRegistrationDocument;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CarRegistrationDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class CarRegistrationDocumentService {

    @Autowired
    private CarRegistrationDocumentRepository carRegistrationDocumentRepository;

    public CarRegistrationDocument uploadAndSaveScannedRegistrationDocument (
            MultipartFile dataFileName) throws IOException {
        String fileName = StringUtils.cleanPath(dataFileName.getOriginalFilename());
                                                //TODO veranderen?: (Objects.requireNonNull(dataFileName.getOriginalFilename()));

        CarRegistrationDocument carRegistrationDocument = new CarRegistrationDocument(fileName, dataFileName.getBytes());
                                                                                      //TODO dataFileName.getContentType()?
        return carRegistrationDocumentRepository.save(carRegistrationDocument);
    }


    public CarRegistrationDocument getFileNameFromUploadedCarRegistrationDocument (String fileName) {

        var fileNameFound = carRegistrationDocumentRepository.findByFileNameEquals(fileName);

        return fileNameFound;
    }


}
