package pl.lg.charity.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.lg.charity.domain.entities.Donation;
import pl.lg.charity.domain.repositories.DonationRepository;
import pl.lg.charity.dtos.DonationDataDTO;
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
    public Integer findSumOfAllDonations() {
        return donationRepository.sumOfQuantities();
    }

    @Override
    public void addDonation(DonationDataDTO donationData) {
        ModelMapper modelMapper = new ModelMapper();
        Donation donation = modelMapper.map(donationData, Donation.class);
        donationRepository.save(donation);
    }

}
