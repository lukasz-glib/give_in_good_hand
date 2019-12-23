package pl.lg.charity.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lg.charity.domain.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
