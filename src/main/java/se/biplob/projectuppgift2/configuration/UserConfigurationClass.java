package se.biplob.projectuppgift2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.biplob.projectuppgift2.component.AdminComponent;
import se.biplob.projectuppgift2.component.ManagerComponent;

/**
 * This is a configurations class that defines the admin and manager component beans and creates instances.
 */
@Configuration //
public class UserConfigurationClass {
    @Bean
    public AdminComponent getAdminComponent() {
        return new AdminComponent();
    }
    @Bean
    public ManagerComponent getManagerComponent() {
        return new ManagerComponent();
    }
}
