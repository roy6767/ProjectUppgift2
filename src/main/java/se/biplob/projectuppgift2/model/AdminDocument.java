package se.biplob.projectuppgift2.model;
/**
 * A model class representing an administrative document.
 * It defines the attributes of the documents and uses constructor to create instances.
 * Uses get and set methods to bring and define values of the properties of AdminDocument object.
 */
public class AdminDocument {
    private String title;
    private String description;

    public AdminDocument(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
