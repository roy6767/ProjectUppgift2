package se.biplob.projectuppgift2.users.admin;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminComponent {
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
