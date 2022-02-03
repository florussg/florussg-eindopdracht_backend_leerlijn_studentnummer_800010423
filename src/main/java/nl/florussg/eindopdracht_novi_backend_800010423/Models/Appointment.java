package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import javax.persistence.*;
import java.time.LocalDateTime;

public class Appointment {

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime dateTimeAssignment;

    private Boolean apk; //if True then it's an APK appointment, false it's an repair appointment

    @Enumerated(EnumType.STRING)
    private ApkStatus appointmentStatus;

    //getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDateTimeAssignment() {
        return dateTimeAssignment;
    }

    public void setDateTimeAssignment(LocalDateTime dateTimeAssignment) {
        this.dateTimeAssignment = dateTimeAssignment;
    }

    public Boolean getApk() {
        return apk;
    }

    public void setApk(Boolean apk) {
        this.apk = apk;
    }

    public ApkStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(ApkStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    //methods
    //ADD method calculatetotalcostapk() here?
}
