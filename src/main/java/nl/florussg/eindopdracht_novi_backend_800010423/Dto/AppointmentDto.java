package nl.florussg.eindopdracht_novi_backend_800010423.Dto;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.ApkStatus;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Car;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Customer;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Repair;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class AppointmentDto {

    private long id;

    private LocalDateTime dateTimeAppointment;

    private Boolean apk; //if true then the appointment contains an APK inspection

    private Boolean repair; //if true then the appointment contains a repair

    //private Boolean completed; if apk and repair False then the appointment is 'completed'. Create autosetter?~~~~~~~~

    private ApkStatus apkStatus;

    //@ManyToOne
    private Car carAppointment;

    //@OneToMany (mappedBy = "repairAppointment")
    private List<Repair> repairs;

    //@ManyToOne
    private Customer appointmentOfCustomer;


}
