package pl.lg.charity.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.lg.charity.domain.entities.Donation;

import javax.transaction.Transactional;
import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query(value = "SELECT SUM(quantity) FROM charity_donation.donations;", nativeQuery = true)
    Integer sumOfQuantities();

    @Query(value = "SELECT * FROM charity_donation.donations WHERE user_id = ? ORDER BY pick_up_date DESC;",
            nativeQuery = true)
    List<Donation> findAllOrderedDonationsForUser(Long id);

}
