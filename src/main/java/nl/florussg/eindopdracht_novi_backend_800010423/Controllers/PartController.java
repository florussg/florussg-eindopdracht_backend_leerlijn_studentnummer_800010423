package nl.florussg.eindopdracht_novi_backend_800010423.Controllers;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.Part;
import nl.florussg.eindopdracht_novi_backend_800010423.Services.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class PartController {

    @Autowired
    private PartService partservice;

    @GetMapping (value= "/parts")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAllParts() {
        return ResponseEntity.ok(partservice.getAllParts());
    }

    @GetMapping (value= "parts/brandtypeyear")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAllPartsByBrandTypeYear(@RequestBody String brandTypeYear) {
        return ResponseEntity.ok(partservice.getAllPartsByBrandTypeYear(brandTypeYear));
    }

    @PostMapping (value= "parts/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> addNewPart (@RequestBody Part part) {
        long newId = partservice.addNewPart(part);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();
        return ResponseEntity.created(location).build();
    }




}
