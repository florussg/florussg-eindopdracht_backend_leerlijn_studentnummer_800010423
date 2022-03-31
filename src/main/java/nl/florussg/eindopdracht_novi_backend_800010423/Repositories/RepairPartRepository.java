package nl.florussg.eindopdracht_novi_backend_800010423.Repositories;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.RepairPart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RepairPartRepository extends JpaRepository<RepairPart, Long> {

    List<RepairPart> findAllRepairPartsByRepair(long repairId);

}
