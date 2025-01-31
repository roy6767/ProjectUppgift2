package se.biplob.projectuppgift2.component.initializer;

import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se.biplob.projectuppgift2.model.ApplicationUser;
import se.biplob.projectuppgift2.repository.UserRepository;

/**
 * A component class responsible for initializing default data in the database.
 * Creates a user account with username "guest" on application startup if it doesn't already exist.
 */
@Component
public class DataInitializer {
    private UserRepository repository;
    private  PasswordEncoder encoder;
    /**
     * Constructor for dependency injection.
     * @param repository UserRepository instance for database operations
     * @param encoder PasswordEncoder instance for secure password hashing
     */
    public DataInitializer(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }
    /**
     * Initializes default user data after dependency injection is complete.
     * @PostConstruct annotation ensures this method executes automatically
     * after the bean is initialized and dependencies are injected. Creates a
     * demo user with USER role if no existing "guest" user is found.
     */
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
