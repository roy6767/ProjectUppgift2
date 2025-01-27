package se.biplob.projectuppgift2.WebSecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.biplob.projectuppgift2.users.admin.AdminComponent;
import se.biplob.projectuppgift2.users.manager.ManagerComponent;

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
