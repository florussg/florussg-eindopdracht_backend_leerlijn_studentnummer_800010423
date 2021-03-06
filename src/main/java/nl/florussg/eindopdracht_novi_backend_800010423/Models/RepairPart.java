package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import javax.persistence.*;

//Intermediate class Repair en Part
@Entity
public class RepairPart {

    //attributes
    @EmbeddedId
    RepairPartKey id;

    @ManyToOne
    @MapsId("repairId")
    private Repair repair;

    @ManyToOne
    @MapsId("partId")
    private Part part;

    private int amount;

    public RepairPartKey getId() {
        return id;
    }

    public void setId(RepairPartKey id) {
        this.id = id;
    }

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }

    public Part getPart() {
        return part;
   }

   public void setPart(Part part) {
        this.part = part;
   }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
   }
}
