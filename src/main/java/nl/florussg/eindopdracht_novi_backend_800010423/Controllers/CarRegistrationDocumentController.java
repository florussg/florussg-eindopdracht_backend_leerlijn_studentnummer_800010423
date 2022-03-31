package nl.florussg.eindopdracht_novi_backend_800010423.Controllers;

import nl.florussg.eindopdracht_novi_backend_800010423.Message.ResponseMessage;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.CarRegistrationDocument;
import nl.florussg.eindopdracht_novi_backend_800010423.Services.CarRegistrationDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class CarRegistrationDocumentController {

    @Autowired
    private CarRegistrationDocumentService carRegistrationDocumentService;

    @PostMapping (value = "/upload/car_registration_document")
    public ResponseEntity<ResponseMessage> uploadCarRegistrationDocument(@RequestParam("file") MultipartFile dataFileName)  {

        String message = "";

        try {

            carRegistrationDocumentService.uploadAndSaveScannedRegistrationDocument(dataFileName);

            var fileName = carRegistrationDocumentService.getFileNameFromUploadedCarRegistrationDocument(
                    dataFileName.getOriginalFilename()).getFileName();

            message = "" + fileName + " has succesfully been uploaded.";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

        } catch (Exception e) {

            message = "could not upload the car registration document: " + dataFileName.getOriginalFilename() + ".";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    //TODO JOHAN Hoe kan ik nu de .png downloaden?
    @GetMapping (value = "/download/car_registration_document/{id}")
    public CarRegistrationDocument downloadCarRegistrationDocument(@PathVariable long id){
        return carRegistrationDocumentService.downloadRegistrationDocument(id);
    }

    @PatchMapping (value = "/car_registration_document/car")
    public void setCarToUploadedRegistrationDocument (long carId) {

    }




}
