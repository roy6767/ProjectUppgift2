package se.biplob.projectuppgift2.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.biplob.projectuppgift2.component.AdminComponent;
import se.biplob.projectuppgift2.model.AdminDocument;
import se.biplob.projectuppgift2.component.ManagerComponent;
import se.biplob.projectuppgift2.model.ManagerDocument;

import java.util.List;
/**
 * This is a dedicated Controller class for handling user, admin, and manager views.
 * It defines different endpoints for different user roles:
 */
@Controller
@RequestMapping
public class AppUserController {

    private AdminComponent adminComponent;
    private ManagerComponent managerComponent;

    /**
     * Constructor for AppUserController.
     * @param adminComponent   AdminComponent responsible for retrieving admin documents.
     * @param managerComponent ManagerComponent responsible for retrieving manager documents.
     */

    public AppUserController(AdminComponent adminComponent, ManagerComponent managerComponent) {
        this.adminComponent = adminComponent;
        this.managerComponent = managerComponent;
    }

    /**
     * GET request for the user page.
     * Brings the currently authenticated user's username  from the securityContextHolder and adds it to the model.
     * @param model Model object to pass data to the view.
     * @return home view template.
     */
    @GetMapping("/user")
    public String index(Model model) {
        Authentication manager= SecurityContextHolder.getContext().getAuthentication();
        String username = manager.getName();
        model.addAttribute("username",username);
        return "home";
    }

    /**
     * GET request for the admin page.
     * Retrieves a list of admin documents and adds them to the model object.
     * @param model Model object to pass data to the view.
     * @return admin_documents view template.
     */
    @GetMapping("/admin")
    public String admin(Model model) {
        List<AdminDocument> documents= adminComponent.getAdminDocuments();
        model.addAttribute("documents",documents);
        return "admin_documents";
    }

    /**
     * GET requests for the manager page.
     * Retrieves a list of manager documents and adds them to the model objeect.
     * @param model Model object to pass data to the view.
     * @return manager_documents view template as the endpoint.
     */
    @GetMapping("/manager")
    public String manager(Model model) {
        List<ManagerDocument> documents=managerComponent.getManagersDocuments();
        model.addAttribute("documents",documents);
        return "manager_documents";
    }
}
