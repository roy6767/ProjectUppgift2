package se.biplob.projectuppgift2.servicetest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.util.ExceptionCollector;
import se.biplob.projectuppgift2.model.ApplicationUser;
import se.biplob.projectuppgift2.repository.UserRepository;
import se.biplob.projectuppgift2.service.AppUserDetailsService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AppUserDetailsServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AppUserDetailsService appUserDetailsService;

    @BeforeEach
    void setUp() {
        this.appUserDetailsService = new AppUserDetailsService(userRepository);
    }

    @Test
    void loadUserByUsernameTest() {
        ApplicationUser applicationUser = new ApplicationUser("testuser","testpassword","USER");
        when(userRepository.findByUsername("testuser")).thenReturn(applicationUser);
        UserDetails userDetails = appUserDetailsService.loadUserByUsername("testuser");
        assertThat(userDetails).isNotNull();
        assertThat(userDetails.getUsername()).isEqualTo("testuser");
        assertThat(userDetails.getPassword()).isEqualTo("testpassword");
        assertThat(userDetails.getAuthorities().iterator().next().getAuthority()).isEqualTo("ROLE_USER");

    }
    @Test
    void loadByUsernameTestFoundNull() {
        when(userRepository.findByUsername("nonexistentuser")).thenReturn(null);

        // Act & Assert: Call the service method and expect an exception
        UsernameNotFoundException exception = assertThrows(
                UsernameNotFoundException.class,
                new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        appUserDetailsService.loadUserByUsername("nonexistentuser");
                    }
                }
        );

        // Verify the exception message
        assertThat(exception.getMessage()).isEqualTo("User not found");

    }


}
