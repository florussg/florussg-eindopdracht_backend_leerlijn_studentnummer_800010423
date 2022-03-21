package nl.florussg.eindopdracht_novi_backend_800010423.Controllers;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.Repair;
import nl.florussg.eindopdracht_novi_backend_800010423.Services.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class RepairController {

    @Autowired
    private RepairService repairService;

    @PostMapping(value = "/appointments/{id}/repair")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> addRepairToAppointment(@PathVariable long id, @RequestBody Repair repair) {
        long newId = repairService.addRepair(repair, id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();
    }



}
