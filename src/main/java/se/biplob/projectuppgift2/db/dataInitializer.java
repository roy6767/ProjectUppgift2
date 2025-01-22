package se.biplob.projectuppgift2.db;

import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se.biplob.projectuppgift2.users.ApplicationUser;

@Component
public class dataInitializer {
    private  usersRepository repository;
    private  PasswordEncoder encoder;

    public dataInitializer(usersRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @PostConstruct
    public void init() {
        if(repository.findByUsername("user") == null) {
            ApplicationUser user = new ApplicationUser();
            user.setUsername("guest");
            user.setPassword(encoder.encode("password"));
            user.setRole("USER");
            repository.save(user);
        }
    }
}
