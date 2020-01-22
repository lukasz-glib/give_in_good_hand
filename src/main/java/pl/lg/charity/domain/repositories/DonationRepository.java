package pl.lg.charity.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.lg.charity.domain.entities.Donation;
import pl.lg.charity.domain.entities.User;

import javax.transaction.Transactional;
import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    List<Donation> findAllByUserEmail(String email);

    List<Donation> findAllByUserOrderByStatusDescPickUpDateAscAddingDateAsc(User user);

    @Query(value = "SELECT SUM(quantity) FROM donations;", nativeQuery = true)
    Integer sumOfQuantities();

    @Modifying
    @Transactional
    @Query(value = "UPDATE Donation d SET d.status = true WHERE d.id = ?1")
    void changeStatusToDelivered(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Donation d SET d.status = false WHERE d.id = ?1")
    void changeStatusToUndelivered(Long id);
}
