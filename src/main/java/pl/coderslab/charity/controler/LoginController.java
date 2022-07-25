package pl.coderslab.charity.controler;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.email.EmailService;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.model.Messages;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final UserService userService;

    public LoginController(UserRepository userRepository, EmailService emailService, UserService userService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.userService = userService;
    }


    @GetMapping("")
    public String login() {
        return "login";
    }

    @GetMapping("/resetpassword")
    public String resetPassword() {
        return "reset-password";
    }

    @PostMapping("/resetpassword")
    public String resetPassword(@RequestParam String email,
                                Model model) {
        List<User> userList = userRepository.findAll();
        if (userList.stream()
                .noneMatch(user -> user.getEmail()
                        .equals(email))) {
            model.addAttribute("emailnotexists", "yes");
            return "reset-password";
        }

        User user = userList.stream().filter(u -> u.getEmail().equals(email)).findFirst().get();
        user.setUuid(UUID.randomUUID().toString());
        user.setSentResetRequest(1);
        userService.updateUser(user);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Wejdź na podaną stronę i wpisz nowe hasło: http://localhost:8080/login/reset?uuid=%s");
        String text = String.format(message.getText(), user.getUuid());
        emailService.sendSimpleMessage(user.getEmail(), "Charity app - link resetujący hasło", text);
        model.addAttribute("message", "Email z linkiem resetującym został wysłany");
        return "show-message";
    }


    @GetMapping("/reset")
    public String resetPasswordForm(@RequestParam String uuid,
                                    Model model) {
        model.addAttribute("uuid", uuid);
        return "reset-password-new-password";
    }

    @PostMapping("/reset")
    public String resetPasswordFormPost(Model model,
                                    @RequestParam String password,
                                    @RequestParam String password2,
                                    @RequestParam(required = false) String uuid
    ) {
//        if (!passwordValidator.isValid(user.getPassword(), null)) {
//            model.addAttribute("invalidPassword", Messages.INVALID_PASSWORD);
//        }
        if (!password.equals(
                password2
        )) {
            model.addAttribute("invalidPassword", Messages.PASSWORD_ARE_NOT_EQUAL);
        }

        if (model.getAttribute("invalidPassword") != null) {
            return "reset-password-new-password";
        }

        Optional<User> userOptional = userRepository.findByUuid(uuid);

        if (userOptional.isEmpty()) {
            return "redirect:/";
        }

        User user = userOptional.get();

        user.setPassword(password);
        user.setSentResetRequest(0);
        user.setUuid(null);
        userService.updateUser(user, true);

        model.addAttribute("message", "Hasło zostało zresetowane");
        return "show-message";
    }

}
