package se.biplob.projectuppgift2.model;

import jakarta.persistence.*;
/**
 * Represents an Applicationuser entity mapped to a database table and stores user credentials and roles
 * for authentication and authorization purposes.
 */
@Entity // Marks this class as a JPA entity which is mapped to a database table.
public class ApplicationUser {
    @Id  // Specifies the primary key of the entity.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Automatically generates unique ID value for each user.
    @Column(nullable = false) // specifies the field attribute and ensures the column that holds the value of the attribute can not be null.
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String role;
    /**
     * Default constructor for JPA to create entity instances.
     */
    public ApplicationUser() {

    }
    /**
     * Parameterized constructor to initialize an application user.
     * @param username The username of the user.
     * @param password The password of the user stored in encoded format.
     * @param role     The role assigned to the user.
     */
    public ApplicationUser(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    //Getter and setter methods to set the values and get the values for the properties of a ApplicationUser Object.
    public Long getId() {return id;}
    public void setId(Long id) { this.id = id;    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {return password;    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

}
