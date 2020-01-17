package pl.lg.charity.domain.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.lg.charity.domain.entities.Institution;

import javax.transaction.Transactional;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    @Query(value = "SELECT MAX(id) FROM institutions", nativeQuery = true)
    Integer numberOfAllInstitutions();

    /**
    Below - queries for heroku
     */

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO institutions(description, name) VALUES " +
            "('Cel i misja: Pomoc dzieciom z ubogich rodzin', 'Fundacja Dbam O Zdrowie')", nativeQuery = true)
    void createFirstInstitutionToHeroku();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO institutions(description, name) VALUES " +
            "('Cel i misja: Pomoc wybudzaniu dzieci ze śpiączki', 'Fundacja A Kogo')", nativeQuery = true)
    void createSecondInstitutionToHeroku();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO institutions(description, name) VALUES " +
            "('Cel i misja: Pomoc osobom znajdującym się w trudnej sytuacji życiowej.', 'Fundacja Dla Dzieci')",
            nativeQuery = true)
    void createThirdInstitutionToHeroku();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO institutions(description, name) VALUES " +
            "('Cel i misja: Pomoc dla osób nie posiadających miejsca zamieszkania', 'Fundacja Bez Domu')",
            nativeQuery = true)
    void createFourthInstitutionToHeroku();
}
