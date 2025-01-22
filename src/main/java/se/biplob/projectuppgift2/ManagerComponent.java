package se.biplob.projectuppgift2;

import org.apache.catalina.Manager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ManagerComponent {
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
