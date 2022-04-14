package nl.florussg.eindopdracht_novi_backend_800010423.Controllers;

import nl.florussg.eindopdracht_novi_backend_800010423.EindopdrachtNoviBackend800010423Application;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Car;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.CarRegistrationDocument;
import nl.florussg.eindopdracht_novi_backend_800010423.Services.CarRegistrationDocumentService;
import nl.florussg.eindopdracht_novi_backend_800010423.Services.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@ExtendWith({MockitoExtension.class})
class CarRegistrationDocumentControllerTest {

    @InjectMocks
    CarRegistrationDocumentController carRegistrationDocumentController;

    @Mock
    CarRegistrationDocumentService carRegistrationDocumentService;

    CarRegistrationDocument docOne = new CarRegistrationDocument();

    List<String> docs = new ArrayList<>();

    @BeforeEach
    public void setUp() {

        docOne.setFileName("bestand.pdf");
        docOne.setPathSaved("C://");

        String filename = docOne.getFileName();
        docs.add(filename);
    }

    @Test
    void showAllCarRegistrationDocumentSavedToFileSystem() {

        when(carRegistrationDocumentService.showAllDownloadableCarRegistrationDocuments()).thenReturn(docs);

        HttpServletRequest request = null;
        carRegistrationDocumentController.showAllCarRegistrationDocumentSavedToFileSystem(request);

        verify(carRegistrationDocumentService, times(1)).showAllDownloadableCarRegistrationDocuments();

    }

    @Test
    void singleUpload() {

    }


    @Test
    void downloadCarRegistrationDocument() {
    }

    @Test
    void setCarToUploadedRegistrationDocument() {
    }
}