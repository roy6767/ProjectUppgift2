package se.biplob.projectuppgift2.repositorytest;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import se.biplob.projectuppgift2.model.ApplicationUser;
import se.biplob.projectuppgift2.repository.UserRepository;
import org.springframework.test.context.TestPropertySource;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserRepositoryTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void testFindUserByUsername() throws Exception {
        ApplicationUser user = new ApplicationUser();
        user.setUsername("testusername");
        user.setPassword(passwordEncoder.encode("password"));
        user.setRole("USER");
        userRepository.save(user);
        mockMvc.perform(formLogin("/login")
                        .user("testusername")
                        .password("password"))
                        .andExpect(status().is3xxRedirection())
                        .andExpect(redirectedUrl("/user"));

    }
}