package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.RecordNotFoundException;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Appointment;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.AppointmentRepository;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CarRepository;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;


    public Iterable<Appointment> getAllAppointments() {
        List<Appointment> all = appointmentRepository.findAll();
        return all;
    }

    public Appointment getAllApkAppointments() {
        Optional<Appointment> optionalApkAppointments = appointmentRepository.findAppointmentByApkIsTrue();
        if (optionalApkAppointments.isPresent()) {
            Appointment foundApkAppointments = optionalApkAppointments.get();
            return foundApkAppointments;
        } else {
            throw new RecordNotFoundException("There are no open APK appointments");
        }
    }

    public Appointment getAllRepairAppointments() {
        Optional<Appointment> optionalRepairAppointments = appointmentRepository.findAppointmentByRepairIsTrue();
        if (optionalRepairAppointments.isPresent()) {
            Appointment foundRepairAppointments = optionalRepairAppointments.get();
            return foundRepairAppointments;
        } else {
            throw new RecordNotFoundException("There are no open repair appointments");
        }
    }


    //SAMEN MET JOHAN NAAR KIJKEN?????~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public boolean checkIfAppointmentsPerDayIsNotHigherThenFour(Appointment appointment) {

        LocalDate appointmentInputDate = appointment.getDateFromAppointment(appointment);
        Iterator<Appointment> optionalAppointment = appointmentRepository.findAppointmentByDate();

        if (optionalAppointment.equals(appointmentInputDate)) {

            for (Iterator<Appointment> it = optionalAppointment; it.hasNext(); ) {
                int appointmentsPerDay = '0';
                int maxAppointmentsPerDay = '4';
                appointmentsPerDay++;
                if (appointmentsPerDay >= maxAppointmentsPerDay) {
                    return false;

                } else {
                    return true;
                }
            }
        }
        else {
            return true;
        }
        return true;
    }

}



