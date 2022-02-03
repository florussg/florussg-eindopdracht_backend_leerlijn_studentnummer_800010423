package nl.florussg.eindopdracht_novi_backend_800010423.Repositories;

import nl.florussg.eindopdracht_novi_backend_800010423.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

//Long of String needed?
public interface UserRepository extends JpaRepository<User, Long> {


}
