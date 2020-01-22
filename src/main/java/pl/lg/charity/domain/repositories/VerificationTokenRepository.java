package pl.lg.charity.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lg.charity.domain.entities.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByUserId(Long id);

    VerificationToken findByToken(String token);
}
