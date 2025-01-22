package se.biplob.projectuppgift2;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MyController {
    @GetMapping("/user")
    public String index(Model model) {
        Authentication manager= SecurityContextHolder.getContext().getAuthentication();
        String username = manager.getName();
        model.addAttribute("username",username);
        return "Home";
    }
    @GetMapping("/admin")
    public String admin() {

    }
}
