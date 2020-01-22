package pl.lg.charity.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.lg.charity.domain.entities.VerificationToken;
import pl.lg.charity.domain.repositories.VerificationTokenRepository;
import pl.lg.charity.services.VerificationTokenService;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class DefaultVerificationTokenService implements VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;

    public DefaultVerificationTokenService(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Override
    public VerificationToken findByToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }
}
