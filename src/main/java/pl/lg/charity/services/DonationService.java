package pl.lg.charity.services;


import pl.lg.charity.dtos.DonationDataDTO;

public interface DonationService {

    Integer findSumOfAllDonations();

    void addDonation(DonationDataDTO dataDTO);
}
