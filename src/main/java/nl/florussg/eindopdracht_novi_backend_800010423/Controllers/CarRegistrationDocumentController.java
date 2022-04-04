package nl.florussg.eindopdracht_novi_backend_800010423.Controllers;

import nl.florussg.eindopdracht_novi_backend_800010423.Message.ResponseMessage;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.CarRegistrationDocument;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.UploadResponse;
import nl.florussg.eindopdracht_novi_backend_800010423.Services.CarRegistrationDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
public class CarRegistrationDocumentController {

    @Autowired
    private CarRegistrationDocumentService carRegistrationDocumentService;

//    @PostMapping (value = "/upload/car_registration_document")
//    public ResponseEntity<ResponseMessage> uploadCarRegistrationDocument(@RequestParam("file") MultipartFile dataFileName)  {
//
//        String message = "";
//
//        try {
//
//            carRegistrationDocumentService.uploadAndSaveScannedRegistrationDocument(dataFileName);
//
//            var fileName = carRegistrationDocumentService.getFileNameFromUploadedCarRegistrationDocument(
//                    dataFileName.getOriginalFilename()).getFileName();
//
//            message = "" + fileName + " has succesfully been uploaded.";
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//
//        } catch (Exception e) {
//
//            message = "could not upload the car registration document: " + dataFileName.getOriginalFilename() + ".";
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//        }
//    }
//
//    //TODO JOHAN Hoe kan ik nu de .png downloaden?
//    @GetMapping (value = "/download/car_registration_document/{id}")
//    public CarRegistrationDocument downloadCarRegistrationDocument(@PathVariable long id){
//        return carRegistrationDocumentService.downloadRegistrationDocument(id);
//    }

    @PostMapping (value = "upload/car_registration_document")
    UploadResponse singleUpload(@RequestParam("file") MultipartFile dataFileName) {

        String fileName = carRegistrationDocumentService.saveCarRegistrationDocument(dataFileName);

        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(fileName).toUriString();

        String contentType = dataFileName.getContentType();

        UploadResponse response = new UploadResponse(fileName, contentType, url);

        return response;
    }

    @GetMapping (value = "download/files_names")
    List<String> showAllCarRegistrationDocumentSavedToFileSystem (HttpServletRequest request) {
        return carRegistrationDocumentService.showAllDownloadableCarRegistrationDocuments();
    }

    @GetMapping (value = "download/{fileName}")
    ResponseEntity<Resource> downloadCarRegistrationDocument
            (@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = carRegistrationDocumentService.downloadCarRegistrationDocument(fileName);

        MediaType contentType = MediaType.APPLICATION_PDF;

        try {
            request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            contentType = MediaType.APPLICATION_PDF;
        }

        return ResponseEntity.ok().contentType(contentType).header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=" + resource.getFilename()).body(resource);

    }








    @PatchMapping (value = "/car_registration_document/car")
    public void setCarToUploadedRegistrationDocument (long carId) {

    }




}
