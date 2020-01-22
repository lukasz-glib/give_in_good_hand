package pl.lg.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.lg.charity.domain.entities.VerificationToken;
import pl.lg.charity.dtos.RegistrationDataDTO;
import pl.lg.charity.services.RegistrationService;
import pl.lg.charity.services.VerificationTokenService;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final VerificationTokenService verificationTokenService;

    public RegistrationController(RegistrationService registrationService,
                                  VerificationTokenService verificationTokenService) {
        this.registrationService = registrationService;
        this.verificationTokenService = verificationTokenService;
    }

    @GetMapping
    public String getRegistrationPage (Model model) {
        model.addAttribute("registrationData", new RegistrationDataDTO());
        return "register/register";
    }

    @PostMapping
    public String processRegistrationPage(@ModelAttribute("registrationData")
                                          @Valid RegistrationDataDTO registrationData, BindingResult result){
        if (result.hasErrors()) {
            return "register/register";
        }
        registrationService.register(registrationData);
        return "register/confirm-registration";
    }

    @GetMapping("/confirmation")
    public String processConfirmRegistration(@RequestParam("token") String token) {
        VerificationToken verificationToken = verificationTokenService.findByToken(token);

        if (verificationToken != null) {
            registrationService.makeUsersStatusActive(verificationToken.getUser().getId());
        } else {
            return "failed-activation";
        }
        return "complete-activation";
    }
}
