package nl.florussg.eindopdracht_novi_backend_800010423.Controllers;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.*;
import nl.florussg.eindopdracht_novi_backend_800010423.Services.RepairService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class RepairControllerTest {

    @InjectMocks
    RepairController repairController;

    @Mock
    RepairService repairService;

    RepairStatus repairStatus;

    Repair repairOne = new Repair();
    Repair repairTwo = new Repair();

    Appointment appointment = new Appointment();
    Appointment appointmentTwo = new Appointment();

    RepairPart partZ = new RepairPart();
    List<RepairPart> parts = new ArrayList<>();

    List<Repair> repairs = new ArrayList<>();
    List<Repair> repairsTwo = new ArrayList<>();

    @BeforeEach
    public void setUp() {

        appointment.setId(1L);
        appointmentTwo.setId(2L);

        parts.add(partZ);

        repairOne.setId(1L);
        repairOne.setRepairAppointment(appointment);
        repairOne.setPartToRepair(parts);
        repairOne.setFinding("APK failed");

        repairs.add(repairOne);

        repairTwo.setId(2L);
        repairTwo.setRepairAppointment(appointmentTwo);
        repairTwo.setRepairStatus(repairStatus.STARTED);
        repairTwo.setPartToRepair(parts);
        repairTwo.setFinding("Bad brakes");

        repairsTwo.add(repairTwo);
    }

    @Test
    void getAllRepairsBasedOnRepairStatus() {

        when(repairService.findRepairByRepairStatus(repairStatus.STARTED)).thenReturn(repairsTwo);

        repairController.getAllRepairsBasedOnRepairStatus(repairStatus.STARTED);

        verify(repairService, times(1)).findRepairByRepairStatus(repairStatus.STARTED);
    }

    @Test
    void getAllRepairs() {

        when(repairService.getAllRepairs()).thenReturn(repairs);

        repairController.getAllRepairs();

        verify(repairService, times(1)).getAllRepairs();
    }
}