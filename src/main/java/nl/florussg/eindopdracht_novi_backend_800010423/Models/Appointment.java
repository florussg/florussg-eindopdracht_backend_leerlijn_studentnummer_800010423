package nl.florussg.eindopdracht_novi_backend_800010423.Models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="appointment")
public class Appointment {

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name= "appointment_date_time")
    private LocalDateTime dateTimeAppointment;

    @Column(name= "appointment_apk")
    private Boolean apk; //if true then the appointment contains an APK inspection

    @Column(name = "appointment_repair")
    private Boolean repair; //if true then the appointment contains a repair

    //TODO If there is time left add a Boolean completed
    //private Boolean completed; if apk and repair False then the appointment is 'completed'. Create autosetter?~~~~~~~~

    //TODO veranderen enum net zoals in Repair
    @Enumerated(EnumType.STRING)
    private ApkStatus apkStatus;

    @ManyToOne
    private Car carAppointment;

    //TODO checken of het een OneToOne is of toch oneToMany
    @OneToOne (mappedBy = "repairAppointment")
    private Repair appointmentRepair;

    @ManyToOne //TODO appointmentOfCustomer veranderen naar CustomerAppointment ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private Customer appointmentOfCustomer;

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

    public Customer getAppointmentOfCustomer() {
        return appointmentOfCustomer;
    }

    public void setAppointmentOfCustomer(Customer appointmentOfCustomer) {
        this.appointmentOfCustomer = appointmentOfCustomer;
    }

    public Repair getAppointmentRepair() {
        return appointmentRepair;
    }

    public void setAppointmentRepair(Repair appointmentRepair) {
        this.appointmentRepair = appointmentRepair;
    }

    //methods

    //Add method calculateTotalCostAppointment() here?~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //price APK = 40,-
    //price part
    //price per hour to repair part = 25,-




}
