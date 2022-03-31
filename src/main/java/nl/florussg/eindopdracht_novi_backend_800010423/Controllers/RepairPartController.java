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

    //TODO waarom werkt deze query niet?
    @GetMapping("repair/parts/{repairId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAllPartsToBeRepairedFromOneRepair (
            @PathVariable("repairId") long repairId) {
        return ResponseEntity.ok(repairPartService.getAllPartsToBeRepairedFromOneRepair(repairId));
    }












    //    @PostMapping ("repair/parts/{repairId}/{partId}/{amount}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<Object> addPartToRepair (@PathVariable("repairId") long repairId,
//                                                   @PathVariable( "partId") long partId,
//                                                   @PathVariable int amount) {
//
//        RepairPartKey newId = repairPartService.addPartToRepair(repairId, partId, amount);
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(newId).toUri();
//
//        return ResponseEntity.created(location).build();
//    }




}
