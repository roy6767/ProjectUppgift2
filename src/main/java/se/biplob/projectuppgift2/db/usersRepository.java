package se.biplob.projectuppgift2.db;

import org.springframework.data.jpa.repository.JpaRepository;
import se.biplob.projectuppgift2.users.ApplicationUser;

public interface usersRepository extends JpaRepository<ApplicationUser,Long> {
    ApplicationUser findByUsername(String username);
}
