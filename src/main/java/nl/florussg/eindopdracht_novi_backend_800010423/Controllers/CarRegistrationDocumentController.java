package nl.florussg.eindopdracht_novi_backend_800010423.Controllers;

import nl.florussg.eindopdracht_novi_backend_800010423.Services.CarRegistrationDocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class CarRegistrationDocumentController {

    private CarRegistrationDocumentService carRegistrationDocumentService;

    @PostMapping (value = "/upload/carregistrationdocument")
    public ResponseEntity<Object> uploadCarRegistrationDocument(@RequestBody MultipartFile dataFileName)  {

        String message = "";

        try {

            carRegistrationDocumentService.uploadAndSaveScannedRegistrationDocument(dataFileName);

            var fileName = carRegistrationDocumentService.getFileNameFromUploadedCarRegistrationDocument(
                    dataFileName.getOriginalFilename()).getId();

            message = "" + fileName;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            )

        }

    }


}
