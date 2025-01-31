package se.biplob.projectuppgift2.webscurityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.biplob.projectuppgift2.model.admin.AdminComponent;
import se.biplob.projectuppgift2.model.manager.ManagerComponent;

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
