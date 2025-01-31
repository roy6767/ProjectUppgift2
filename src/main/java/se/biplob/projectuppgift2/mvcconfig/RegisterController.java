package se.biplob.projectuppgift2.mvcconfig;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.biplob.projectuppgift2.db.UserRepository;
import se.biplob.projectuppgift2.model.ApplicationUser;

/**
 ** This view controller class is responsible for handling user registration.
 *  defines the endpoints to display the registration form and perform user registration by saving user details in the database.
 */
@Controller
@RequestMapping //Base mapping for request handling which is applicable for all the methods unless individual path is not specified.
public class RegisterController {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    /**
     * Constructor for RegisterController.
     * @param repository UserRepository is used for accessing the database.
     * @param encoder PasswordEncoder is used for encrypting user passwords.
     */
    public RegisterController(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    /**
     * Get request to display the registration form.
     * creates a new ApplicationUser object and  using model object allows thymeleaf to bind form fields data with the object.
     * @param model Model object to pass data to the view.
     * @return registration_form view.
     */
    @GetMapping("/register")
    public String getRegistrationForm(Model model) {
        ApplicationUser user = new ApplicationUser();
        model.addAttribute("user", user);
        return "registration_form";
    }

    /**
     * POST request for user registration.
     * receives data from the form, encrypts the user's password, and save the user in the database.
     * @param user ApplicationUser object rendered from the form.
     * @return The successful view.
     */
    @PostMapping("/register")
    public String submitForm(@ModelAttribute ApplicationUser user){  //@ModelAttribute annotation binds form data to ApplicationUser object
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
        return "success";
    }

}
