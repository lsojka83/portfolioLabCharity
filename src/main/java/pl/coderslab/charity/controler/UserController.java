package pl.coderslab.charity.controler;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.model.CurrentUser;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/edit")
    public String userEditForm(Model model) {
        return "user-edit";
    }

    @PostMapping("/edit")
    public String userSave(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user-edit";
        }

        userService.updateUser(user);
        return "redirect:/";
    }

    @GetMapping("/donations")
    public String donationList(Model model,
                               @AuthenticationPrincipal CurrentUser customUser) {
        model.addAttribute("user", userService.findByEmail(customUser.getUser().getEmail()));
        return "user-donation-list";
    }

    @ModelAttribute("user")
    public User getUser(
            @AuthenticationPrincipal CurrentUser customUser) {
        return customUser.getUser();
    }
}
