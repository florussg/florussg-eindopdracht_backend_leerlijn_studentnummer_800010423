package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.BadRequestException;
import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.RecordNotFoundException;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Appointment;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Car;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Customer;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.AppointmentRepository;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CarRepository;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        if (allApkAppointments.size() > 0) {
            return allApkAppointments;
        } else {
            throw new RecordNotFoundException("There are no open APK appointments");
        }
    }

    public List<Appointment> getAllRepairAppointments() {
        List<Appointment> allRepairAppointments = appointmentRepository.findAppointmentByRepairIsTrue();
        if (allRepairAppointments.size() > 0) {
            return allRepairAppointments;
        } else {
            throw new RecordNotFoundException("There are no open repair appointments");
        }
    }

    public long addNewAppointment(Appointment appointment) {
        if (checkIfAppointmentsPerDayIsNotHigherThenThree(appointment.getDateTimeAppointment()) == true) {

            Appointment saveAppointment = appointmentRepository.save(appointment);
            return saveAppointment.getId();

        } else {
            throw new BadRequestException("To many appointments today, make an appointment on another day");
        }
    }

    public void deleteAppointment(long id) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isPresent()) {
            appointmentRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("There is no appointment with this id");
        }
    }

    public Appointment editAppointment(long id, Appointment appointment) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isPresent()) {
            Appointment appointmentToEdit = optionalAppointment.get();

            appointmentToEdit.setDateTimeAppointment(appointment.getDateTimeAppointment());
            appointmentToEdit.setApk(appointment.getApk());
            appointmentToEdit.setRepair(appointment.getRepair());

            appointmentRepository.save(appointmentToEdit);
            //

        } else {
            throw new RecordNotFoundException("There is no appointment with this id");
        }
        return appointment;
    }

    public Appointment partialEditAppointment(long id, Appointment appointment) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);

        if (optionalAppointment.isPresent()) {
            Appointment appointmentToEdit = optionalAppointment.get();

            if (appointment.getDateTimeAppointment() != null) {
                appointmentToEdit.setDateTimeAppointment(appointment.getDateTimeAppointment());
            }
            if (appointment.getApk() != null && appointment.getApk().booleanValue()) {
                appointmentToEdit.setApk(appointment.getApk());
            }
            if (appointment.getRepair() != null && appointment.getRepair().booleanValue()) {
                appointmentToEdit.setRepair(appointment.getRepair());
            }

            appointmentRepository.save(appointmentToEdit);

        } else {
            throw new RecordNotFoundException("There is no appointment with this id");
        }
        return appointment;
    }

    public Appointment setApkStatus(long id, Appointment status) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);

        if (optionalAppointment.isPresent()) {
            Appointment appointmentToEdit = optionalAppointment.get();
            if (appointmentToEdit.getApk() == true) {
                if (status.getApkStatus() != null && !status.getApkStatus().toString().isEmpty()) {
                    appointmentToEdit.setApkStatus(status.getApkStatus());
                } else {
                    throw new BadRequestException("You can not change the APK status because the input field is null");
                }
            } else {
                throw new BadRequestException("This is not an APK appointment");
            }

            appointmentRepository.save(appointmentToEdit);

            return appointmentToEdit;

        } else {
            throw new RecordNotFoundException("There is no appointment with this id");
        }
    }

    public Appointment addCustomerToAppointment(long appointmentId, int customerBsn) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        Optional<Customer> optionalCustomer = customerRepository.findCustomerByBsnnumber(customerBsn);

        if (optionalAppointment.isPresent() && optionalCustomer.isPresent()) {

            Appointment appointmentToEdit = optionalAppointment.get();
            Customer customerToAdd = optionalCustomer.get();

            appointmentToEdit.setAppointmentOfCustomer(customerToAdd);

            appointmentRepository.save(appointmentToEdit);

            return appointmentToEdit;

        } else {
            throw new RecordNotFoundException("There is no appointment with this id or the customer with this BSN does not exist");
        }
    }

    public Appointment addCarToAppointment(long appointmentId, String licenseplateNumber) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        Optional<Car> optionalCar = carRepository.findCarByLicenseplateNumberContainingIgnoreCase(licenseplateNumber);

        if (optionalAppointment.isPresent()) {
            System.out.println("appointment found");

            if (optionalCar.isPresent()) {
                System.out.println("car found");
            } else {
                throw new RecordNotFoundException("There is no car with this licenseplate number");
            }

            Appointment appointmentToEdit = optionalAppointment.get();
            Car carToAdd = optionalCar.get();

            appointmentToEdit.setCarAppointment(carToAdd);

            appointmentRepository.save(appointmentToEdit);

            return appointmentToEdit;

        } else {
            throw new RecordNotFoundException("There is no appointment with this id");
        }

    }
        //methods
        public String getDateFromDateTimeAppointment (LocalDateTime dateTimeAppointment){
            String date = String.valueOf(dateTimeAppointment);
            LocalDateTime ldt = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);

            return ldt.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
        }

        public boolean checkIfAppointmentsPerDayIsNotHigherThenThree (LocalDateTime dateTimeAppointment){

            String date = getDateFromDateTimeAppointment(dateTimeAppointment);

            List foundAppointment = appointmentRepository.findAppointmentByDate(date);
            if (foundAppointment.size() > 3) {
                return false;
            } else {
                return true;
            }
        }
    }






