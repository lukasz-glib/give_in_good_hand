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
import pl.lg.charity.dtos.DonationDataDTO;
import pl.lg.charity.dtos.RegistrationDataDTO;
import pl.lg.charity.dtos.UpdateUserDataDTO;
import pl.lg.charity.dtos.UpdateUserPasswordDataDTO;
import pl.lg.charity.services.CategoryService;
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
    private final CategoryService categoryService;

    public UserAccountController(UserRepository userRepository, InstitutionService institutionService,
                                 DonationService donationService, UserService userService, CategoryService categoryService) {
        this.userRepository = userRepository;
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userService = userService;
        this.categoryService = categoryService;
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
    public String processEditDataUserPage(@ModelAttribute("editDataLoggedUser") @Valid UpdateUserDataDTO dataDTO,
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
    public String processEditPasswordUserPage(UpdateUserPasswordDataDTO dataDTO, Principal principal, HttpServletRequest req)
                                              throws ServletException {
        userService.processEditPasswordUser(dataDTO, principal, req);
        return "redirect:/login";
    }

    /**
     User's donations management
     */

    @GetMapping("/myDonations")
    public String getUserDonationsPage(Model model, Principal principal) {
        model.addAttribute("allUserDonations", donationService.getOwnDonationsForUser(principal));
        return "user/all-user-donations";
    }

    @GetMapping("/myDonations/delete")
    public String processDeleteUsersDonation(Long id) {
        donationService.deleteDonation(id);
        return "redirect:/user/myDonations";
    }

    @GetMapping("/myDonations/update")
    public String prepareUpdateUsersDonation(Long id, Model model) {
        model.addAttribute("editDonations", donationService.prepareUpdateDonationForUser(id));
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("institutions", institutionService.findAllInstitutions());
        return "user/edit-user-donation";
    }

    @PostMapping("myDonations/update")
    public String processUpdateUsersDonation(@ModelAttribute("editDonations") @Valid DonationDataDTO donationData,
                                             BindingResult result) {
        if (result.hasErrors()) {
            return "user/edit-user-donation";
        }
        donationService.addDonation(donationData);
        return "redirect:/user/myDonations";
    }

    @GetMapping("myDonations/details")
    public String prepareDetailsOfUserDonation(Model model, Long id) {
        model.addAttribute("institutionName", donationService.prepareDetailsOfUserDonation(id).getInstitution().getName());
        model.addAttribute("pickUpDate", donationService.prepareDetailsOfUserDonation(id).getPickUpDate());
        model.addAttribute("pickUpTime", donationService.prepareDetailsOfUserDonation(id).getPickUpTime());
        model.addAttribute("status", donationService.prepareDetailsOfUserDonation(id).getStatus());
        model.addAttribute("id", donationService.prepareDetailsOfUserDonation(id).getId());
        return "user/details-user-donation";
    }

    @GetMapping("myDonations/changeStatus")
    public String changingUserDonationStatus(Long id) {
        donationService.changeDonationStatus(id);
        return "redirect:/user/myDonations";
    }
}
