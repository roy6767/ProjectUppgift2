package se.biplob.projectuppgift2.controllertests;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import se.biplob.projectuppgift2.controller.RegisterController;
import se.biplob.projectuppgift2.repository.UserRepository;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class RegisterControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @WithMockUser
    public void testGetRegister() throws Exception{
        mockMvc.perform(get("/register").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("registration_form"));
    }
    @Test
    public void testPostRegister() throws Exception{
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword"); // Mock password encoding

        mockMvc.perform(post("/register")
                        .param("username", "testuser")
                        .param("password", "password123")
                        .param("role", "USER"))
                .andExpect(status().isOk())
                .andExpect(view().name("success"));

    }
}
