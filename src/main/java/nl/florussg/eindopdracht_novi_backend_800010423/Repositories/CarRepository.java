package nl.florussg.eindopdracht_novi_backend_800010423.Repositories;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findCarByLicenseplateNumberContainingIgnoreCase(String licenseplateNumber);
    Optional<Car> deleteCarByLicenseplateNumber(String licenseplateNumber);
}
