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

        CarRegistrationDocument carRegistrationDocument = new CarRegistrationDocument(fileName, dataFileName.getBytes());
        return carRegistrationDocumentRepository.save(carRegistrationDocument);
    }




}
