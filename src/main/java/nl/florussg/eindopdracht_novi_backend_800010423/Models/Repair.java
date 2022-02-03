package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Repair {

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //this is a inherited from the Part class! Still have to make this relation
    private String partToRepair;

    private String finding;

}



