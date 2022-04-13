package nl.florussg.eindopdracht_novi_backend_800010423.Controllers;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.Car;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Part;
import nl.florussg.eindopdracht_novi_backend_800010423.Services.CarService;
import nl.florussg.eindopdracht_novi_backend_800010423.Services.PartService;
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
class PartControllerTest {

    @InjectMocks
    PartController partController;

    @Mock
    PartService partService;

    Part partOne = new Part();
    Part partTwo = new Part();

    List<Part> parts = new ArrayList<>();

    @BeforeEach
    public void setUp() {

        partOne.setId(1L);
        partOne.setDescription("Wheel");
        partOne.setPrice(BigDecimal.valueOf(10));
        partOne.setBrandTypeYear("Seat Leon 2022");

        partTwo.setId(2L);
        partTwo.setDescription("Brake");
        partTwo.setPrice(BigDecimal.valueOf(20));
        partTwo.setBrandTypeYear("Toyota Yaris 2011");

        parts.add(partOne);
        parts.add(partTwo);
    }


    @Test
    void getAllParts() {

        when(partService.getAllParts()).thenReturn(parts);

        partController.getAllParts();

        verify(partService, times(1)).getAllParts();
    }

    @Test
    void getAllPartsByBrandTypeYear() {

        when(partService.getAllPartsByBrandTypeYear("Seat")).thenReturn(parts);

        partController.getAllPartsByBrandTypeYear("Seat");

        verify(partService, times(1)).getAllPartsByBrandTypeYear("Seat");
    }

    @Test
    void addNewPart() {
    }

    @Test
    void deletePart() {

        partController.deletePart(1L);
        verify(partService, times(1)).deletePart(1L);
    }

    @Test
    void editPart() {
    }
}