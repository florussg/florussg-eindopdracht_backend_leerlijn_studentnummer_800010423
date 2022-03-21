package nl.florussg.eindopdracht_novi_backend_800010423.Repositories;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.Repair;
import nl.florussg.eindopdracht_novi_backend_800010423.Models.RepairStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepairRepository extends JpaRepository<Repair, Long> {

    //List<Repair> getAllRepairsByRepairStatusContaining (String repairStatus);
    List<Repair> findRepairByRepairStatusContaining (String repairStatus);


}
