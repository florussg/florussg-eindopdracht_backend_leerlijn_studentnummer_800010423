package nl.florussg.eindopdracht_novi_backend_800010423.Controllers;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.Repair;;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.RepairStatus;
import nl.florussg.eindopdracht_novi_backend_800010423.Services.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
        long newId = repairService.createRepairAndLinkItToTheAppointment(repair, id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();
    }

    //Possible parameters are:
    // "REPAIR_not_approved_by_customer", "REPAIR_started",
    // "REPAIR_pass", "REPAIR_fail", "REPAIR_cancelled"
    @PatchMapping(value = "/repair/{id}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> setRepairStatus (@PathVariable long id, @RequestBody Repair repairStatus) {
        repairService.setRepairStatus(id, repairStatus);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "repairs")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAllRepairsBasedOnRepairStatus(@RequestParam(name = "input", defaultValue = "") RepairStatus repairStatus) {
        return ResponseEntity.ok(repairService.findRepairByRepairStatus(repairStatus));
    }


//    @GetMapping(value = "repairs")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<Object> getAllRepairsBasedOnRepairStatus(@RequestParam(name = "input", defaultValue = "") RepairStatus repairStatus) {
//        return ResponseEntity.ok(repairService.findRepairByRepairStatus(repairStatus));
//    }

//    @GetMapping (value= "repairs")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public ResponseEntity<Object> getAllRepairs() {
//        return ResponseEntity.ok(repairService.getAllRepairs());
//    }





}
