package se.biplob.projectuppgift2.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * Configuration class for setting up simple view controllers.
 * maps specific URL paths with the view templates without specifying dedicated controller class.
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    /**
     * Registers view controllers and maps them to specific view templates.
     * @param registry ViewControllerRegistry used to register view controllers.
     */
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("welcome");
        registry.addViewController("/user/documents").setViewName("user_document");
        registry.addViewController("/access-denied").setViewName("access_denied");
    }
}
