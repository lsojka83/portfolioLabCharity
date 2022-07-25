package pl.coderslab.charity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.coderslab.charity.service.SpringDataUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("{noop}pass").roles("USER")
//                .and()
//                .withUser("admin").password("{noop}pass").roles("ADMIN");
//
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String roleUser = "USER";
        String roleAdmin = "ADMIN";

        http.authorizeRequests()
//                .antMatchers("/institutions/**","/register/**").permitAll()
//                .antMatchers("/").hasAnyRole(roleUser,roleAdmin)
//                .antMatchers("/user/**","/form/**").authenticated()
                .antMatchers("/user/**","/form/**").hasRole(roleUser)
                .antMatchers("/admin/**").hasRole(roleAdmin)
                .and().formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
//                .defaultSuccessUrl("/")
                .and().logout().logoutSuccessUrl("/")
                .permitAll()
                .and().exceptionHandling().accessDeniedPage("/403")
        ;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringDataUserDetailsService customUserDetailsService() {
        return new SpringDataUserDetailsService();
    }
}
