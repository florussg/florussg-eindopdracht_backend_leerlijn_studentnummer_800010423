package nl.florussg.eindopdracht_novi_backend_800010423.Repositories;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

//Long of String needed?
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    //Optional<User> findById(String username);
}
