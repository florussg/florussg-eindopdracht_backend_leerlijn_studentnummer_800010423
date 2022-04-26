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

        car.setId(2L);
        car.setBrand("Seat");
        car.setType("Leon");
        car.setLicenseplatenumber("31-LZ-XL");

        customer.setId(2L);
        customer.setFirstname("Piet");
        customer.setLastname("Janssen");
        customer.setBsnnumber(111111111);
        customer.setPhonenumber(0612121212);

        appointmentOne.setId(1L);
        appointmentOne.setDateTimeAppointment(datetime1);
        appointmentOne.setApk(true);
        appointmentOne.setRepair(false);
        appointmentOne.setCarAppointment(car);
        appointmentOne.setCustomerAppointment(customer);

        appointmentTwo.setId(2L);
        appointmentTwo.setDateTimeAppointment(datetime2);
        appointmentTwo.setApk(false);
        appointmentTwo.setRepair(true);
        appointmentTwo.setCarAppointment(car);
        appointmentTwo.setCustomerAppointment(customer);

        appointmentThree.setId(3L);
        appointmentThree.setDateTimeAppointment(datetime3);
        appointmentThree.setApk(true);
        appointmentThree.setRepair(false);
        appointmentThree.setCarAppointment(car);
        appointmentThree.setCustomerAppointment(customer);

        appointmentFour.setId(4L);
        appointmentFour.setDateTimeAppointment(datetime4);
        appointmentFour.setApk(true);
        appointmentFour.setRepair(true);
        appointmentFour.setCarAppointment(car);
        appointmentFour.setCustomerAppointment(customer);

        appointmentSetApk.setApkStatus(ApkStatus.PASS);

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
    void editAppointmentException() {

        Exception exception = assertThrows(RecordNotFoundException.class, () -> appointmentService.editAppointment(1000L, appointmentOne));

        String expectedMessage = "There is no appointment with this id";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
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
    void partialEditAppointmentException() {

        Exception exception = assertThrows(RecordNotFoundException.class, () -> appointmentService.partialEditAppointment(1000L, appointmentOne));

        String expectedMessage = "There is no appointment with this id";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void partialEditAppointmentRepair() {
        when(appointmentRepository.findById(2L)).thenReturn(Optional.ofNullable(appointmentTwo));
        when(appointmentRepository.save(appointmentTwo)).thenReturn(appointmentTwo);

        Appointment appointmentPartialEdit = appointmentService.partialEditAppointment(2L, appointmentTwo);

        verify(appointmentRepository, times(1)).findById(appointmentTwo.getId());
        verify(appointmentRepository, times(1)).save(appointmentTwo);

        assertThat(appointmentPartialEdit.getId()).isEqualTo(appointmentTwo.getId());
    }

    @Test
    void setApkStatus() {

        when(appointmentRepository.findById(3L)).thenReturn(Optional.of(appointmentThree));
        when(appointmentRepository.save(appointmentThree)).thenReturn(appointmentThree);

        Appointment appointmentToSetApkStatus = appointmentService.setApkStatus(3L, appointmentSetApk);

        verify(appointmentRepository, times(1)).findById(appointmentThree.getId());
        verify(appointmentRepository, times(1)).save(appointmentThree);

       assertThat(appointmentToSetApkStatus.getId()).isEqualTo(appointmentThree.getId());
    }

    @Test
    void setApkStatusIsNullException() {

        Appointment appointmentApkStatusNull = new Appointment();
        appointmentApkStatusNull.setId(10L);
        appointmentApkStatusNull.setApk(true);
        appointmentApkStatusNull.setApkStatus(null);

        when(appointmentRepository.findById(10L)).thenReturn(Optional.of(appointmentApkStatusNull));

        Exception exception = assertThrows(BadRequestException.class, () -> {
            appointmentService.setApkStatus(10L, appointmentApkStatusNull);
        });

        String expectedMessage = "You can not change the APK status because the input field is null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void setApkStatusNotAnApkAppointmentException() {

        Appointment appointmentApkStatusNotAnApkAppointment = new Appointment();
        appointmentApkStatusNotAnApkAppointment.setId(10L);
        appointmentApkStatusNotAnApkAppointment.setApk(false);

        when(appointmentRepository.findById(10L)).thenReturn(Optional.of(appointmentApkStatusNotAnApkAppointment));

        Exception exception = assertThrows(BadRequestException.class, () -> {
            appointmentService.setApkStatus(10L, appointmentApkStatusNotAnApkAppointment);
        });

        String expectedMessage = "This is not an APK appointment";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void setApkStatusNoAppointmentFound() {
        Exception exception = assertThrows(RecordNotFoundException.class, () -> {
            appointmentService.setApkStatus(10L, appointmentOne);
        });

        String expectedMessage = "There is no appointment with this id";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void addCustomerToAppointment() {

        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointmentOne));
        when(appointmentRepository.save(appointmentOne)).thenReturn(appointmentOne);
        when(customerRepository.findCustomerByBsnnumber(111111111)).thenReturn((Optional.of(customer)));

        Appointment customerAdded = appointmentService.addCustomerToAppointment(1L, 111111111);

        verify(appointmentRepository, times(1)).findById(appointmentOne.getId());
        verify(appointmentRepository, times(1)).save(appointmentOne);

        assertThat(customerAdded.getCustomerAppointment()).isEqualTo(appointmentOne.getCustomerAppointment());
    }

    @Test
    void addCustomerToAppointmentException() {

        Exception exception = assertThrows(RecordNotFoundException.class, () -> appointmentService.addCustomerToAppointment(1000L, 111111111));

        String expectedMessage = "There is no appointment with this id or the customer with this BSN does not exist";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void addCarToAppointment() {
        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointmentOne));
        when(appointmentRepository.save(appointmentOne)).thenReturn(appointmentOne);
        when(carRepository.findCarByLicenseplateNumberContainingIgnoreCase("31-LZ-XL")).thenReturn(Optional.of(car));

        Appointment carAdded = appointmentService.addCarToAppointment(1L, "31-LZ-XL");

        verify(appointmentRepository, times(1)).findById(appointmentOne.getId());
        verify(appointmentRepository, times(1)).save(appointmentOne);

        assertThat(carAdded.getCarAppointment()).isEqualTo(appointmentOne.getCarAppointment());
    }

    @Test
    void addCarToAppointmentException() {
        Exception exception = assertThrows(RecordNotFoundException.class, () -> appointmentService.addCarToAppointment(1000L, "GL-33-NN"));

        String expectedMessage = "There is no appointment with this id";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void addCarToAppointmentExceptionTwo() {
        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointmentOne));
        when(carRepository.findCarByLicenseplateNumberContainingIgnoreCase("NO-NO-99")).thenReturn(Optional.ofNullable(null));

        Exception exception = assertThrows(RecordNotFoundException.class, () -> appointmentService.addCarToAppointment(1L, "NO-NO-99"));

        String expectedMessage = "There is no car with this licenseplate number";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


}