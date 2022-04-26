package nl.florussg.eindopdracht_novi_backend_800010423.Controllers;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.CarRegistrationDocument;
import nl.florussg.eindopdracht_novi_backend_800010423.Services.CarRegistrationDocumentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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


}