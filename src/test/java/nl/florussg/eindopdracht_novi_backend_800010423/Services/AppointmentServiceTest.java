package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.RecordNotFoundException;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Appointment;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Car;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Customer;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.AppointmentRepository;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CarRepository;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {


    @InjectMocks
    private AppointmentService appointmentService;

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CarRepository carRepository;

    Appointment appointmentOne = new Appointment();
    Appointment appointmentTwo = new Appointment();
    Appointment appointmentThree = new Appointment();
    Appointment appointmentFour = new Appointment();
    List<Appointment> appointments = new ArrayList<>();

    LocalDateTime datetime1 =  LocalDateTime.of(2022, 5, 25, 12, 00, 00);
    LocalDateTime datetime2 =  LocalDateTime.of(2022, 5, 25, 13, 00, 00);
    LocalDateTime datetime3 =  LocalDateTime.of(2022, 5, 25, 14, 00, 00);
    LocalDateTime datetime4 =  LocalDateTime.of(2022, 5, 25, 15, 00, 00);

    Customer customer = new Customer();

    Car car = new Car();



    @BeforeEach
    public void setUp() {

        car.setId(1L);
        car.setBrand("Seat");
        car.setType("Leon");
        car.setLicenseplatenumber("31-LZ-XL");
        car.setFileNameCarRegistrationDocument("kentekenpapieren.pdf");



        customer.setId(1L);
        customer.setFirstname("Piet");
        customer.setLastname("Janssen");
        customer.setBsnnumber(111111111);
        customer.setPhonenumber(0612121212);

        appointmentOne.setId(1L);
        appointmentOne.setDateTimeAppointment(datetime1);
        appointmentOne.setApk(true);
        appointmentOne.setRepair(false);
        appointmentOne.setCarAppointment(car);
        appointmentOne.setAppointmentOfCustomer(customer);

        appointmentTwo.setId(2L);
        appointmentTwo.setDateTimeAppointment(datetime2);
        appointmentTwo.setApk(false);
        appointmentTwo.setRepair(true);
        appointmentTwo.setCarAppointment(car);
        appointmentTwo.setAppointmentOfCustomer(customer);

        appointmentThree.setId(3L);
        appointmentThree.setDateTimeAppointment(datetime3);
        appointmentThree.setApk(true);
        appointmentThree.setRepair(false);
        appointmentThree.setCarAppointment(car);
        appointmentThree.setAppointmentOfCustomer(customer);

        appointmentFour.setId(4L);
        appointmentFour.setDateTimeAppointment(datetime4);
        appointmentFour.setApk(true);
        appointmentFour.setRepair(true);
        appointmentFour.setCarAppointment(car);
        appointmentFour.setAppointmentOfCustomer(customer);

        appointments.add(appointmentOne);
        appointments.add(appointmentTwo);
        appointments.add(appointmentThree);
        appointments.add(appointmentFour);

    }

    @Test
    void getAllAppointments() {
        when(appointmentRepository.findAll()).thenReturn(appointments);

        Iterable<Appointment> foundAppointments = appointmentService.getAllAppointments();

        verify(appointmentRepository, times(1)).findAll();

        assertThat(foundAppointments).isEqualTo(appointments);
    }

    @Test
    void getAllApkAppointments() {

        when(appointmentRepository.findAppointmentByApkIsTrue()).thenReturn(appointments);

        Iterable<Appointment> foundAppointments = appointmentService.getAllApkAppointments();

        verify(appointmentRepository, times(1)).findAppointmentByApkIsTrue();

        assertThat(foundAppointments).isEqualTo(appointments);
    }

    @Test
    void getAllApkAppointmentsException() {
        assertThrows(RecordNotFoundException.class, () -> appointmentService.getAllApkAppointments());
    }

    @Test
    void getAllRepairAppointments() {
    }

    @Test
    void addNewAppointment() {
    }

    @Test
    void deleteAppointment() {
    }

    @Test
    void editAppointment() {
    }

    @Test
    void partialEditAppointment() {
    }

    @Test
    void setApkStatus() {
    }

    @Test
    void addCustomerToAppointment() {
    }

    @Test
    void addCarToAppointment() {
    }

    @Test
    void getDateFromDateTimeAppointment() {
    }

    @Test
    void checkIfAppointmentsPerDayIsNotHigherThenThree() {
    }
}