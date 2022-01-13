package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// NOG TE BEKIJKEN HOE DIT IN TE RICHTEN //
@Entity
public class AppointmentStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    enum Status {
        assignment_made,
        assignment_execute,
        APK_pass,
        APK_fail,
        assignment_ready,
        needs_repair;


    }










}
