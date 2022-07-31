package pl.coderslab.charity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.coderslab.charity.converter.CategoryConverter;
import pl.coderslab.charity.converter.InstitutionConverter;
import pl.coderslab.charity.converter.StatusConverter;
import pl.coderslab.charity.email.EmailService;
import pl.coderslab.charity.email.EmailServiceImpl;
import pl.coderslab.charity.model.URL;
import pl.coderslab.charity.validate.PasswordValidator;

@EnableAsync
@SpringBootApplication
public class CharityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CharityApplication.class, args);
    }

    @Bean
    public PasswordValidator passwordValidator() {
        return new PasswordValidator();
    }

//    @Bean
//    public SimpleMailMessage templateSimpleMessage() {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setText("Przejdz to tego adresu, zeby aktywowac konto: "+URL.APP_URL+"/register/activate?uuid=%s");
//        return message;
//    }

    @Bean
    public EmailService emailService() {
        return new EmailServiceImpl();
    }
}
