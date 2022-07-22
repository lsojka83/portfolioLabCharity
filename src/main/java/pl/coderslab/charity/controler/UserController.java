package pl.coderslab.charity.controler;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.*;
import pl.coderslab.charity.model.CurrentUser;
import pl.coderslab.charity.model.Messages;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.StatusRepository;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final StatusRepository statusRepository;
    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    public UserController(UserService userService, BCryptPasswordEncoder passwordEncoder, StatusRepository statusRepository, CategoryRepository categoryRepository, InstitutionRepository institutionRepository, DonationRepository donationRepository) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.statusRepository = statusRepository;
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }

    @GetMapping("/edit")
    public String userEditForm() {
        return "user-edit";
    }

    @PostMapping("/edit")
    public String userSave(@Valid User user, BindingResult bindingResult,
                           @RequestParam String password,
                           @RequestParam String password2,
//                           @AuthenticationPrincipal CurrentUser currentUser,
                           Model model) {

        boolean updatePassword = false;

//        if (!passwordValidator.isValid(user.getPassword(), null)) {
//            model.addAttribute("invalidPassword", Messages.INVALID_PASSWORD);
//        }

        if (bindingResult.hasErrors()) {
            return "user-edit";
        }

        if (!password.isEmpty() || !password2.isEmpty())
        {
                if (!password.equals(password2))
                {
                    model.addAttribute("invalidPassword", Messages.PASSWORD_ARE_NOT_EQUAL);
                } else {
                    updatePassword = true;
                }
            if (model.getAttribute("invalidPassword") != null) {
                return "user-edit";
            }
        }


//        if(!user.getPassword().equals(userService.findById(user.getId()).getPassword()))
//        {
//            System.out.println("!!!!" +user.getPassword()+" "+ password2);
//            if (!user.getPassword().equals(
//                    password2
//            )) {
//                model.addAttribute("invalidPassword", Messages.PASSWORD_ARE_NOT_EQUAL);
//            }
//            else {
//                updatePassword = true;
//            }
//        }
//        if (model.getAttribute("invalidPassword") != null) {
//            return "user-edit";
//        }
        user.setPassword(userService.findById(user.getId()).getPassword());
        userService.updateUser(user, updatePassword);
        return "redirect:/";
    }

    @GetMapping("/donations")
    public String donationList(Model model,
                               @AuthenticationPrincipal CurrentUser customUser) {
        model.addAttribute("user", userService.findById(customUser.getUser().getId()));
        return "user-donation-list";
    }

    @GetMapping("/donation")
    public String donationEdit(Model model,
                               @RequestParam Long id,
                               @AuthenticationPrincipal CurrentUser customUser) {
        model.addAttribute("donation", donationRepository.findById(id));
        User user = userService.findById(customUser.getUser().getId());
        model.addAttribute("user", user);

        return "donation-edit";
    }

    @PostMapping("/donation")
    public String donationSave(@Valid final Donation donation, final BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("!!!" + bindingResult.getAllErrors());
            return "donation-edit";
        }

        donationRepository.save(donation);
        return "redirect:/user/donations";
    }

    @GetMapping("/deletedonation")
    public String deleteDonation(@RequestParam Long id) {
        donationRepository.deleteFromParentRelationTableById(id);
        donationRepository.deleteById(id);
        return "redirect:/user/donations";
    }

    @ModelAttribute("user")
    public User getUser(
            @AuthenticationPrincipal CurrentUser customUser) {
        return customUser.getUser();
    }

    @ModelAttribute("statuses")
    public List<Status> getStatusList() {
        return statusRepository.findAll();
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryRepository.findAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> getInstitutions() {
        return institutionRepository.findAll();
    }
}
