package pl.lg.charity.services;

import pl.lg.charity.domain.entities.Donation;
import pl.lg.charity.dtos.DonationDataDTO;

import java.security.Principal;
import java.util.List;

public interface DonationService {

    Integer findSumOfAllDonations();

    void addDonation(DonationDataDTO dataDTO);

    List<Donation>getOwnDonationsForUser(Principal principal);

    void deleteDonation(Long id);
}
