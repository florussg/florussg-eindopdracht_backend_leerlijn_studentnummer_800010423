package nl.florussg.eindopdracht_novi_backend_800010423.Controllers;

import nl.florussg.eindopdracht_novi_backend_800010423.Dto.RepairPartDto;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.RepairPartKey;
import nl.florussg.eindopdracht_novi_backend_800010423.Services.RepairPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class RepairPartController {

    @Autowired
    private RepairPartService repairPartService;

    @PostMapping("repair/parts")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> addPartToRepair (@RequestBody RepairPartDto repairPartDto) {

        RepairPartKey newId = repairPartService.addPartToRepair(
                repairPartDto.getRepairId(), repairPartDto.getPartId(),repairPartDto.getAmount());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.created(location).body(newId);
    }

    @GetMapping("repair/{repairId}/parts")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAllPartsToBeRepairedFromOneRepair (
            @PathVariable("repairId") long repairId) {
        return ResponseEntity.ok(repairPartService.getAllPartsToBeRepairedFromOneRepair(repairId));
    }

}
