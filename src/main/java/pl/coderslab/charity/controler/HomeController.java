package pl.coderslab.charity.controler;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Random;


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
    public String homeAction(Model model,
                             @RequestParam(required = false) Integer institutionPageNumber

    ) {
        // pagination handling code block - start
        int institutionPageSize = 10;
        if (institutionPageNumber == null) {
            model.addAttribute("previousPageNumber", 0);
            model.addAttribute("nextPageNumber", 1);
            institutionPageNumber = 0;
        }

        PageRequest pageRequest = PageRequest.of(institutionPageNumber, institutionPageSize);
        int numberOfLastPage = institutionRepository.findAll(pageRequest).getTotalPages();
        model.addAttribute("numberOfInstitutionPages", numberOfLastPage);
        model.addAttribute("institutions", institutionRepository.findAll(pageRequest).getContent());
        if (institutionPageNumber - 1 > 0) {
            model.addAttribute("previousPageNumber", institutionPageNumber - 1);
        } else {
            model.addAttribute("previousPageNumber",0);
        }
        if (institutionPageNumber + 1 < numberOfLastPage) {
            model.addAttribute("nextPageNumber", institutionPageNumber + 1);
        } else {
            model.addAttribute("nextPageNumber", numberOfLastPage-1);
        }
        // pagination handling code block - end

        model.addAttribute("totalQuantity", donationRepository.getTotalQuantity());
        model.addAttribute("donationCount", donationRepository.count());

        return "index";
    }


    @GetMapping("/addinstitution")
    public String addInstitution() {
        Institution institution = new Institution();
        String iName = "Fundation " + (new Random(System.currentTimeMillis())).nextInt(1000);
        institution.setName(iName);
        institution.setDescription(iName + " opis");
        institutionRepository.save(institution);

        int institutionPageSize = 10;
        PageRequest pageRequest = PageRequest.ofSize(institutionPageSize);
        int numberOfLastPage = institutionRepository.findAll(pageRequest).getTotalPages()-1;
        return "redirect:/?institutionPageNumber="+numberOfLastPage+"#help";
    }

//    @ModelAttribute("institutions")
//    public List<Institution> getAllInstitutions() {
//        return institutionRepository.findAll();
//    }


}