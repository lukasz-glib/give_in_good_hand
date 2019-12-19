package pl.lg.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/donation")
public class DonationController {

    @GetMapping
    public String prepareDonationView() {
        return "donation/add-donation";
    }
}
