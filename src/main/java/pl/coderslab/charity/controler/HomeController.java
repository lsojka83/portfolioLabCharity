package pl.coderslab.charity.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;


@Controller
public class HomeController {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final CategoryRepository categoryRepository;

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository, CategoryRepository categoryRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
    }


    @RequestMapping("/")
    public String homeAction(Model model){

        model.addAttribute("institutionPairs", institutionRepository.findAllInPaires());
        model.addAttribute("totalQuantity",donationRepository.getTotalQuantity());
        model.addAttribute("donationCount",donationRepository.count());
        return "index";
    }

    @ModelAttribute("institutions")
    public List<Institution> getAllInstitutions()
    {
        return institutionRepository.findAll();
    }
}
