package pl.lg.charity.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.lg.charity.domain.entities.Donation;

import javax.transaction.Transactional;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query(value = "SELECT SUM(quantity) FROM charity_donation.donations;", nativeQuery = true)
    Integer sumOfQuantities();


}
