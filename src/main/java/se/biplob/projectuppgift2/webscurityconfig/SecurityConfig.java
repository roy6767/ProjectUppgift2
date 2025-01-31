package se.biplob.projectuppgift2.webscurityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
/**
 * This is Security configuration class
 * for setting the configuration of Spring Security to handle authentication, authorization
 * form login, logout, CSRF protection, and exception handling.
 * It also contains a java bean for hashing the password using BCrypt.
 */

@Configuration
@EnableWebSecurity // this annotation enables spring security for the application
public class SecurityConfig {

    /**
     * This configures the security filter chain for handling authentication and authorization.
     * @param http an HttpSecurity object used to configure security settings
     * @return the configured SecurityFilterChain
     * @throws Exception if any configuration error occurs
     */
    @Bean // Marks this method as a Spring-managed bean for the security filter chain
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/","/register","/login").permitAll() //Everyone has access to these resources.
                .requestMatchers("/manager/**").hasRole("MANAGER") //only user with manager role can access these resources.
                .requestMatchers("/admin/**").hasAnyRole("ADMIN","MANAGER") // User with manager and admin role both can access those resources.
                .requestMatchers("/user").hasAnyRole("USER", "ADMIN","MANAGER") // User with user,manager and admin role can access these resources.
                .anyRequest().authenticated()); //any other request to access resources will need authentication.

        //for login Spring security default form login handling is used with custom redirect url.
        http.formLogin(form -> form
                .defaultSuccessUrl("/user", true)
                .permitAll());

        //default spring security logout handling is used for logout action.
        http.logout(Customizer.withDefaults());

        //enables csrf security with default behavior.
        http.csrf(Customizer.withDefaults());

        //Exception handling for denial of unauthorized access.
        http.exceptionHandling(exception -> exception
                .accessDeniedPage("/access-denied"));

        return http.build();  //build and return the security filter chain configuration.
    }

    /**
     * This is password encoder bean to securely hash passwords using BCrypt.
     * @return a BCryptPasswordEncoder instance for password encryption
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
