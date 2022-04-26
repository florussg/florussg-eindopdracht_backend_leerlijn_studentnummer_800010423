package nl.florussg.eindopdracht_novi_backend_800010423.Repositories;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PartRepository extends JpaRepository<Part, Long> {

    List<Part> findPartByBrandTypeYearContainingIgnoreCase(String brandTypeYear);

    Optional<Part> findPartByDescription (String description);
}
