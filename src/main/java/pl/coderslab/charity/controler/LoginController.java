package pl.coderslab.charity.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage()
    {
        return "login";
    }

    @PostMapping("/login")
    public String validateCredentials(@RequestParam String email,
                                      @RequestParam String password)
    {
        return "login";
    }

}
