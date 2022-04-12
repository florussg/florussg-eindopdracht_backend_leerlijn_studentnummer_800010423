package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.Part;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.PartRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PartServiceTest {

    @InjectMocks
    private PartService partService;

    @Mock
    private PartRepository partRepository;

    Part partOne = new Part();
    Part partTwo = new Part();


    @Test
    void getAllParts() {
    }

    @Test
    void getAllPartsByBrandTypeYear() {
    }

    @Test
    void addNewPart() {
    }

    @Test
    void deletePart() {
    }

    @Test
    void editPart() {
    }
}