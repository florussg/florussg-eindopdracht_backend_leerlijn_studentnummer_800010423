package nl.florussg.eindopdracht_novi_backend_800010423.Repositories;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Iterator;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Optional<Appointment> findAppointmentByApkIsTrue();
    Optional<Appointment> findAppointmentByRepairIsTrue();
    Iterator<Appointment> findAppointmentByDate();

    Iterator<Appointment> findAppointmentByDateTime();
    Iterator<Appointment> findAppointmentByDateTimeAppointment_Date();

    //@Query(value= "SELECT DATE FROM appointment, COUNT appointment
    //@Query(value = "SELECT * FROM books b WHERE b.title LIKE %:s%", nativeQuery = true) // using SQL
}
