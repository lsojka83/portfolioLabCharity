package pl.coderslab.charity.controler;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.model.CurrentUser;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/form")
public class DonationController {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;


    public DonationController(InstitutionRepository institutionRepository, DonationRepository donationRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public String getForm(Model model,
                          @RequestParam(required = false) Integer institutionPageNumber,
                          @AuthenticationPrincipal CurrentUser customUser

                          ) {

        Donation donation = new Donation();
        User user = customUser.getUser();
        donation.setStreet(user.getStreet());
        donation.setCity(user.getCity());
        donation.setZipCode(user.getZipCode());
        donation.setPhoneNumber(user.getPhoneNumber());
        model.addAttribute("donation",donation);

        return "form";
    }

    @PostMapping("")
    public String saveFormData(@Valid Donation donation,
                               BindingResult bindingResult,
                               @AuthenticationPrincipal CurrentUser customUser,
                               Model model,
                               @RequestParam(required = false) Integer institutionPageNumber
    ) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        donationRepository.save(donation);
        User user = userRepository.findByEmail(customUser.getUser().getEmail());
        user.getDonations().add(donation);
        userRepository.save(user);

        return "form-confirmation";
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryRepository.findAll();
    }




}
