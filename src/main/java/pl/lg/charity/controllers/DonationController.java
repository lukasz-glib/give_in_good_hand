package pl.lg.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.lg.charity.dtos.DonationDataDTO;
import pl.lg.charity.services.CategoryService;
import pl.lg.charity.services.DonationService;


@Controller
@RequestMapping("/donation")
public class DonationController {

    private final DonationService donationService;
    private final CategoryService categoryService;

    public DonationController(DonationService donationService, CategoryService categoryService) {
        this.donationService = donationService;
        this.categoryService = categoryService;
    }

    @GetMapping("/create")
    public String prepareDonationFormView(Model model) {
        model.addAttribute("donations", new DonationDataDTO());
        model.addAttribute("categories", categoryService.findAllCategories());
        return "donation/add-donation";
    }


}
