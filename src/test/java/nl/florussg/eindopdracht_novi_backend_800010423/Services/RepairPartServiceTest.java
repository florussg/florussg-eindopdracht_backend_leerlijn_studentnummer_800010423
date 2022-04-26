package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.RecordNotFoundException;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.*;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.PartRepository;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.RepairPartRepository;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.RepairRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RepairPartServiceTest {

    @InjectMocks
    private RepairPartService repairPartService;

    @Mock
    private RepairPartRepository repairPartRepository;

    @Mock
    private PartRepository partRepository;

    @Mock
    private RepairRepository repairRepository;

    RepairPart repairPartOne = new RepairPart();

    Repair repairOne = new Repair();

    Appointment appointment = new Appointment();
    LocalDateTime datetime1 =  LocalDateTime.of(2022, 5, 25, 12, 00, 00);

    Part part = new Part();

    List<RepairPart> repairParts = new ArrayList<>();
    List<Part> parts = new ArrayList<>();

    @BeforeEach
    public void setUp() {

        appointment.setId(1L);
        appointment.setDateTimeAppointment(datetime1);
        appointment.setApk(true);
        appointment.setRepair(false);

        part.setId(1L);
        part.setDescription("Wheel");
        part.setBrandTypeYear("Seat Leon 2011");
        part.setPrice(BigDecimal.valueOf(100));

        repairOne.setId(1L);
        repairOne.setFinding("Bad wheel");
        repairOne.setRepairAppointment(appointment);

        repairPartOne.setPart(part);
        repairPartOne.setRepair(repairOne);
        repairPartOne.setAmount(1);

        repairParts.add(repairPartOne);
        parts.add(part);
    }

    @Test
    void addPartToRepair() {
        when(repairRepository.findById(1L)).thenReturn(Optional.ofNullable(repairOne));
        when(partRepository.findById(1L)).thenReturn(Optional.ofNullable(part));
        when(repairPartRepository.save(ArgumentMatchers.any(RepairPart.class))).thenReturn(repairPartOne);

        repairPartRepository.save(repairPartOne);
        RepairPartKey id = repairPartService.addPartToRepair(1L, 1L, 1);

        assertThat(id.getPartId()).isEqualTo(1L);
    }

    @Test
    void addPartToRepairExceptionOne() {
        assertThrows(RecordNotFoundException.class, () -> repairPartService.addPartToRepair(1000L, 1L, 1));
    }

    @Test
    void addPartToRepairExceptionTwo() {
        when(repairRepository.findById(1L)).thenReturn(Optional.ofNullable(repairOne));

        Exception exception = assertThrows(RecordNotFoundException.class, () -> repairPartService.addPartToRepair(1L, 1000L, 1));

        String expectedMessage = "No part with this id found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getAllPartsToBeRepairedFromOneRepair() {
        when(repairPartRepository.findAllByRepairId(1L)).thenReturn(repairParts);

        List<Part> foundParts = repairPartService.getAllPartsToBeRepairedFromOneRepair(1L);
        verify(repairPartRepository, times(1)).findAllByRepairId(1L);

        assertThat(foundParts).isEqualTo(parts);
    }

    @Test
    void getAllPartsToBeRepairedFromOneRepairException() {
        assertThrows(RecordNotFoundException.class, () -> repairPartService.getAllPartsToBeRepairedFromOneRepair(1000L));
    }

}