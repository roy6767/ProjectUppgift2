package se.biplob.projectuppgift2.configurationtests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import se.biplob.projectuppgift2.component.AdminComponent;
import se.biplob.projectuppgift2.component.ManagerComponent;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
public class UserConFigurationTests {
    @Autowired
    private ApplicationContext applicationContext;

    @Test  // checks that automatic instances are created and managed by application context.
    void testAdminComponentBean() {
        AdminComponent adminComponent = applicationContext.getBean(AdminComponent.class);
        assertNotNull(adminComponent, "AdminComponent bean should not be null");
    }

    @Test
    public void testManagerComponentBean() {
        ManagerComponent managerComponent=applicationContext.getBean(ManagerComponent.class);
        assertNotNull(managerComponent, "ManagerComponent bean should not be null");
    }

    @Test // checks only one instances is created and injected
    public void testUniqueAdminComponentInstances() {
        AdminComponent adminComponent1 = applicationContext.getBean(AdminComponent.class);
        AdminComponent adminComponent2 = applicationContext.getBean(AdminComponent.class);
        assertSame(adminComponent1, adminComponent2, "AdminComponent instances should be the same (singleton)");
    }

    @Test
    void testUniqueManagerComponentInstances() {
        ManagerComponent managerComponent1 = applicationContext.getBean(ManagerComponent.class);
        ManagerComponent managerComponent2 = applicationContext.getBean(ManagerComponent.class);
        assertSame(managerComponent1, managerComponent2, "ManagerComponent instances should be the same (singleton)");
    }
}
