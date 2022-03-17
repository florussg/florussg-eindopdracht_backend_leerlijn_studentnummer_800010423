package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.BadRequestException;
import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.RecordNotFoundException;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Part;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.CarRepository;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartService {

    @Autowired
    private PartService partService;

    @Autowired
    private PartRepository partRepository;

    public Iterable<Part> getAllParts() {

        List<Part> all = partRepository.findAll();
        return all;
    }

    public List<Part> getAllPartsByBrandTypeYear (String userInput) {
        List<Part> all = partRepository.findPartByBrandTypeYearContaining(userInput);

        if (all.size() > 0) {
            return all;
        } else {
            throw new RecordNotFoundException("No parts found based on your user input");
        }
    }

    public long addNewPart (Part part) {

        Optional<Part> optionalPart = partRepository.findPartByDescription(part.getDescription());
        if (optionalPart.isPresent()) {
            throw new BadRequestException("This part already exists!");
        } else {
            Part savePart = partRepository.save(part);
            return savePart.getId();
        }
    }











}
