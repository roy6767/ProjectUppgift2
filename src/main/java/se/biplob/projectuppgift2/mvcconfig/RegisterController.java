package se.biplob.projectuppgift2.mvcconfig;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.biplob.projectuppgift2.db.usersRepository;
import se.biplob.projectuppgift2.users.ApplicationUser;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private usersRepository repository;
    private PasswordEncoder encoder;
    public RegisterController(usersRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }
    @PostMapping
    public String register(
        @RequestParam("username") String username,
        @RequestParam("password") String password,
        @RequestParam("role") String role){

        ApplicationUser user = new ApplicationUser(username, encoder.encode(password), role);
        repository.save(user);
        return "Success";
    }
    @GetMapping
    public String registerForm(){
        return "Register";
    }
}
