package se.biplob.projectuppgift2.mvcconfig;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.biplob.projectuppgift2.model.admin.AdminComponent;
import se.biplob.projectuppgift2.model.admin.AdminDocument;
import se.biplob.projectuppgift2.model.manager.ManagerComponent;
import se.biplob.projectuppgift2.model.manager.ManagerDocument;

import java.util.List;

@Controller
@RequestMapping
public class AppUserController {
    private AdminComponent adminComponent;
    private ManagerComponent managerComponent;

    public AppUserController(AdminComponent adminComponent, ManagerComponent managerComponent) {
        this.adminComponent = adminComponent;
        this.managerComponent = managerComponent;
    }

    @GetMapping("/user")
    public String index(Model model) {
        Authentication manager= SecurityContextHolder.getContext().getAuthentication();
        String username = manager.getName();
        model.addAttribute("username",username);
        return "home";
    }
    @GetMapping("/admin")
    public String admin(Model model) {
        List<AdminDocument> documents= adminComponent.getAdminDocuments();
        model.addAttribute("documents",documents);
        return "admin_documents";
    }
    @GetMapping("/manager")
    public String manager(Model model) {
        List<ManagerDocument> documents=managerComponent.getManagersDocuments();
        model.addAttribute("documents",documents);
        return "manager_documents";
    }
}
