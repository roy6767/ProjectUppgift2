package se.biplob.projectuppgift2.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.biplob.projectuppgift2.model.ApplicationUser;
@Repository
public interface UserRepository extends JpaRepository<ApplicationUser,Long> {
    ApplicationUser findByUsername(String username);
}
//java -cp ./lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:./userdb --dbname.0 userdb