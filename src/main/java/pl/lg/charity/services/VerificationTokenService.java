package pl.lg.charity.services;

import pl.lg.charity.domain.entities.VerificationToken;

public interface VerificationTokenService {

    VerificationToken findByToken(String token);
}
