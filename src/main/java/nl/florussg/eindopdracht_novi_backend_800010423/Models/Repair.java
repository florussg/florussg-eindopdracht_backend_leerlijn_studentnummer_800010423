package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import javax.persistence.*;

@Entity
public class Repair {

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //this is a inherited from the Part class! Still have to make this relation
    private String partToRepair;

    private String finding;

    @OneToOne(mappedBy = "repairAppointmentOrApkNeedsRepairToPass")
    Appointment repair;

}



