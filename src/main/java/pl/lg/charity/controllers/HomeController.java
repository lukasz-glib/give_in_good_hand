package pl.lg.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.lg.charity.services.DonationService;
import pl.lg.charity.services.InstitutionService;



@Controller
@RequestMapping("/")
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;

    public HomeController(InstitutionService institutionService, DonationService donationService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @GetMapping
    public String homeAction(Model model){
        model.addAttribute("allInstitutions", institutionService.findAllInstitutions());
        model.addAttribute("allBags", donationService.findSumOfAllDonations());
        return "index";
    }

}
