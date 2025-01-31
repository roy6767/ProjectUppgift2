package se.biplob.projectuppgift2.component;

import org.springframework.stereotype.Component;
import se.biplob.projectuppgift2.model.ManagerDocument;

import java.util.ArrayList;
import java.util.List;
/**
 * ManagerComponent is a Spring-managed component responsible for providing a list of manager-related documents.
 * @Component annotation marks this class as a Spring-managed component,
 * enables for automatic detection and registration in the application context.
 */
@Component
public class ManagerComponent {
    /**
     * This method creates instances and returns a list of predefined ManagerDocument objects.
     * @return A list of ManagerDocument objects representing manager-related documents.
     */
    public List<ManagerDocument> getManagersDocuments() {
        List<ManagerDocument> managerDocuments = new ArrayList<ManagerDocument>();
        ManagerDocument managerDocument1 = new ManagerDocument("x","This is manager document 1");
        ManagerDocument managerDocument2 = new ManagerDocument("y","This is manager document 2");
        ManagerDocument managerDocument3 = new ManagerDocument("z","This is manager document 3");
        managerDocuments.add(managerDocument1);
        managerDocuments.add(managerDocument2);
        managerDocuments.add(managerDocument3);
        return managerDocuments;
    }
}
