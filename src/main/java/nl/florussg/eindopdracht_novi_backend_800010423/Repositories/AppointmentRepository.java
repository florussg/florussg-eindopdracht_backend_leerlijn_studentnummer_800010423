package nl.florussg.eindopdracht_novi_backend_800010423.Repositories;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;


public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query(value = "SELECT * FROM appointment u WHERE DATE(appointment_date_time) =?1", nativeQuery = true)
    List<Appointment> findByDate(LocalDate date);

    @Query(value= "select * from appointment where appointment_apk is true", nativeQuery = true)
    List<Appointment> findAppointmentByApkIsTrue();

    @Query(value= "select * from appointment where appointment_repair is true", nativeQuery = true)
    List<Appointment> findAppointmentByRepairIsTrue();
}
