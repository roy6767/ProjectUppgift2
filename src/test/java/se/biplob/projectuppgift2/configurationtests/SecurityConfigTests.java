package se.biplob.projectuppgift2.configurationtests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTests {
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testPublicEndpoints() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("welcome"));
        mockMvc.perform(get("/register")).andExpect(status().isOk()).andExpect(view().name("registration_form"));
        mockMvc.perform(get("/login")).andExpect(status().isOk()); // doesn't need to check view because of default form login.
    }

    @Test
    public void testUnauthenticatedAccess() throws Exception {
        mockMvc.perform(get("/user")).andExpect(status().is3xxRedirection());
        mockMvc.perform(get("/admin")).andExpect(status().is3xxRedirection());
        mockMvc.perform(get("/manager")).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testUserAccess() throws Exception {
        mockMvc.perform(get("/user")).andExpect(status().isOk());
        mockMvc.perform(get("/admin")).andExpect(status().isForbidden());
        mockMvc.perform(get("/manager")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testAdminAccess() throws Exception {
        mockMvc.perform(get("/user")).andExpect(status().isOk());
        mockMvc.perform(get("/admin")).andExpect(status().isOk());
        mockMvc.perform(get("/manager")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "MANAGER")
    public void testManagerAccess() throws Exception {
        mockMvc.perform(get("/user")).andExpect(status().isOk());
        mockMvc.perform(get("/admin")).andExpect(status().isOk());
        mockMvc.perform(get("/manager")).andExpect(status().isOk());
    }

}
