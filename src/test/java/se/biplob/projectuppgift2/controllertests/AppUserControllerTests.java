package se.biplob.projectuppgift2.controllertests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import se.biplob.projectuppgift2.component.AdminComponent;
import se.biplob.projectuppgift2.component.ManagerComponent;
import se.biplob.projectuppgift2.controller.AppUserController;
import se.biplob.projectuppgift2.model.AdminDocument;
import se.biplob.projectuppgift2.model.ManagerDocument;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AppUserController.class)
@AutoConfigureMockMvc
public class AppUserControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AdminComponent adminComponent;

    @MockitoBean
    private ManagerComponent managerComponent;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @BeforeEach
    void setUp() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    @WithMockUser(username="testUser")
    void testIndexEndpoint() throws Exception {
        when(authentication.getName()).thenReturn("testUser");

        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attribute("username", "testUser"));
    }

    @Test
    @WithMockUser(roles="ADMIN")
    void testAdminEndpoint() throws Exception {
        List<AdminDocument> adminDocuments = Arrays.asList(
                new AdminDocument("a", "Admin Document 1"),
                new AdminDocument("b", "Admin Document 2")
        );
        when(adminComponent.getAdminDocuments()).thenReturn(adminDocuments);

        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin_documents"))
                .andExpect(model().attribute("documents", adminDocuments));
    }

    @Test
    @WithMockUser(roles="MANAGER")
    void testManagerEndpoint() throws Exception {
        List<ManagerDocument> managerDocuments = Arrays.asList(
                new ManagerDocument("x", "Manager Document 1"),
                new ManagerDocument("y", "Manager Document 2")
        );
        when(managerComponent.getManagersDocuments()).thenReturn(managerDocuments);

        mockMvc.perform(get("/manager"))
                .andExpect(status().isOk())
                .andExpect(view().name("manager_documents"))
                .andExpect(model().attribute("documents", managerDocuments));
    }
}
