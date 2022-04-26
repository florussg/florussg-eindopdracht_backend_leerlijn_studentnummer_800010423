package nl.florussg.eindopdracht_novi_backend_800010423.Repositories;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.CarRegistrationDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface CarRegistrationDocumentRepository extends JpaRepository<CarRegistrationDocument, Long> {

    CarRegistrationDocument findByFileNameEquals(String fileName);
}
