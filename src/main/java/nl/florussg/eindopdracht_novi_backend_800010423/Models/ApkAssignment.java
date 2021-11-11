package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ApkAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    // BEKIJKEN HOE IK DE DATE GA INRICHTEN //
    private String dateassignment;

    // BEKIJKEN HOE IK DE DATE GA INRICHTEN //
    private String apkvalidtill;

    //Link maken met AssignmentStatus klasse//
    private String status;



}
