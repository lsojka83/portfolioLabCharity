package pl.coderslab.charity.controler;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.model.Messages;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.UserServiceImpl;
import pl.coderslab.charity.validate.PasswordValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserRepository userRepository;
    private final PasswordValidator passwordValidator;
    private final RoleRepository roleRepository;
    private final UserServiceImpl userService;
    private final BCryptPasswordEncoder passwordEncoder;


    public RegisterController(UserRepository userRepository, PasswordValidator passwordValidator, RoleRepository roleRepository, UserServiceImpl userService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordValidator = passwordValidator;
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("")
    public String showRegisterForm(Model model)
    {
        model.addAttribute("user",new User());
        return "register";
    }

    @PostMapping("")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model,
                          @RequestParam String password2)
    {
//        if (!passwordValidator.isValid(user.getPassword(), null)) {
//            model.addAttribute("invalidPassword", Messages.INVALID_PASSWORD);
//        }
        if (!user.getPassword().equals(
                password2
        )) {
            model.addAttribute("invalidPassword", Messages.PASSWORD_ARE_NOT_EQUAL);
        }
        if (bindingResult.hasErrors()) {
            return "register";
        }
        if (model.getAttribute("invalidPassword") != null) {
            return "register";
        }

        userService.saveUser(user);
        return "redirect:login";
    }
}
