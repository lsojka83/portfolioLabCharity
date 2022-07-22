package pl.coderslab.charity.controler;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.model.CurrentUser;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.service.UserService;


import java.util.Random;

@Controller
@RequestMapping("/")
public class HomeController {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final RoleRepository roleRepository;

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository, CategoryRepository categoryRepository, UserService userService, RoleRepository roleRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
        this.userService = userService;
        this.roleRepository = roleRepository;
    }


    @RequestMapping("")
    public String homeAction(Model model,
                             @RequestParam(required = false) Integer institutionPageNumber,
                             @AuthenticationPrincipal CurrentUser customUser

                             )
    {

        // pagination handling code block - start
        int institutionPageSize = 6;
        if (institutionPageNumber == null) {
            model.addAttribute("previousPageNumber", 0);
            model.addAttribute("nextPageNumber", 1);
            institutionPageNumber = 0;
        }

        PageRequest pageRequest = PageRequest.of(institutionPageNumber, institutionPageSize);
        Page<Institution> page = institutionRepository.findAll(pageRequest);
        model.addAttribute("page", page);
        if(customUser != null) {
            if(customUser.hasRole("ROLE_ADMIN"))        {
            return "redirect:/admin";
        }
//            model.addAttribute("user", customUser);
            User user =  userService.findById(customUser.getUser().getId());
            model.addAttribute("totalQuantity",user.getDonations().stream()
                    .map(d -> d.getQuantity())
                    .reduce(Integer::sum).orElse(0)
            );
            model.addAttribute("donationCount", user.getDonations().size());
        }
        else {
            model.addAttribute("totalQuantity", donationRepository.getTotalQuantity().orElse(0));
            model.addAttribute("donationCount", donationRepository.count());
        }
        //Option 2
//        int numberOfLastPage = page.getTotalPages();
//        model.addAttribute("numberOfInstitutionPages", numberOfLastPage);
//        model.addAttribute("institutions", page.getContent());
//        if (institutionPageNumber - 1 > 0) {
//            model.addAttribute("previousPageNumber", institutionPageNumber - 1);
//        } else {
//            model.addAttribute("previousPageNumber",0);
//        }
//        if (institutionPageNumber + 1 < numberOfLastPage) {
//            model.addAttribute("nextPageNumber", institutionPageNumber + 1);
//        } else {
//            model.addAttribute("nextPageNumber", numberOfLastPage-1);
//        }
        // pagination handling code block - end



        return "index";
    }


    @GetMapping("addinstitution")
    public String addInstitution() {
        Institution institution = new Institution();
        String iName = "Fundation " + (new Random(System.currentTimeMillis())).nextInt(1000);
        institution.setName(iName);
        institution.setDescription(iName + " opis");
        institutionRepository.save(institution);

        int institutionPageSize = 10;
        PageRequest pageRequest = PageRequest.ofSize(institutionPageSize);
        int numberOfLastPage = institutionRepository.findAll(pageRequest).getTotalPages()-1;
        return "redirect:?institutionPageNumber="+numberOfLastPage+"#help";
    }

}
