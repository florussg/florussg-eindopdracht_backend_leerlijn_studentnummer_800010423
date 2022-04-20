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

    Iterator<Appointment> findAppointmentByDateTimeAppointment(LocalDateTime localDateTime);

    @Query(value= "select * from appointment where cast (appointment_date_time as text) like ':stringDate'", nativeQuery = true)
    List<Appointment> findAppointmentByDate(@Param("stringDate") String stringDate);

    @Query(value= "select * from appointment where appointment_apk is true", nativeQuery = true)
    List<Appointment> findAppointmentByApkIsTrue();

    @Query(value= "select * from appointment where appointment_repair is true", nativeQuery = true)
    List<Appointment> findAppointmentByRepairIsTrue();
}
