package pl.lg.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.lg.charity.domain.entities.User;
import pl.lg.charity.domain.repositories.UserRepository;
import pl.lg.charity.dtos.RegistrationDataDTO;
import pl.lg.charity.services.DonationService;
import pl.lg.charity.services.InstitutionService;
import pl.lg.charity.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserAccountController {

    private final UserRepository userRepository;
    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final UserService userService;

    public UserAccountController(UserRepository userRepository, InstitutionService institutionService,
                                 DonationService donationService, UserService userService) {
        this.userRepository = userRepository;
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userService = userService;
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

    /**
     User's data and password management
     */

    @GetMapping("/edit")
    public String prepareUserEditButtonsPage() {
        return "user/edit-data-buttons-by-user";
    }

    @GetMapping("/edit/changeData")
    public String prepareEditDataUserPage(Principal principal, Model model) {
        model.addAttribute("editDataLoggedUser", userService.prepareEditDataUser(principal));
        return "user/change-data-user";
    }

    @PostMapping("/edit/changeData")
    public String processEditDataUserPage(@ModelAttribute("editDataLoggedUser") @Valid RegistrationDataDTO dataDTO,
                                          BindingResult result, Principal principal, HttpServletRequest req)
                                          throws ServletException {
        if (result.hasErrors()) {
            return "user/change-data-user";
        }
        userService.processEditDataUser(dataDTO, principal, req);
        return "redirect:/login";
    }

    @GetMapping("/edit/changePassword")
    public String prepareEditPasswordUserPage(Principal principal, Model model) {
        model.addAttribute("editPasswordLoggedUser", userService.prepareEditPasswordUser(principal));
        return "user/change-password-user";
    }

    @PostMapping("/edit/changePassword")
    public String processEditPasswordUserPage(RegistrationDataDTO dataDTO, Principal principal, HttpServletRequest req)
                                              throws ServletException {
        userService.processEditPasswordUser(dataDTO, principal, req);
        return "redirect:/login";
    }

    /**
     User's donations management
     */

    @GetMapping("/myDonations")
    public String getUserDonationsPage(Model model, Principal principal) {
        model.addAttribute("allUserDonations", donationService.findAllOrderedDonationsForUser(principal));
        return "user/all-user-donations";
    }
}
