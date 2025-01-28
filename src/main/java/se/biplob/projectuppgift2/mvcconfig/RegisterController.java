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

@Controller
@RequestMapping
public class RegisterController {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public RegisterController(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @GetMapping("/register")
    public String getRegistrationForm(Model model) {
        ApplicationUser user = new ApplicationUser();
        model.addAttribute("user", user);
        return "registration_form";
    }

    @PostMapping("/register")
    public String submitForm(@ModelAttribute ApplicationUser user){  //@ModelAttribute annotation binds form data to ApplicationUser object
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
        return "success";
    }

}
