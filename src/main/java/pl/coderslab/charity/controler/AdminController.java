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
import pl.coderslab.charity.repository.*;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final StatusRepository statusRepository;
    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;


    public AdminController(UserService userService, BCryptPasswordEncoder passwordEncoder, StatusRepository statusRepository, CategoryRepository categoryRepository, InstitutionRepository institutionRepository, DonationRepository donationRepository, RoleRepository roleRepository, UserRepository userRepository) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.statusRepository = statusRepository;
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("")
    public String homeView(Model model) {
        model.addAttribute("totalQuantity", donationRepository.getTotalQuantity().orElse(0));
        model.addAttribute("donationCount", donationRepository.count());
        model.addAttribute("userCount", userRepository.findAll().stream()
                .filter(u->u.getRoles()
                        .stream()
                        .anyMatch(r->r.getName().equals("ROLE_USER")))
                .count());
        model.addAttribute("adminCount", userRepository.findAll().stream()
                .filter(u->u.getRoles()
                        .stream()
                        .anyMatch(r->r.getName().equals("ROLE_ADMIN"))).count());

        List<Donation> donations = donationRepository.findAll();
        model.addAttribute("donationsCreated",donations.stream().filter(d->d.getStatus().getValue().equals("złożone")).count());
        model.addAttribute("donationsPickedUp",donations.stream().filter(d->d.getStatus().getValue().equals("odebrane")).count());
        model.addAttribute("donationsDelivered",donations.stream().filter(d->d.getStatus().getValue().equals("przekazane")).count());

        return "admin-home";

    }

    //list actions

    @GetMapping("/donations")
    public String donationList(Model model,
                               @AuthenticationPrincipal CurrentUser customUser) {
        return "admin-list-donations";
    }

    @GetMapping("/categories")
    public String categoriesList(Model model) {
        return "admin-list-categories";
    }

    @GetMapping("/institutions")
    public String institutionsList(Model model) {
        return "admin-list-institutions";
    }

    @GetMapping("/admins")
    public String adminsList(Model model,
                             @AuthenticationPrincipal CurrentUser currentUser) {
        model.addAttribute("currentAdminId", currentUser.getUser().getId());
        return "admin-list-admins";
    }

    @GetMapping("/users")
    public String usersList(Model model) {
        return "admin-list-users";
    }

    //add actions
    @GetMapping("/addcategory")
    public String categoryAddForm(Model model) {
        model.addAttribute("category", new Category());
        return "admin-add-edit-category";
    }

    @PostMapping("/addcategory")
    public String categoryEditSave(@Valid Category category, BindingResult result, @RequestParam String confirm) {
        if (confirm.equals("no")) {
            return "redirect:/admin/categories";
        }
        if (result.hasErrors()) {
            return "admin-add-edit-category";
        }
        if (confirm.equals("yes")) {
            categoryRepository.save(category);
        }
        return "redirect:/admin/categories";
    }

    @GetMapping("/addinstitution")
    public String institutionAddForm(Model model) {
        model.addAttribute("institution", new Institution());
        return "admin-add-edit-institution";
    }

    @PostMapping("/addinstitution")
    public String institutionAddSave(@Valid Institution institution, BindingResult result, @RequestParam String confirm) {
        if (confirm.equals("no")) {
            return "redirect:/admin/institutions";
        }
        if (result.hasErrors()) {
            return "admin-add-edit-institution";
        }
        if (confirm.equals("yes")) {
            institutionRepository.save(institution);
        }
        return "redirect:/admin/institutions";
    }

    @GetMapping("/addadmin")
    public String adminAddForm(Model model) {
        model.addAttribute("user", new User());
        return "admin-add-admin";
    }

    @PostMapping("/addadmin")
    public String adminAddSave(@Valid User user, BindingResult result,
                               @RequestParam String confirm,
                               @RequestParam String password,
                               @RequestParam String password2,
                               Model model
    ) {
        if (confirm.equals("no")) {
            return "redirect:/admin/admins";
        }


        if(password.isEmpty() || password2.isEmpty())
        {
            model.addAttribute("invalidPassword", Messages.PASSWORD_IS_BLANK);
            return "admin-add-admin";
        }

        if (!password.equals(password2))
        {
                model.addAttribute("invalidPassword", Messages.PASSWORD_ARE_NOT_EQUAL);
                return "admin-add-admin";
        }

        if (result.hasErrors()) {
            return "admin-add-admin";
        }

        if (confirm.equals("yes")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setEnabled(1);
            Role userRole = roleRepository.findByName("ROLE_ADMIN");
            user.setRoles(new HashSet<>(Arrays.asList(userRole)));
            userRepository.save(user);
        }
        return "redirect:/admin/admins";
    }


    //edit actions

//    @GetMapping("/editcurrentuser")
//    public String currentUserEditForm(
//            @AuthenticationPrincipal CurrentUser currentUser, Model model) {
//        model.addAttribute("user",currentUser.getUser());
//        return "admin-add-edit-user";
//    }

    @GetMapping("/edituser")
    public String userEditForm(Model model,
                               @RequestParam(required = false) String id,
                               @RequestParam(required = false) String group,
                               @AuthenticationPrincipal CurrentUser currentUser) {
        if(id==null)
        {
            model.addAttribute("user",userService.findById(currentUser.getUser().getId()));

        }
        else {
            model.addAttribute("user", userService.findById(Long.parseLong(id)));
        }
        model.addAttribute("group", group);
        return "admin-add-edit-user";
    }

    @PostMapping("/edituser")
    public String userEditSave(@Valid User user, BindingResult bindingResult,
                               @RequestParam String password,
                               @RequestParam String password2,
                               Model model,
                               @RequestParam String confirm,
                               @RequestParam(required = false) String group

    ) {

        if(group == null)
        {
            group ="";
        }

        if (confirm.equals("no")) {
            return "redirect:/admin/"+group;
        }

        boolean updatePassword = false;

//        if (!passwordValidator.isValid(user.getPassword(), null)) {
//            model.addAttribute("invalidPassword", Messages.INVALID_PASSWORD);
//        }

        if (bindingResult.hasErrors()) {
            return "admin-add-edit-user";
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
                return "admin-add-edit-user";
            }
        }

//        if (!user.getPassword().equals(userService.findById(user.getId()).getPassword())) {
//            System.out.println("!!!!" + user.getPassword() + " " + password2);
//            if (!user.getPassword().equals(
//                    password2
//            )) {
//                model.addAttribute("invalidPassword", Messages.PASSWORD_ARE_NOT_EQUAL);
//            } else {
//                updatePassword = true;
//            }
//        }
//        if (model.getAttribute("invalidPassword") != null) {
//            return "admin-add-edit-user";
//        }
        user.setPassword(userService.findById(user.getId()).getPassword());
        userService.updateUser(user, updatePassword);
        return "redirect:/admin/"+group;
    }

    @GetMapping("/editinstitution")
    public String institutionEditForm(Model model,
                                      @RequestParam String id) {
        model.addAttribute("institution", institutionRepository.findById(Long.parseLong(id)));
        return "admin-add-edit-institution";
    }

    @PostMapping("/editinstitution")
    public String institutionEditSave(@Valid Institution institution, BindingResult result, @RequestParam String confirm) {
        if (confirm.equals("no")) {
            return "redirect:/admin/institutions";
        }
        if (result.hasErrors()) {
            return "admin-add-edit-institution";
        }
        if (confirm.equals("yes")) {
            institutionRepository.save(institution);
        }
        return "redirect:/admin/institutions";
    }

    @GetMapping("/editcategory")
    public String categoriesEditForm(Model model,
                                     @RequestParam String id) {
        model.addAttribute("category", categoryRepository.findById(Long.parseLong(id)));
        return "admin-add-edit-category";
    }

    @PostMapping("/editcategory")
    public String categoriesEditsave(@Valid Category category, BindingResult result, @RequestParam String confirm) {
        if (confirm.equals("no")) {
            return "redirect:/admin/categories";
        }
        if (result.hasErrors()) {
            return "admin-add-edit-category";
        }
        if (confirm.equals("yes")) {
            categoryRepository.save(category);
        }
        return "redirect:/admin/categories";
    }

    //delete actions
    @GetMapping("/deleteinstitution")
    public String deleteInstitution(Model model,
                                    @RequestParam String id) {
        institutionRepository.deleteById(Long.parseLong(id));

        return "redirect:/admin/institutions";
    }

    @GetMapping("/deletecategory")
    public String deleteCategory(Model model,
                                 @RequestParam String id) {
        categoryRepository.deleteById(Long.parseLong(id));

        return "redirect:/admin/categories";
    }

    @GetMapping("/deleteuser")
    public String deleteUser(Model model,
                             @RequestParam String id,
                             @RequestParam String group,
                             @AuthenticationPrincipal CurrentUser currentUser) {

        if(Long.parseLong(id) != currentUser.getUser().getId()) {
            userRepository.deleteById(Long.parseLong(id));
        }

        return "redirect:/admin/" + group;
    }

    //logout
    @GetMapping("/logout")
    public String logout()
    {
        return "redirect:/logout";
    }




    //model attributes

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryRepository.findAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> getInstitutions() {
        return institutionRepository.findAll();
    }

    @ModelAttribute("donations")
    public List<Donation> getDonations() {
        return donationRepository.findAll();
    }

    @ModelAttribute("admins")
    public List<User> getAdmins() {
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        return userRepository.findAll().stream()
                .filter(u -> u.getRoles().contains(adminRole))
                .collect(Collectors.toList());
    }

    @ModelAttribute("users")
    public List<User> getUsers() {
        Role userRole = roleRepository.findByName("ROLE_USER");
        return userRepository.findAll().stream()
                .filter(u -> u.getRoles().contains(userRole))
                .collect(Collectors.toList());
    }


}
