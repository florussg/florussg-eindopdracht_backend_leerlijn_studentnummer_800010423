package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.RecordNotFoundException;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Appointment;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.AppointmentRepository;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CarRepository;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
         if(optionalApkAppointments.isPresent()) {
             Appointment foundApkAppointments = optionalApkAppointments.get();
             return foundApkAppointments;
         } else {
             throw new RecordNotFoundException("There are no open APK appointments");
            }

    }


}
