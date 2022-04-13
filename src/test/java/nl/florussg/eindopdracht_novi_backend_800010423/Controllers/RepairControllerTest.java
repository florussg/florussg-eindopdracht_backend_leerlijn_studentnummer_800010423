package nl.florussg.eindopdracht_novi_backend_800010423.Controllers;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.*;
import nl.florussg.eindopdracht_novi_backend_800010423.Services.PartService;
import nl.florussg.eindopdracht_novi_backend_800010423.Services.RepairService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class RepairControllerTest {

    @InjectMocks
    RepairController repairController;

    @Mock
    RepairService repairService;

    @Mock
    RepairStatus repairStatus;

    @Mock
    Part part;


    Repair repairOne = new Repair();
    Repair repairTwo = new Repair();

    Appointment appointment = new Appointment();

    RepairPart partZ = new RepairPart();
    List<RepairPart> parts = new ArrayList<>();

    List<Repair> repairs = new ArrayList<>();

    @BeforeEach
    public void setUp() {

        appointment.setId(1L);
        //partZ.setId(1L);
        parts.add(partZ);

        repairOne.setId(1L);
        repairOne.setRepairAppointment(appointment);
        repairOne.setRepairStatus(repairStatus.STARTED);
        repairOne.setPartToRepair(parts);
        repairOne.setFinding("APK failed");

        repairs.add(repairOne);

    }

    @Test
    void addRepairToAppointment() {
    }

    @Test
    void setRepairStatus() {
    }

    @Test
    void getAllRepairsBasedOnRepairStatus() {

    }

    @Test
    void getAllRepairs() {

    }
}