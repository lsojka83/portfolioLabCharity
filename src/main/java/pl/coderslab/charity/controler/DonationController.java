package pl.coderslab.charity.controler;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/form")
public class DonationController {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final CategoryRepository categoryRepository;

    public DonationController(InstitutionRepository institutionRepository, DonationRepository donationRepository, CategoryRepository categoryRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("")
    public String getForm(Model model,
                          @RequestParam(required = false) Integer institutionPageNumber
    )
    {
        model = paginationSetup(model, institutionPageNumber);
        model.addAttribute("donation", new Donation());

        return "form";
    }

    @PostMapping("")
    public String saveFormData(@Valid Donation donation,
                             BindingResult bindingResult,
                               Model model,
                               @RequestParam(required = false) Integer institutionPageNumber
    )
    {
        model = paginationSetup(model, institutionPageNumber);

        if(bindingResult.hasErrors())
        {
//            model.addAttribute("categories", categoryRepository.findAll());
//            model.addAttribute("institutions", institutionRepository.findAll());
            return "form";
        }
        donationRepository.save(donation);
        return "form-confirmation";
    }

    private Model paginationSetup(Model model,Integer institutionPageNumber)
    {
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

        return model;
    }

    @ModelAttribute("categories")
    public List<Category> categories()
    {
        return categoryRepository.findAll();
    }


}
