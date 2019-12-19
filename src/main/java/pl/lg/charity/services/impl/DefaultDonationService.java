package pl.lg.charity.services.impl;

import org.springframework.stereotype.Service;
import pl.lg.charity.domain.repositories.DonationRepository;
import pl.lg.charity.services.DonationService;

import javax.transaction.Transactional;

@Service
@Transactional
public class DefaultDonationService implements DonationService {

    private final DonationRepository donationRepository;

    public DefaultDonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public Long findSumOfAllDonations() {
        return donationRepository.sumOfQuantities();
    }
}
