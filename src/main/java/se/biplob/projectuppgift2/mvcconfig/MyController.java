package se.biplob.projectuppgift2.mvcconfig;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.biplob.projectuppgift2.users.admin.AdminComponent;
import se.biplob.projectuppgift2.users.admin.AdminDocument;
import se.biplob.projectuppgift2.users.manager.ManagerComponent;
import se.biplob.projectuppgift2.users.manager.ManagerDocument;

import java.util.List;

@Controller
@RequestMapping
public class MyController {
    private AdminComponent adminComponent;
    private ManagerComponent managerComponent;

    public MyController(AdminComponent adminComponent, ManagerComponent managerComponent) {
        this.adminComponent = adminComponent;
        this.managerComponent = managerComponent;
    }
    @GetMapping("/home")
    public String index(Model model) {
        Authentication manager= SecurityContextHolder.getContext().getAuthentication();
        String username = manager.getName();
        model.addAttribute("username",username);
        return "Home";
    }
    @GetMapping("/admin")
    public String admin(Model model) {
        List<AdminDocument> documents= adminComponent.getAdminDocuments();
        model.addAttribute("documents",documents);
        return "Admin";
    }
    @GetMapping("/manager")
    public String manager(Model model) {
        List<ManagerDocument> documents=managerComponent.getManagersDocuments();
        model.addAttribute("documents",documents);
        return "Manager";
    }
}
