package pl.lg.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.lg.charity.services.InstitutionService;



@Controller
@RequestMapping("/")
public class HomeController {

    private final InstitutionService institutionService;

    public HomeController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @GetMapping
    public String homeAction(Model model){
        model.addAttribute("allInstitutions", institutionService.findAllInstitutions());
        return "index";
    }

}
