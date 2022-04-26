package nl.florussg.eindopdracht_novi_backend_800010423.Controllers;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.Appointment;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Part;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Repair;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.RepairPart;
import nl.florussg.eindopdracht_novi_backend_800010423.Services.RepairPartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


@ExtendWith({MockitoExtension.class})
class RepairPartControllerTest {

    @InjectMocks
    RepairPartController repairPartController;

    @Mock
    RepairPartService repairPartService;

    Part partOne = new Part();

    Repair repairOne = new Repair();

    Appointment appointmentOne = new Appointment();

    List<Part> parts = new ArrayList<>();


    @BeforeEach
    void setUp() {

        appointmentOne.setId(1L);
        appointmentOne.setDateTimeAppointment(LocalDateTime.parse("2022-04-01T12:00:00"));
        appointmentOne.setApk(true);
        appointmentOne.setRepair(false);

        partOne.setId(1L);
        partOne.setDescription("Wheel");
        partOne.setBrandTypeYear("Seat Leon 2011");
        partOne.setPrice(BigDecimal.valueOf(100));

        repairOne.setFinding("Bad wheels");
        repairOne.setRepairAppointment(appointmentOne);

        parts.add(partOne);
    }

    @Test
    void getAllPartsToBeRepairedFromOneRepair() {

        when(repairPartService.getAllPartsToBeRepairedFromOneRepair(1L)).thenReturn(parts);

        repairPartController.getAllPartsToBeRepairedFromOneRepair(1L);

        verify(repairPartService, times(1)).getAllPartsToBeRepairedFromOneRepair(1L);
    }
}