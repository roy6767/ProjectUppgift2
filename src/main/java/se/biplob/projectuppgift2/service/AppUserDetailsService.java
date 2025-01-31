package se.biplob.projectuppgift2.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.biplob.projectuppgift2.model.ApplicationUser;
import se.biplob.projectuppgift2.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
/**
 * An implementation class of Spring Security's UserDetailsService interface.
 * This service loads user-specific data in the process of authenticating a user.
 * @Service annotation means this class is managed by Spring while scanning for components and registers in the application context.
 */

@Service
public class AppUserDetailsService implements UserDetailsService {
    private final UserRepository repository;
    /**
     * Constructor for dependency injection of the UserRepository.
     * @param repository UserRepository instance for retrieving user data from the database.
     */
    public AppUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }
    /**
     * Loads user details by username during the authentication process from the database and use the data to
     * convert the ApplicationUser entity into a UserDetails object, which is used
     * for authentication and authorization.
     * @param username The username of the user to be authenticated.
     * @return UserDetails object containing user information.
     * @throws UsernameNotFoundException If no user is found with the given username.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        // Create a list of authorities (roles) for the user and Convert the user's role into a GrantedAuthority object
        //GrantedAuthority is an interface in Spring Security that represents an authority granted to a user.
        List<GrantedAuthority> authorities = new ArrayList<>();
        //SimpleGrantedAuthority is a concrete implementation of the GrantedAuthority interface.
        // It wraps the roles into an object that Spring Security can use for authorization checks.
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole());
        authorities.add(authority);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                true,true,true,true,
                authorities);
    }
}
