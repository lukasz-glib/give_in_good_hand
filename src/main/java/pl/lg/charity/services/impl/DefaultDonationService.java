package pl.lg.charity.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
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
@Slf4j
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
        User user = userRepository.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Donation donation = modelMapper.map(donationData, Donation.class);
        donation.setUser(user);
        log.debug("Zapis daru: {}", donation);
        donationRepository.save(donation);
        log.debug("Zapisano dar: {}", donation);
    }

    @Override
    public List<Donation> getOwnDonationsForUser(Principal principal) {
        return donationRepository.findAllByUserOrderByStatusDescPickUpDateAscAddingDateAsc(userRepository
                .findUserByEmail(principal.getName()));
    }

    @Override
    public void deleteDonation(Long id) {
        Donation donation = donationRepository.findById(id).get();
        log.debug("Usunięcie daru (zalogowanego użytkownika): {}", donation);
        if (donation != null && (!donation.getStatus())) {
            donationRepository.delete(donation);
        }
        log.debug("Dar został usunięty (zalogowanego użytkownika): {}", donation);
    }

    @Override
    public DonationDataDTO prepareUpdateDonationForUser(Long id) {
        Donation donation = donationRepository.findById(id).get();
        ModelMapper model = new ModelMapper();
        return model.map(donation, DonationDataDTO.class);
    }

    @Override
    public DonationDataDTO prepareDetailsOfUserDonation(Long id) {
        Donation detailDonation = donationRepository.getOne(id);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(detailDonation, DonationDataDTO.class);
    }

    @Override
    public void changeDonationStatus(Long id) {
        Donation donation = donationRepository.getOne(id);
        if (donation.getStatus()) {
            donationRepository.changeStatusToUndelivered(id);
        } else {
            donationRepository.changeStatusToDelivered(id);
        }
    }
}
