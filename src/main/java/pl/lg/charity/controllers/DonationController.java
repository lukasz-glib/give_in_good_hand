package pl.lg.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.lg.charity.dtos.DonationDataDTO;
import pl.lg.charity.services.CategoryService;
import pl.lg.charity.services.DonationService;
import pl.lg.charity.services.InstitutionService;

import javax.validation.Valid;


@Controller
@RequestMapping("/donation")
public class DonationController {

    private final DonationService donationService;
    private final CategoryService categoryService;
    private final InstitutionService institutionService;

    public DonationController(DonationService donationService, CategoryService categoryService,
                              InstitutionService institutionService) {
        this.donationService = donationService;
        this.categoryService = categoryService;
        this.institutionService = institutionService;
    }

    @GetMapping("/create")
    public String prepareDonationFormView(Model model) {
        model.addAttribute("donations", new DonationDataDTO());
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("institutions", institutionService.findAllInstitutions());
        return "donation/add-donation";
    }

    @PostMapping("/create")
    public String processCreationDonationForm(@ModelAttribute("donations") @Valid DonationDataDTO donationData,
                                              BindingResult result) {
        if (result.hasErrors()) {
            return "donation/add-donation";
        }
        donationService.addDonation(donationData);
        return "donation/form-confirmation";
    }

}
