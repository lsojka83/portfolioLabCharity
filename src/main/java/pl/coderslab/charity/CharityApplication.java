package pl.coderslab.charity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.coderslab.charity.converter.CategoryConverter;
import pl.coderslab.charity.converter.InstitutionConverter;
import pl.coderslab.charity.converter.StatusConverter;
import pl.coderslab.charity.validate.PasswordValidator;

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
//    public Converter categoryConverter() {
//        return new CategoryConverter();
//    }
//
//
//    @Bean
//    public Converter institutionConverter() {
//        return new InstitutionConverter();
//    }
//
//    @Bean
//    public Converter statusConverter() {
//        return new StatusConverter();
//    }
}
