package nl.florussg.eindopdracht_novi_backend_800010423.Dto;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.Part;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.RepairPart;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.RepairPartKey;

//TODO Als ik deze niet ga gebruiken, niet vergeten in zijn volledigheid te verwijderen
public class RepairPartDto {

    private long repairId;

    private long partId;

    private int amount;


    //getters
    public long getRepairId() {
        return repairId;
    }

    public long getPartId() {
        return partId;
    }

    public int getAmount() {
        return amount;
    }

}
