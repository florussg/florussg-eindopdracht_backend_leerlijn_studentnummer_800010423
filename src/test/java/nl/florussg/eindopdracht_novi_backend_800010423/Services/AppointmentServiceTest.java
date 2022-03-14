package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.BadRequestException;
import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.RecordNotFoundException;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.ApkStatus;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Appointment;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Car;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Customer;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.AppointmentRepository;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CarRepository;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {


    @InjectMocks
    private AppointmentService appointmentService;

    @Mock
    private ApkStatus apkStatus;

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
    Appointment appointmentSetApk = new Appointment();

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

        appointmentSetApk.setApkStatus(ApkStatus.APK_pass);

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

        Iterable<Appointment> foundApkAppointments = appointmentService.getAllApkAppointments();

        verify(appointmentRepository, times(1)).findAppointmentByApkIsTrue();

        assertThat(foundApkAppointments).isEqualTo(appointments);
    }

    @Test
    void getAllApkAppointmentsException() {
        assertThrows(RecordNotFoundException.class, () -> appointmentService.getAllApkAppointments());
    }

    @Test
    void getAllRepairAppointments() {

        when(appointmentRepository.findAppointmentByRepairIsTrue()).thenReturn(appointments);

        Iterable<Appointment> foundRepairAppointments = appointmentService.getAllRepairAppointments();

        verify(appointmentRepository, times(1)).findAppointmentByRepairIsTrue();

        assertThat(foundRepairAppointments).isEqualTo(appointments);
    }

    @Test
    void getAllRepairAppointmentsException() {
        assertThrows(RecordNotFoundException.class, () -> appointmentService.getAllRepairAppointments());
    }

    @Test
    void addNewAppointment() {

        when(appointmentRepository.save(ArgumentMatchers.any(Appointment.class))).thenReturn(appointmentOne);

       Appointment appointmentAdded = appointmentRepository.save(appointmentOne);
       long AppointmentCreatedId = appointmentService.addNewAppointment(appointmentOne);

       assertThat(AppointmentCreatedId).isSameAs(appointmentAdded.getId());
    }

    @Test
    void deleteAppointment() {
        when(appointmentRepository.findById(appointmentOne.getId())).thenReturn(Optional.of(appointmentOne));

        appointmentService.deleteAppointment(appointmentOne.getId());
        verify(appointmentRepository).deleteById(appointmentOne.getId());
    }
    @Test
    void deleteAppointmentException() {
        assertThrows(RecordNotFoundException.class, () -> appointmentService.deleteAppointment(1000L));
    }

    @Test
    void editAppointment() {

        when(appointmentRepository.findById(1L)).thenReturn(Optional.ofNullable(appointmentOne));
        when(appointmentRepository.save(appointmentOne)).thenReturn(appointmentOne);

        Appointment appointmentToEdit = appointmentService.editAppointment(1L, appointmentOne);

        verify(appointmentRepository, times(1)).findById(appointmentOne.getId());
        verify(appointmentRepository, times(1)).save(appointmentOne);

        assertThat(appointmentToEdit.getId()).isEqualTo(appointmentOne.getId());
    }

    @Test
    void partialEditAppointment() {
        when(appointmentRepository.findById(1L)).thenReturn(Optional.ofNullable(appointmentOne));
        when(appointmentRepository.save(appointmentOne)).thenReturn(appointmentOne);

        Appointment appointmentPartialEdit = appointmentService.partialEditAppointment(1L, appointmentOne);

        verify(appointmentRepository, times(1)).findById(appointmentOne.getId());
        verify(appointmentRepository, times(1)).save(appointmentOne);

        assertThat(appointmentPartialEdit.getId()).isEqualTo(appointmentOne.getId());
    }

    @Test
    void partialEditAppointmentRepair() {
        when(appointmentRepository.findById(2L)).thenReturn(Optional.ofNullable(appointmentThree));
        when(appointmentRepository.save(appointmentTwo)).thenReturn(appointmentThree);

        Appointment appointmentPartialEdit = appointmentService.partialEditAppointment(2L, appointmentTwo);

        verify(appointmentRepository, times(1)).findById(appointmentTwo.getId());
        verify(appointmentRepository, times(1)).save(appointmentTwo);

        assertThat(appointmentPartialEdit.getId()).isEqualTo(appointmentTwo.getId());
    }

    @Test
    void setApkStatus() {

        when(appointmentRepository.findById(3L)).thenReturn(Optional.ofNullable(appointmentThree));
        when(appointmentRepository.save(appointmentThree)).thenReturn(appointmentThree);

        appointmentThree.setApkStatus(ApkStatus.APK_pass);
        Appointment appointmentToSetApkStatus = appointmentService.setApkStatus(3L, appointmentSetApk);

        //verify(appointmentRepository, times(1)).findById(appointmentThree.getId());
        verify(appointmentRepository, times(1)).save(appointmentThree);
        assertThat(appointmentToSetApkStatus.getApkStatus()).isSameAs(appointmentThree.getApkStatus());


        //assertThat(appointmentToSetApkStatus.getApkStatus()).isEqualTo(appointmentThree.getApkStatus());

    }

    @Test
    void addCustomerToAppointment() {
    }

    @Test
    void addCarToAppointment() {
    }

    @Test
    void checkIfAppointmentsPerDayIsNotHigherThenThree() {
        }





    @Test //WERKT NIET
    void addNewAppointmentException() {

//        when(appointmentRepository.save(appointmentOne)).thenReturn(appointmentOne);
//        when(appointmentRepository.save(appointmentTwo)).thenReturn(appointmentTwo);
//        when(appointmentRepository.save(appointmentThree)).thenReturn(appointmentThree);
//        when(appointmentRepository.save(appointmentFour)).thenReturn(appointmentFour);

        //when(appointmentRepository.findAppointmentByDate(String.valueOf(appointmentOne.getDateTimeAppointment()))).thenReturn(appointments);
        //List foundAppointment = appointmentRepository.findAppointmentByDate(date);

        //long appointmentWithException = appointmentService.addNewAppointment(appointmentFour);

//        verify(appointmentRepository, times(1)).save(appointmentOne);
//        verify(appointmentRepository, times(1)).save(appointmentTwo);
//        verify(appointmentRepository, times(1)).save(appointmentThree);
        //verify(appointmentRepository, times(1)).save(appointmentFour);

        //assertThrows(BadRequestException.class, () -> appointmentService.addNewAppointment(appointmentFour));


        //when(appointmentRepository.save(ArgumentMatchers.any(Appointment.class))).thenReturn(appointmentOne);
        //when(appointmentRepository.save(ArgumentMatchers.any(Appointment.class))).thenReturn(appointmentTwo);

        //when(appointmentService.checkIfAppointmentsPerDayIsNotHigherThenThree(appointmentFour.getDateTimeAppointment())).thenReturn(false);
        //when(appointmentRepository.findAppointmentByDate(String.valueOf(appointmentOne.getDateTimeAppointment()))).thenReturn(appointments);
        //when(appointmentService.checkIfAppointmentsPerDayIsNotHigherThenThree(any())).thenReturn(false);
        //when(appointmentService.checkIfAppointmentsPerDayIsNotHigherThenThree(appointmentThree.getDateTimeAppointment())).thenReturn(true);

        //assertThrows(BadRequestException.class, () -> appointmentService.addNewAppointment(appointmentFour));

        //when(appointmentRepository.save(ArgumentMatchers.any(Appointment.class))).thenReturn(appointmentOne);

//        Appointment firstAppointmentAdded = appointmentRepository.save(appointmentOne);
//        Appointment secondAppointmentAdded = appointmentRepository.save(appointmentTwo);
//        Appointment thirdAppointmentAdded = appointmentRepository.save(appointmentThree);
//        Appointment fourthAppointmentAdded = appointmentRepository.save(appointmentFour);

        //boolean toManyAppointments = appointmentService.checkIfAppointmentsPerDayIsNotHigherThenThree(datetime4);

        //assertThrows(RecordNotFoundException.class, () -> appointmentService.addNewAppointment(null));
    }




}