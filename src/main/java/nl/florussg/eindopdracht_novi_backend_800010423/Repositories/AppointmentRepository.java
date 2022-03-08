package nl.florussg.eindopdracht_novi_backend_800010423.Repositories;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    //List<Appointment> findAppointmentByApkIsTrue();
    //Optional<Appointment> findAppointmentByRepairIsTrue();


//    Iterator<Appointment> findAppointmentByDate();
//
    Iterator<Appointment> findAppointmentByDateTimeAppointment(LocalDateTime localDateTime);
    //Iterator<Appointment> findAppointmentByDateTimeAppointment_Date(Appointment appointment);

    @Query(value= "select * from appointment where appointment_date_time::text like '%:stringDate%'", nativeQuery = true)
    List<Appointment> findAppointmentByDate(@Param("stringDate") String stringDate);

    //@Query(value = "SELECT * FROM books b WHERE b.title LIKE %:s%", nativeQuery = true) // using SQL

    @Query(value= "select * from appointment where appointment_apk is true", nativeQuery = true)
    List<Appointment> findAppointmentByApkIsTrue();

    @Query(value= "select * from appointment where appointment_repair is true", nativeQuery = true)
    List<Appointment> findAppointmentByRepairIsTrue();
}
