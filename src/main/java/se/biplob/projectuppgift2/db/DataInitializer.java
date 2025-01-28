package se.biplob.projectuppgift2.db;

import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se.biplob.projectuppgift2.model.ApplicationUser;

@Component
public class DataInitializer {
    private UserRepository repository;
    private  PasswordEncoder encoder;

    public DataInitializer(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @PostConstruct
    public void init() {
        if(repository.findByUsername("guest") == null) {
            ApplicationUser user = new ApplicationUser();
            user.setUsername("guest");
            user.setPassword(encoder.encode("password"));
            user.setRole("USER");
            repository.save(user);
        }
    }
}
