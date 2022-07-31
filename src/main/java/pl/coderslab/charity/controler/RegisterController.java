package pl.coderslab.charity.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.email.EmailService;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.model.Messages;
import pl.coderslab.charity.model.URL;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.UserServiceImpl;
import pl.coderslab.charity.validate.PasswordValidator;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserRepository userRepository;
    private final PasswordValidator passwordValidator;
    private final RoleRepository roleRepository;
    private final UserServiceImpl userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailService emailService;

//    @Autowired
//    public SimpleMailMessage template;

    private Logger logger = LoggerFactory.getLogger(RegisterController.class);


    public RegisterController(UserRepository userRepository, PasswordValidator passwordValidator,
                              RoleRepository roleRepository, UserServiceImpl userService, BCryptPasswordEncoder passwordEncoder,
                              EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordValidator = passwordValidator;
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @GetMapping("")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model,
                          @RequestParam String password2) {
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

        if(userService.findByEmail(user.getEmail()) != null)
        {
            model.addAttribute("userexists", "yes");
            return "register";
        }

        userService.saveUser(user);


        // send an email
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setText("Przejdz to tego adresu, zeby aktywowac konto: "+URL.APP_URL+"/register/activate?uuid=%s");
            String text = String.format(message.getText(), user.getUuid());
//        String text = String.format(template.getText(), user.getUuid());
            emailService.sendSimpleMessage(user.getEmail(), "Charity app - potwierdź adres email", text);
        }catch( Exception e ){
            // catch error
            logger.info("Error Sending Email: " + e.getMessage());
        }


        return "redirect:login?userregistered";
    }

    @GetMapping("/activate")
    public String activateUser(@RequestParam String uuid) {

        User userToBeActivated = userRepository.findAll().stream().filter(u -> u.getUuid()!=null).filter(u->u.getUuid().equals(uuid)).findFirst().orElse(null);

        if(userToBeActivated == null)
        {
            return "redirect:/login";
        }

        if (userToBeActivated.getActive() == 0 && userToBeActivated.getSentResetRequest() == 0) {
            userToBeActivated.setActive(1);
            userToBeActivated.setUuid(null);
            userService.updateUser(userToBeActivated);
        }
        return "redirect:/login";
    }

}
