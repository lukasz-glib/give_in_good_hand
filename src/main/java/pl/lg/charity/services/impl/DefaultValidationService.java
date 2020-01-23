package pl.lg.charity.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.lg.charity.domain.repositories.UserRepository;
import pl.lg.charity.services.ValidationService;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class DefaultValidationService implements ValidationService {

    private final UserRepository userRepository;

    @Override
    public Boolean isUniqueEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }
}
