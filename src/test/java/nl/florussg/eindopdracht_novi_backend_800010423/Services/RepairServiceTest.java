package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.BadRequestException;
import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.RecordNotFoundException;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Appointment;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Repair;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.RepairStatus;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.AppointmentRepository;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.RepairRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RepairServiceTest {

    @InjectMocks
    private RepairService repairService;

    @Mock
    private RepairRepository repairRepository;

    @Mock
    private AppointmentRepository appointmentRepository;

    Repair repairOne = new Repair();
    Repair repairTwo = new Repair();
    Repair repairThree = new Repair();
    Repair repairWithStatusStarted = new Repair();

    List<Repair> repairs = new ArrayList<>();

    Appointment appointmentOne = new Appointment();
    Appointment appointmentTwo = new Appointment();
    LocalDateTime datetime1 =  LocalDateTime.of(2022, 5, 25, 12, 00, 00);
    LocalDateTime datetime2 =  LocalDateTime.of(2022, 5, 25, 13, 00, 00);

    @BeforeEach
    public void setUp() {

        repairOne.setId(1L);
        repairOne.setFinding("Bad wheels");
        repairOne.setRepairStatus(RepairStatus.STARTED);

        repairTwo.setRepairStatus(RepairStatus.PASS);
        repairThree.setId(3L);
        repairThree.setFinding("All is bad");

        repairWithStatusStarted.setRepairStatus(RepairStatus.STARTED);

        appointmentOne.setId(1L);
        appointmentOne.setDateTimeAppointment(datetime1);
        appointmentOne.setApk(true);
        appointmentOne.setRepair(true);

        repairOne.setRepairAppointment(appointmentOne);


        appointmentTwo.setId(2L);
        appointmentTwo.setDateTimeAppointment(datetime2);
        appointmentTwo.setApk(true);
        appointmentTwo.setRepair(false);



        repairs.add(repairOne);


    }

    @Test
    void createRepairAndLinkItToTheAppointment() {
    }

    @Test
    void setRepairStatus() {

        when(repairRepository.findById(1L)).thenReturn(Optional.of(repairOne));
        when(repairRepository.save(repairOne)).thenReturn(repairOne);

        Repair repairToSetStatus = repairService.setRepairStatus(1L, repairTwo);

        verify(repairRepository, times(1)).findById(1L);
        verify(repairRepository, times(1)).save(repairOne);

        assertThat(repairToSetStatus.getId()).isEqualTo(repairOne.getId());
    }

    @Test
    void setRepairStatusException() {

        Exception exception = assertThrows(RecordNotFoundException.class, () -> {
            repairService.setRepairStatus(1000L, repairTwo);
        });

        String expectedMessage = "There is no repair with this id";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void findRepairByRepairStatus() {

        when(repairRepository.findRepairByRepairStatusEquals(RepairStatus.STARTED)).thenReturn(repairs);

        List<Repair> foundRepairs = repairService.findRepairByRepairStatus(RepairStatus.STARTED);

        verify(repairRepository, times(1)).findRepairByRepairStatusEquals(RepairStatus.STARTED);

        assertThat(foundRepairs).isEqualTo(repairs);
    }

    @Test
    void findRepairByRepairStatusException() {

        Exception exception = assertThrows(RecordNotFoundException.class, () -> repairService.findRepairByRepairStatus(RepairStatus.FAIL));

        String expectedMessage = "There are no repairs based on your input";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getAllRepairs() {

        when(repairService.getAllRepairs()).thenReturn(repairs);

        Iterable<Repair> foundRepairs = repairService.getAllRepairs();

        verify(repairRepository, times(1)).findAll();

        assertThat(foundRepairs).isEqualTo(repairs);
    }


    @Test
    void linkRepairToAppointment() {

        when(repairRepository.findById(1L)).thenReturn(Optional.ofNullable(repairOne));
        when(repairRepository.save(repairOne)).thenReturn(repairOne);
        when(appointmentRepository.findById(1L)).thenReturn(Optional.ofNullable(appointmentOne));

        long repairX = repairService.linkRepairToAppointment(1L, 1L);

        verify(repairRepository, times(1)).findById(1L);
        verify(repairRepository, times(1)).save(repairOne);

        assertThat(repairX).isEqualTo(repairOne.getId());
    }

    @Test
    void linkRepairToAppointmentException() {

        Exception exception = assertThrows(RecordNotFoundException.class, () -> repairService.linkRepairToAppointment(1000L, 1L));

        String expectedMessage = "Repair or appointment does not exist";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    //TODO: werkt niet
    @Test
    void autoSetStartingRepairStatus() {

        when(repairRepository.findById(3L)).thenReturn(Optional.ofNullable(repairThree));
        when(repairRepository.save(repairThree)).thenReturn(repairThree);

       Repair repair = repairService.autoSetStartingRepairStatus(3L);

        verify(repairRepository, times(1)).findById(3L);
        verify(repairRepository, times(1)).save(repairThree);

        assertThat(repair.getRepairStatus()).isEqualTo(repairThree.getRepairStatus());
    }

    @Test
    void checkIfAppointmentHasRepairBooleanTrue() {
        when(appointmentRepository.findById(1L)).thenReturn(Optional.ofNullable(appointmentOne));

        boolean appointment = repairService.checkIfAppointmentHasRepairBooleanTrue(1L);

        assertThat(appointment).isEqualTo(appointmentOne.getRepair());

    }

    @Test
    void checkIfAppointmentHasRepairBooleanTrueTwo() {
        when(appointmentRepository.findById(2L)).thenReturn(Optional.ofNullable(appointmentTwo));

        boolean appointment = repairService.checkIfAppointmentHasRepairBooleanTrue(2L);

        assertThat(appointment).isEqualTo(appointmentTwo.getRepair());
    }

    @Test
    void checkIfAppointmentHasRepairBooleanTrueException() {

        Exception exception = assertThrows(RecordNotFoundException.class, () -> repairService.checkIfAppointmentHasRepairBooleanTrue(1000L));

        String expectedMessage = "There is no appointment with this id";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createRepairAndLinkItToTheAppointmentException() {

        Exception exception = assertThrows(RecordNotFoundException.class, () -> repairService.createRepairAndLinkItToTheAppointment(repairTwo, 1000L));

        String expectedMessage = "There is no appointment with this id!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


}