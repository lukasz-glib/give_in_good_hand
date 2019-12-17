package pl.lg.charity.domain.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.lg.charity.domain.entities.Institution;

public interface InstitutionRepositories extends JpaRepository<Institution, Long> {

}
