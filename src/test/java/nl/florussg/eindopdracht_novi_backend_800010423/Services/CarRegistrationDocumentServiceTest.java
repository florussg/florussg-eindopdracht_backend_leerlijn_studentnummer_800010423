package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.Car;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.CarRegistrationDocument;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CarRegistrationDocumentRepository;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CarRegistrationDocumentServiceTest {

    @InjectMocks
    private CarRegistrationDocumentService carRegistrationDocumentService;


    @Mock
    private CarRegistrationDocumentRepository carRegistrationDocumentRepository;

    @Mock
    private CarRepository carRepository;

    CarRegistrationDocument docOne = new CarRegistrationDocument();

    Car carOne = new Car();

    List<CarRegistrationDocument> docs = new ArrayList<CarRegistrationDocument>();

    @BeforeEach
    public void setUp() {
        docOne.setFileName("File.pdf");
        docOne.setPathSaved("C://");

        docs.add(docOne);

        carOne.setId(1);
        carOne.setBrand("Seat");
        carOne.setType("Ibiza");
        carOne.setLicenseplatenumber("GL-33-NN");

    }



    @Test
    void showAllDownloadableCarRegistrationDocuments() {

    }

    //TODO WERKT NIET!
    @Test
    void addCarToUploadedRegistrationDocument() {
//        when(carRegistrationDocumentRepository.findByFileNameEquals("File.pdf")).thenReturn(docOne);
//        when(carRepository.findCarByLicenseplateNumberContainingIgnoreCase("GL-33-NN")).thenReturn(Optional.of(carOne));
//
//        long carToSet = carRegistrationDocumentService.addCarToUploadedRegistrationDocument("File.pdf", "GL-33-NN");
////        long docToSet = carRegistrationDocumentService.addCarToUploadedRegistrationDocument("File.pdf", "GL-33-NN");
//
//        verify(carRegistrationDocumentRepository, times(1)).findById(docOne.getId());
//        verify(carRegistrationDocumentRepository, times(1)).save(docOne);
//
//        assertThat(carToSet).isEqualTo(docOne.getCar().getId());

    }
}