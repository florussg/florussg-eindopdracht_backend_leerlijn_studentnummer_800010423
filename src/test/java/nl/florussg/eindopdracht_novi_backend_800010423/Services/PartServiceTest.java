package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.RecordNotFoundException;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Part;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.PartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PartServiceTest {

    @InjectMocks
    private PartService partService;

    @Mock
    private PartRepository partRepository;

    Part partOne = new Part();
    Part partTwo = new Part();

    List<Part> parts = new ArrayList<>();

    @BeforeEach
    public void setUp() {

        partOne.setId(1L);
        partOne.setDescription("Wheel");
        partOne.setBrandTypeYear("Seat Leon 2011");
        partOne.setPrice(BigDecimal.valueOf(100));

        partTwo.setId(2L);
        partTwo.setDescription("Brake");
        partTwo.setBrandTypeYear("Toyota Yaris 2001");
        partTwo.setPrice(BigDecimal.valueOf(40));

        parts.add(partOne);
    }


    @Test
    void getAllParts() {
        when(partService.getAllParts()).thenReturn(parts);

        Iterable<Part> foundParts = partService.getAllParts();

        verify(partRepository, times(1)).findAll();

        assertThat(foundParts).isEqualTo(parts);
    }

    @Test
    void getAllPartsByBrandTypeYear() {
        when(partRepository.findPartByBrandTypeYearContainingIgnoreCase("Seat Leon 2011")).thenReturn(parts);

        List<Part> foundParts = partService.getAllPartsByBrandTypeYear("Seat Leon 2011");

        verify(partRepository, times(1)).findPartByBrandTypeYearContainingIgnoreCase("Seat Leon 2011");

        assertThat(foundParts).isEqualTo(parts);
    }

    @Test
    void getAllPartsByBrandTypeYearException() {
        Exception exception = assertThrows(RecordNotFoundException.class, () -> partService.getAllPartsByBrandTypeYear("Mercedes"));

        String expectedMessage = "No parts found based on your user input";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void addNewPart() {
        when(partRepository.save(ArgumentMatchers.any(Part.class))).thenReturn(partOne);

        Part partAdded = partRepository.save(partOne);
        long partCreatedId = partService.addNewPart(partOne);

        assertThat(partCreatedId).isSameAs(partAdded.getId());
    }

    @Test
    void deletePart() {
        when(partRepository.findById(1L)).thenReturn(Optional.of(partOne));

        partService.deletePart(1L);

        verify(partRepository).deleteById(1L);
    }

    @Test
    void deletePartException() {
        assertThrows(RecordNotFoundException.class, () -> partService.deletePart(1000L));
    }

    @Test
    void editPart() {
        when(partRepository.findById(1L)).thenReturn(Optional.ofNullable(partOne));
        when(partRepository.save(partOne)).thenReturn(partOne);

        Part partToEdit = partService.editPart(1L, partOne);

        verify(partRepository, times(1)).findById(1L);
        verify(partRepository, times(1)).save(partOne);

        assertThat(partToEdit.getId()).isEqualTo(partOne.getId());
    }

    @Test
    void editPartException() {
        Exception exception = assertThrows(RecordNotFoundException.class, () -> partService.editPart(1000L, partOne));

        String expectedMessage = "There is no part with this id";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

}