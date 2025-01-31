package se.biplob.projectuppgift2.component;

import org.springframework.stereotype.Component;
import se.biplob.projectuppgift2.model.AdminDocument;

import java.util.ArrayList;
import java.util.List;
/**
 * AdminComponent is a Spring-managed component responsible for providing a list of admin-related documents.
 * @Component annotation marks this class as a Spring-managed component,
 * enables for automatic detection and registration in the application context.
 */
@Component
public class AdminComponent {
    /**
     * This method creates instances and returns a list of predefined AdminDocument objects.
     * @return A list of AdminDocument objects representing admin-related documents.
     */
    public List<AdminDocument> getAdminDocuments() {
        List<AdminDocument> adminDocuments = new ArrayList<AdminDocument>();
        AdminDocument document1=new AdminDocument("a","This is admin document1");
        AdminDocument document2=new AdminDocument("b","This is admin document2");
        AdminDocument document3=new AdminDocument("c","This is admin document3");
        adminDocuments.add(document1);
        adminDocuments.add(document2);
        adminDocuments.add(document3);
        return adminDocuments;
    }
}
