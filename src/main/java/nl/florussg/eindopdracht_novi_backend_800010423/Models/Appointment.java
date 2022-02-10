package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Appointment {

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime dateTimeAssignment;

    private Boolean apk; //if true then the appointment contains an APK inspection

    private Boolean repair; //if true then the appointment contains an repair

    @ManyToOne
    private Car carAppointment;

    @Enumerated(EnumType.STRING) // moet hier @Enumerated staan? ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private ApkStatus apkStatus;

    @OneToMany
    private List<Repair> repairs;


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

    public Boolean getRepair() {
        return repair;
    }

    public void setRepair(Boolean repair) {
        this.repair = repair;
    }

    public ApkStatus getApkStatus() {
        return apkStatus;
    }

    public void setApkStatus(ApkStatus apkStatus) {
        this.apkStatus = apkStatus;
    }

    public Car getCarAppointment() {
        return carAppointment;
    }

    public void setCarAppointment(Car carAppointment) {
        this.carAppointment = carAppointment;
    }

    public List<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<Repair> repairs) {
        this.repairs = repairs;
    }

//methods
    //Add method calculateTotalCostAppointment() here?~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //price APK = 40,-
    //price part
    //price per hour to repair part = 25,-
}
