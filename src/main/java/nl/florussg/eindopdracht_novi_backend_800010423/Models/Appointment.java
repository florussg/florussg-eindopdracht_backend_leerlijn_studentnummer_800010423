package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="appointment")
public class Appointment {

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name= "appointment_date_time") //Even though this is not clean code, the @Column gives you more control over the data.sql input
    private LocalDateTime dateTimeAppointment;

    @Column(name= "appointment_apk")
    private Boolean apk; //if true then the appointment contains an APK inspection

    @Column(name = "appointment_repair")
    private Boolean repair; //if true then the appointment contains an repair (or repair and APK) inspection

    private ApkStatus apkStatus;

    @ManyToOne
    private Car carAppointment;

    @OneToOne (mappedBy = "repairAppointment")
    @JsonIgnore
    private Repair appointmentRepair;

    @ManyToOne
    private Customer customerAppointment;

    //getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDateTimeAppointment() {
        return dateTimeAppointment;
    }

    public void setDateTimeAppointment(LocalDateTime dateTimeAppointment) {
        this.dateTimeAppointment = dateTimeAppointment;
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

    public Customer getCustomerAppointment() {
        return customerAppointment;
    }

    public void setCustomerAppointment(Customer appointmentOfCustomer) {
        this.customerAppointment = appointmentOfCustomer;
    }
}
