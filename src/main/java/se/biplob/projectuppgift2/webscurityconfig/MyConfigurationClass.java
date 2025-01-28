package se.biplob.projectuppgift2.webscurityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.biplob.projectuppgift2.model.admin.AdminComponent;
import se.biplob.projectuppgift2.model.manager.ManagerComponent;

@Configuration
public class MyConfigurationClass {
    @Bean
    public AdminComponent getAdminComponent() {
        return new AdminComponent();
    }
    @Bean
    public ManagerComponent getManagerComponent() {
        return new ManagerComponent();
    }
}
