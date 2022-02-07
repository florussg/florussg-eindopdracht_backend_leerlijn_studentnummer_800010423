package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import javax.persistence.*;

//tussenklasse Repair en Part
@Entity
public class RepairPart {

    //attributes
    //nog te veranderen naar een embeddedId d.m.v. KEY Klasse?~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @MapsId
    private Repair repair;

    @ManyToOne
    @MapsId
    private Part part;

    private int amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
