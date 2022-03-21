package nl.florussg.eindopdracht_novi_backend_800010423.Services;

import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.BadRequestException;
import nl.florussg.eindopdracht_novi_backend_800010423.Exceptions.RecordNotFoundException;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.Part;
import nl.florussg.eindopdracht_novi_backend_800010423.Repositories.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartService {
    

    @Autowired
    private PartRepository partRepository;

    public Iterable<Part> getAllParts() {

        List<Part> all = partRepository.findAll();
        return all;
    }

    public List<Part> getAllPartsByBrandTypeYear (String userInput) {
        List<Part> all = partRepository.findPartByBrandTypeYearContainingIgnoreCase(userInput);

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

    public void deletePart (long id) {
        Optional<Part> optionalPart = partRepository.findById(id);
        if (optionalPart.isPresent()) {
            partRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("There is no part with this id");
        }
    }

    public Part editPart (long id, Part part) {
        Optional<Part> optionalPart = partRepository.findById(id);
        if(optionalPart.isPresent()) {
            Part partToEdit = optionalPart.get();

            partToEdit.setDescription(part.getDescription());
            partToEdit.setBrandTypeYear(part.getBrandTypeYear());
            partToEdit.setPrice(part.getPrice());

            partRepository.save(partToEdit);

        } else {
            throw new RecordNotFoundException("There is no part with this id");
        }
        return part;
    }











}
