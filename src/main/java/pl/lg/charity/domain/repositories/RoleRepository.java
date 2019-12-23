package pl.lg.charity.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lg.charity.domain.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getByName(String name);
}
