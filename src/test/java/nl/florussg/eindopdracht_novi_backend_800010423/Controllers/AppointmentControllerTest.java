package nl.florussg.eindopdracht_novi_backend_800010423.Controllers;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.Appointment;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Car;
import nl.florussg.eindopdracht_novi_backend_800010423.Services.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class AppointmentControllerTest {

    @InjectMocks
    AppointmentController appointmentController;

    @Mock
    AppointmentService appointmentService;

    Appointment appointmentOne = new Appointment();
    Appointment appointmentTwo = new Appointment();
    Car car = new Car();

    List<Appointment> appointments = new ArrayList<>();
    List<Appointment> appointmentsApk = new ArrayList<>();
    List<Appointment> appointmentsRepair = new ArrayList<>();


    @BeforeEach
    public void setUp() {

        appointmentOne.setId(1L);
        appointmentOne.setDateTimeAppointment(LocalDateTime.parse("2022-04-01T12:00:00"));
        appointmentOne.setApk(true);
        appointmentOne.setRepair(false);

        appointmentTwo.setId(2L);
        appointmentTwo.setDateTimeAppointment(LocalDateTime.parse("2022-05-01T12:00:00"));
        appointmentTwo.setApk(false);
        appointmentTwo.setRepair(true);

        appointments.add(appointmentOne);
        appointments.add(appointmentTwo);

        appointmentsApk.add(appointmentOne);
        appointmentsRepair.add(appointmentTwo);

        car.setId(1L);
        car.setBrand("Seat");
        car.setType("Leon");
        car.setLicenseplatenumber("31-LZ-XL");
    }

    @Test
    void getAllAppointments() {

        when(appointmentService.getAllAppointments()).thenReturn(appointments);

        appointmentController.getAllAppointments();

        verify(appointmentService, times(1)).getAllAppointments();
    }

    @Test
    void getAllApkAppointments() {

        when(appointmentService.getAllApkAppointments()).thenReturn(appointmentsApk);

        appointmentController.getAllApkAppointments();

        verify(appointmentService, times(1)).getAllApkAppointments();
    }

    @Test
    void getAllRepairAppointments() {

        when(appointmentService.getAllRepairAppointments()).thenReturn(appointmentsRepair);

        appointmentController.getAllRepairAppointments();

        verify(appointmentService, times(1)).getAllRepairAppointments();

    }

    @Test
    void deleteAppointment() {

        appointmentController.deleteAppointment(1L);
        verify(appointmentService, times(1)).deleteAppointment(1L);
    }

}