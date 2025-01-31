package se.biplob.projectuppgift2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.biplob.projectuppgift2.model.ApplicationUser;
/**
 * an interface extending JpaRepository for performing database operations on the ApplicationUser entity.
 * JpaRepository provides CRUD operations and other database related functionalities.
 * @Repository annotation defines this interface as a Spring Data repository.
 */
@Repository
public interface UserRepository extends JpaRepository<ApplicationUser,Long> {
    /**
     * This method finds an ApplicationUser entity by its username.
     * This method is automatically implemented by Spring Data JPA.
     * It generates a query to search for a user with the specified username.
     * @param username The username of the user to search in the database.
     * @return The ApplicationUser entity with the given username, or null if no user is found.
     */
    ApplicationUser findByUsername(String username);
}
//java -cp ./lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:./userdb --dbname.0 userdb