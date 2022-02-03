package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Appointment {

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime dateTimeAssignment;

    private Boolean apkOrRepair; //if True then it's an APK appointment, if false it's an repair appointment

    @Enumerated(EnumType.STRING)
    private ApkStatus appointmentStatus;

    @ManyToOne
    private Car carAppointment;

    @OneToOne //CAN AN ASSIGNMENT HAVE ONE OR MORE REPAIRS LINKED? ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    Repair repairAppointmentOrApkNeedsRepairToPass;


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

    public Boolean getApkOrRepair() {
        return apkOrRepair;
    }

    public void setApkOrRepair(Boolean apkOrRepair) {
        this.apkOrRepair = apkOrRepair;
    }

    public ApkStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(ApkStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public Car getCarAppointment() {
        return carAppointment;
    }

    public void setCarAppointment(Car carAppointment) {
        this.carAppointment = carAppointment;
    }


    //methods
    //ADD method calculateTotalCostAppointment() here?
}
