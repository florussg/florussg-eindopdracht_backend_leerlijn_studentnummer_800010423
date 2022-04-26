package nl.florussg.eindopdracht_novi_backend_800010423.Repositories;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Iterable<Customer> findAllByLastnameContainingIgnoreCase(String lastname);

    Optional<Customer> findCustomerByBsnnumber(int bsnnumber);
}
