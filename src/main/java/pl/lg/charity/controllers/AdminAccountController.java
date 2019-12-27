package pl.lg.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.lg.charity.domain.entities.User;
import pl.lg.charity.domain.repositories.UserRepository;
import pl.lg.charity.services.DonationService;
import pl.lg.charity.services.InstitutionService;
import pl.lg.charity.services.RegistrationService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminAccountController {

    private final UserRepository userRepository;
    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final RegistrationService registrationService;

    public AdminAccountController(UserRepository userRepository, InstitutionService institutionService, DonationService donationService, RegistrationService registrationService) {
        this.userRepository = userRepository;
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.registrationService = registrationService;
    }

    @GetMapping
    public String prepareAccountPage(Principal principal, Model model) {
        String adminname = principal.getName();
        User loggedUser = userRepository.findByUsername(adminname);
        model.addAttribute("adminAccount", loggedUser);
        model.addAttribute("allInstitutions", institutionService.findAllInstitutions());
        model.addAttribute("allBags", donationService.findSumOfAllDonations());
        model.addAttribute("numberInstitutions", institutionService.numberOfAllInstitutions());
        return "admin/admin-account";
    }

}
