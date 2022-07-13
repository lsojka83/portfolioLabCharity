package pl.coderslab.charity.controler;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.model.Messages;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.validate.PasswordValidator;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private final UserRepository userRepository;
    private final PasswordValidator passwordValidator;
    private final RoleRepository roleRepository;


    public RegisterController(UserRepository userRepository, PasswordValidator passwordValidator, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordValidator = passwordValidator;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model)
    {
        model.addAttribute("role",roleRepository.findByRole("ROLE_USER"));
        model.addAttribute("user",new User());
        return "register";
    }

    @PostMapping("/register")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model)
    {
        if (!passwordValidator.isValid(user.getPassword(), null)) {
            model.addAttribute("invalidPassword", Messages.INVALID_PASSWORD);
        }
        if (bindingResult.hasErrors()) {
            System.out.println("!!!!+form has errors");
            return "register";
        }
        if (model.getAttribute("invalidPassword") != null) {
            return "register";
        }

        user.setRole(roleRepository.findByRole("ROLE_USER"));
        userRepository.save(user);
        return "redirect:login";
    }
}
