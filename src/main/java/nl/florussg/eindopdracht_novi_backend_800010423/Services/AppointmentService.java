package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.BadRequestException;
import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.RecordNotFoundException;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Appointment;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.AppointmentRepository;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CarRepository;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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


    public long addNewAppointment(Appointment appointment) {
        if (checkIfAppointmentsPerDayIsNotHigherThenThree(appointment) == true) {

            Appointment newAppointment = new Appointment();
            newAppointment.setDateTimeAppointment(appointment.getDateTimeAppointment());
            newAppointment.setApk(appointment.getApk());
            newAppointment.setRepair(appointment.getRepair());
            newAppointment.setCarAppointment(appointment.getCarAppointment());

            Appointment saveAppointment = appointmentRepository.save(newAppointment);
            return saveAppointment.getId();

        } else {
            throw new BadRequestException("To many appointments today, make another appointment");
        }
    }


    public boolean checkIfAppointmentsPerDayIsNotHigherThenThree(Appointment appointment) {

       String dateNewAppointment = getDateFromDateTimeAppointment(appointment);

        Iterator<Appointment> optionalAppointment = appointmentRepository.findAppointmentByDateTimeAppointment_MonthValueAndDayValue();
        Iterator dateRepo = getDateFromDateTimeAppointment(optionalAppointment);

        if (optionalAppointment.(date)) {

            for (Iterator<Appointment> it = optionalAppointment; it.hasNext(); ) {
                int appointmentsPerDay = '0';
                int maxAppointmentsPerDay = '3';
                appointmentsPerDay++;
                if (appointmentsPerDay >= maxAppointmentsPerDay) {
                    break;
                } else {
                    continue;
                }
            }
            return true;

        } else {
            return false;
        }
    }


    public String getDateFromDateTimeAppointment (Appointment appointment) {
        String date = String.valueOf(appointment.getDateTimeAppointment());
        LocalDateTime ldt = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);

        return ldt.format(DateTimeFormatter.ofPattern("MM-dd"));
    }


//    public boolean checkIfAppointmentsPerDayIsNotHigherThenThree(Appointment appointment) {
//
//        LocalDate appointmentInputDate = appointment.getDateFromAppointment(appointment);
//        Iterator<Appointment> optionalAppointment = appointmentRepository.findAppointmentByDate();
//
//        if (optionalAppointment.equals(appointmentInputDate)) {
//
//            for (Iterator<Appointment> it = optionalAppointment; it.hasNext(); ) {
//                int appointmentsPerDay = '0';
//                int maxAppointmentsPerDay = '3';
//                appointmentsPerDay++;
//                if (appointmentsPerDay >= maxAppointmentsPerDay) {
//                    break;
//                } else {
//                    continue;
//                }
//            }
//        return true;
//
//        } else {
//            return false;
//            }
//    }

//    public boolean checkIfTimeAssignmentIsAfter0900 (Appointment appointment) {
//        String DateTime = appointment.
//        LocalDate appointmentInputTime = appointment.
//    }

}



