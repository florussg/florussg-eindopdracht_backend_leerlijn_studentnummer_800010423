package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.RecordNotFoundException;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Part;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Repair;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.RepairPart;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.RepairPartKey;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.PartRepository;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.RepairPartRepository;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class RepairPartService {

    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private RepairPartRepository repairPartRepository;

    @Autowired
    private PartRepository partRepository;

    public RepairPartKey addPartToRepair(long repairId, long partId, int amountPart) {
        if (!repairRepository.findById(repairId).isPresent()) {
            throw new RecordNotFoundException("No repair with this id found");
        }

        if (!partRepository.findById(partId).isPresent()) {
            throw new RecordNotFoundException("No part with this id found");
        }

        var repairParts = new RepairPart();

        Repair repair = repairRepository.findById(repairId).orElse(null);

        Part partToAdd = partRepository.findById(partId).orElse(null);

        repairParts.setRepair(repair);
        repairParts.setPart(partToAdd);
        repairParts.setAmount(amountPart);

        RepairPartKey newId = new RepairPartKey(repairId, partId);
        repairParts.setId(newId);

        repairPartRepository.save(repairParts);
        return repairParts.getId();
    }


    public List<Part> getAllPartsToBeRepairedFromOneRepair (long repairId) {
        List<RepairPart> repairPartList = repairPartRepository.findAllByRepairId(repairId);

        if (repairPartList.size() > 0) {
            List<Part> parts = new ArrayList<>();
            for (RepairPart rp : repairPartList) {
                parts.add(rp.getPart());
            }

            return parts;
        } else {
            throw new RecordNotFoundException("No repair found");
            }
    }
}






