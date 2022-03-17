package nl.florussg.eindopdracht_novi_backend_800010423.Repositories;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PartRepository extends JpaRepository<Part, Long> {

    //@Query(value= "select * from part where cast (brand_type_year as text) like '%:brandTypeYear%'", nativeQuery = true)
    List<Part> findPartByBrandTypeYearContaining(String brandTypeYear);

    //contains containing

}
