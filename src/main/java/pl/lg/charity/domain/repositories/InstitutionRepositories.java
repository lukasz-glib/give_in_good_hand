package pl.lg.charity.domain.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.lg.charity.domain.entities.Institution;

public interface InstitutionRepositories extends JpaRepository<Institution, Long> {

    @Query(value = "SELECT MAX(id) FROM charity_donation.institutions", nativeQuery = true)
    Integer numberOfAllInstitutions();

}
