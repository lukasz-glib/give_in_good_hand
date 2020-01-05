package pl.lg.charity.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.lg.charity.domain.entities.Donation;
import pl.lg.charity.domain.entities.User;
import pl.lg.charity.domain.repositories.DonationRepository;
import pl.lg.charity.domain.repositories.UserRepository;
import pl.lg.charity.dtos.DonationDataDTO;
import pl.lg.charity.services.DonationService;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;


@Service
@Transactional
public class DefaultDonationService implements DonationService {

    private final DonationRepository donationRepository;
    private final UserRepository userRepository;

    public DefaultDonationService(DonationRepository donationRepository, UserRepository userRepository) {
        this.donationRepository = donationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Integer findSumOfAllDonations() {
        return donationRepository.sumOfQuantities();
    }

    @Override
    public void addDonation(DonationDataDTO donationData) {
        ModelMapper modelMapper = new ModelMapper();
        Donation donation = modelMapper.map(donationData, Donation.class);
        donationRepository.save(donation);
    }

    @Override
    public List<Donation> findAllOrderedDonationsForUser(Principal principal) {
        User user = userRepository.findUserByEmail(principal.getName());
        return donationRepository.findAllOrderedDonationsForUser(user.getId());
    }

}
