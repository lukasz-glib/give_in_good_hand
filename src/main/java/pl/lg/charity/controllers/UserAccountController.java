package pl.lg.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.lg.charity.domain.entities.User;
import pl.lg.charity.domain.repositories.UserRepository;
import pl.lg.charity.services.DonationService;
import pl.lg.charity.services.InstitutionService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserAccountController {

    private final UserRepository userRepository;
    private final InstitutionService institutionService;
    private final DonationService donationService;

    public UserAccountController(UserRepository userRepository, InstitutionService institutionService,
                                 DonationService donationService) {
        this.userRepository = userRepository;
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @GetMapping
    public String prepareAccountPage(Principal principal, Model model) {
        String username = principal.getName();
        User loggedUser = userRepository.findByUsername(username);
        model.addAttribute("userAccount", loggedUser);
        model.addAttribute("allInstitutions", institutionService.findAllInstitutions());
        model.addAttribute("allBags", donationService.findSumOfAllDonations());
        model.addAttribute("numberInstitutions", institutionService.numberOfAllInstitutions());
        return "user/account";
    }

    @GetMapping("/edit")
    public String prepareUserDataEditPage() {
        return "user/edit-data-by-user";
    }
}
