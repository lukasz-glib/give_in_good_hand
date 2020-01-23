package pl.lg.charity.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.lg.charity.domain.entities.User;
import pl.lg.charity.domain.repositories.UserRepository;
import pl.lg.charity.dtos.DeleteAdminValidationDataDTO;
import pl.lg.charity.dtos.InstitutionDataDTO;
import pl.lg.charity.dtos.RegistrationDataDTO;
import pl.lg.charity.dtos.UpdateUserDataDTO;
import pl.lg.charity.services.DonationService;
import pl.lg.charity.services.InstitutionService;
import pl.lg.charity.services.RegistrationService;
import pl.lg.charity.services.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminAccountController {

    private final UserRepository userRepository;
    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final RegistrationService registrationService;
    private final UserService userService;

    public AdminAccountController(UserRepository userRepository, InstitutionService institutionService,
                                  DonationService donationService, RegistrationService registrationService,
                                  UserService userService) {
        this.userRepository = userRepository;
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.registrationService = registrationService;
        this.userService = userService;
    }

    @GetMapping
    public String prepareAccountPage(Principal principal, Model model) {
        String adminName = principal.getName();
        User loggedUser = userRepository.findByUsername(adminName);
        model.addAttribute("adminAccount", loggedUser);
        model.addAttribute("allInstitutions", institutionService.findAllInstitutions());
        model.addAttribute("allBags", donationService.findSumOfAllDonations());
        model.addAttribute("numberInstitutions", institutionService.numberOfAllInstitutions());
        return "admin/admin-account";
    }

    /**
     Institutions management
     */

    @GetMapping("/institutions")
    public String getInstitutionsPage(Model model) {
        model.addAttribute("allInstitutionsManagement", institutionService.findAllInstitutions());
        return "institution/all-institutions";
    }

    @GetMapping("/institutions/delete")
    public String processDeleteInstitution(InstitutionDataDTO institutionData, Long id) {
        institutionService.deleteInstitution(institutionData, id);
        return "redirect:/admin/institutions";
    }

    @GetMapping("/institutions/create")
    public String prepareCreationInstitutionForm(Model model) {
        model.addAttribute("institution", new InstitutionDataDTO());
        return "institution/add-institution";
    }

    @PostMapping("/institutions/create")
    public String processCreationInstitutionForm(@ModelAttribute("institution") @Valid InstitutionDataDTO
                                                 institutionData, BindingResult result) {
        if (result.hasErrors()) {
            return "institution/add-institution";
        }
        institutionService.addInstitution(institutionData);
        return "redirect:/admin/institutions";
    }

    @GetMapping("/institutions/update")
    public String prepareUpdateForInstitution(Long id, Model model) {
        model.addAttribute("institution", institutionService.prepareUpdateForInstitution(id));
        return "institution/update-institution";
    }

    @PostMapping("/institutions/update")
    public String processUpdateForInstitutions(@ModelAttribute("institution") @Valid InstitutionDataDTO
                                                           institutionDataDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "institution/update-institution";
        }
        institutionService.addInstitution(institutionDataDTO);
        return "redirect:/admin/institutions";
    }

    /**
     Administrators management
     */

    @GetMapping("/admins")
    public String getAdministratorsPage(Model model) {
        model.addAttribute("allAdminsManagement", registrationService.findAllAdmins());
        return "admin/all-admins";
    }

    @GetMapping("admins/delete")
    public String prepareDeleteAdmin(Model model, Long id) {
        model.addAttribute("deleteAdmin",registrationService.findAdminToDeleteById(id));
        return "admin/confirmation-delete-admins";
    }

    @PostMapping("admins/delete")
    public String processDeleteAdmin(@ModelAttribute("deleteAdmin") @Valid DeleteAdminValidationDataDTO dataDTO,
                                     BindingResult result, Long id) {
        if (result.hasErrors()) {
            return "admin/confirmation-delete-admins";
        }
        ModelMapper modelMapper = new ModelMapper();
        registrationService.deleteAdminOrUser(modelMapper.map(dataDTO, RegistrationDataDTO.class), id);
        return "redirect:/admin/admins";
    }

    @GetMapping("/admins/create")
    public String prepareCreationAdminAccount(Model model) {
        model.addAttribute("registrationAdmin", new RegistrationDataDTO());
        return "admin/add-admin";
    }

    @PostMapping("/admins/create")
    public String processCreationAdminAccount(@ModelAttribute("registrationAdmin") @Valid RegistrationDataDTO dataDTO,
                                              BindingResult result) {
        if (result.hasErrors()) {
            return "admin/add-admins";
        }
        registrationService.registerAdmin(dataDTO);
        return "redirect:/admin/admins";
    }

    @GetMapping("/admins/update")
    public String prepareUpdateAdminAccount(Model model, Long id) {
        model.addAttribute("updateAdmin", registrationService.prepareUpdateForAdminDataAccount(id));
        return "admin/update-admin";
    }

    @PostMapping("/admins/update")
    public String processUpdateAdminAccount(@ModelAttribute("updateAdmin") @Valid UpdateUserDataDTO dataDTO,
                                            BindingResult result) {
        if (result.hasErrors()) {
            return "admin/update-admin";
        }
        userService.processEditDataAdminsByAdmin(dataDTO);
        return "redirect:/admin/admins";
    }

    /**
     Users management
     */

    @GetMapping("/users")
    public String getUsersPage(Model model) {
        model.addAttribute("allUsersManagement", registrationService.findAllUsers());
        return "user/all-users";
    }

    @GetMapping("users/delete")
    public String processDeleteUser(RegistrationDataDTO registrationData, Long id) {
        registrationService.deleteAdminOrUser(registrationData, id);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/update")
    public String prepareUpdateUserAccount(Model model, Long id) {
        model.addAttribute("updateUser", registrationService.prepareUpdateForUserDataAccount(id));
        return "user/update-user";
    }

    @PostMapping("/users/update")
    public String processUpdateUserAccount(@ModelAttribute("updateUser") @Valid UpdateUserDataDTO dataDTO,
                                           BindingResult result) {
        if (result.hasErrors()) {
            return "user/update-user";
        }
        userService.processEditDataUsersByAdmin(dataDTO);
        return "redirect:/admin/users";
    }

    @GetMapping("users/unlock")
    public String statusActiveUser(Long id) {
        registrationService.unlockUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("users/lock")
    public String statusBlockedUser(Long id) {
        registrationService.lockUser(id);
        return "redirect:/admin/users";
    }
}
