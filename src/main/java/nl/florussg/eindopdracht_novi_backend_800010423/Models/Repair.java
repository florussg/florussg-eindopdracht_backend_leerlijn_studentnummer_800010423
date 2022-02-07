package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Repair {

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //Deze relatie nog werkend krijgen met de tussenklasse ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @OneToMany (mappedBy = "repair")
    private List<RepairPart> partToRepair;

    private String finding;

    @Enumerated(EnumType.STRING) // moet hier @Enumerated staan? ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private RepairStatus repairStatus;

    @ManyToOne
    private Appointment repairAppointment;



}



