package pl.lg.charity.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lg.charity.domain.entities.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long> {

}
