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

    public List<Appointment> getAllApkAppointments() {
        List<Appointment> allApkAppointments = appointmentRepository.findAppointmentByApkIsTrue();
        if(allApkAppointments.size() > 0) {
            return allApkAppointments;
        } else {
            throw new RecordNotFoundException("There are no open APK appointments");
        }
    }

//    public Appointment getAllApkAppointments() {
//        Optional<Appointment> optionalApkAppointments = appointmentRepository.findAppointmentByApkIsTrue();
//        if (optionalApkAppointments.isPresent()) {
//            Appointment foundApkAppointments = optionalApkAppointments.get();
//            return foundApkAppointments;
//        } else {
//            throw new RecordNotFoundException("There are no open APK appointments");
//        }
//    }

//    public Iterable<Appointment> getAllApkAppointments() {
//            List<Appointment> foundApkAppointments = appointmentRepository.findAppointmentByApkIsTrue();
//            return foundApkAppointments;
//        }


//    public Appointment getAllRepairAppointments() {
//        Optional<Appointment> optionalRepairAppointments = appointmentRepository.findAppointmentByRepairIsTrue();
//        if (optionalRepairAppointments.isPresent()) {
//            Appointment foundRepairAppointments = optionalRepairAppointments.get();
//            return foundRepairAppointments;
//        } else {
//            throw new RecordNotFoundException("There are no open repair appointments");
//        }
//    }

    public List<Appointment> getAllRepairAppointments() {
        List<Appointment> allRepairAppointments = appointmentRepository.findAppointmentByRepairIsTrue();
        if(allRepairAppointments.size() > 0) {
            return allRepairAppointments;
        } else {
            throw new RecordNotFoundException("There are no open repair appointments");
        }
    }


    public long addNewAppointment(Appointment appointment) {
        if (checkIfAppointmentsPerDayIsNotHigherThenThree(appointment.getDateTimeAppointment()) == true) {

//        Appointment newAppointment = new Appointment();
//        newAppointment.setDateTimeAppointment(appointment.getDateTimeAppointment());
//        newAppointment.setApk(appointment.getApk());
//        newAppointment.setRepair(appointment.getRepair());
//        newAppointment.setCarAppointment(appointment.getCarAppointment());

            Appointment saveAppointment = appointmentRepository.save(appointment);
            return saveAppointment.getId();

        } else {
            throw new BadRequestException("To many appointments today, make another appointment");
        }
    }




//    public boolean checkIfAppointmentsPerDayIsNotHigherThenThree(Appointment appointment) {
//
//       String dateNewAppointment = getDateFromDateTimeAppointment(appointment);
//
//        Iterator<Appointment> optionalAppointment = appointmentRepository.findAppointmentByDateTimeAppointment_Date();
//
//
//        if (optionalAppointment.equals(dateNewAppointment)) {
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
//            return true;
//
//        } else {
//            return false;
//        }
//    }



    public String getDateFromDateTimeAppointment (LocalDateTime dateTimeAppointment) {
        String date = String.valueOf(dateTimeAppointment);
        LocalDateTime ldt = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);

        return ldt.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
    }


    public boolean checkIfAppointmentsPerDayIsNotHigherThenThree(LocalDateTime dateTimeAppointment) {

        String date = getDateFromDateTimeAppointment(dateTimeAppointment);

        List foundAppointment = appointmentRepository.findAppointmentByDate(date);
        //hier gaat het mis, var banaan wordt niet gezet!
        //var banaan = new String("banaan");
        if (foundAppointment.size() > 3) {
            return false;
        } else {
            return true;
        }
    }
//        Iterator<Appointment> optionalAppointment = appointmentRepository.find();
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



//    public boolean checkIfTimeAssignmentIsAfter0900 (Appointment appointment) {
//        String DateTime = appointment.
//        LocalDate appointmentInputTime = appointment.
//    }

}



