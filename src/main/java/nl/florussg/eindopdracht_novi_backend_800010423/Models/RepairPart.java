package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import javax.persistence.*;

//tussenklasse Repair en Part
@Entity
public class RepairPart {

    //attributes
   //nog te veranderen naar een embeddedId d.m.v. KEY Klasse?~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @EmbeddedId
    RepairPartKey id;

    @ManyToOne
    @MapsId("repairId")
    private Repair repair;

   @ManyToOne
   @MapsId("partId")
   private Part part;

    private int amount;

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
