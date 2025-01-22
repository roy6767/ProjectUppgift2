package se.biplob.projectuppgift2;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("Welcome");
        registry.addViewController("/home").setViewName("Home");
        registry.addViewController("/register").setViewName("Register");
    }
}
