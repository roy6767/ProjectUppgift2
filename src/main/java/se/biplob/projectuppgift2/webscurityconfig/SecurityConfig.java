package se.biplob.projectuppgift2.webscurityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/").permitAll()
                .requestMatchers("/register").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/manager/**").hasRole("MANAGER")
                .requestMatchers("/admin/**").hasAnyRole("ADMIN","MANAGER") // Manager and Admin both can access those
                .requestMatchers("/user").hasAnyRole("USER", "ADMIN","MANAGER")// Everyone can access these pages// Everyone can access
                .anyRequest().authenticated());
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.csrf(Customizer.withDefaults());

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
