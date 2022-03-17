package nl.florussg.eindopdracht_novi_backend_800010423.Controllers;

import nl.florussg.eindopdracht_novi_backend_800010423.Services.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PartController {

    @Autowired
    private PartService partservice;

    @GetMapping (value= "/parts")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAllParts() {
        return ResponseEntity.ok(partservice.getAllParts());
    }



}
