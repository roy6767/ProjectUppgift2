package se.biplob.projectuppgift2.controllertests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import se.biplob.projectuppgift2.controller.MvcConfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class MvcConfigTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetWelcome() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk()) // Expect HTTP 200 status
                .andExpect(view().name("welcome"));

    }
    @Test
    @WithMockUser(roles = "USER")
    void testGetUserView() throws Exception {
        mockMvc.perform(get("/user/documents"))
                .andExpect(status().isOk())
                .andExpect(view().name("user_document"));
    }
    @Test
    @WithMockUser(roles = "USER")
    public void testAccessDeniedPageForAuthenticatedUser() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isForbidden())
                .andExpect(forwardedUrl("/access-denied"));
    }
    @Test
    public void testAccessDeniedUrl() throws Exception {
        mockMvc.perform(get("/access-denied"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
}
