package se.biplob.projectuppgift2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * This is the main entry point for the application.
 * This class is annotated with @SpringBootApplication, which means the class is a source of bean definitions. Enables automatic configuration mechanism.
 * and scans for components, configurations, and services.
 * The main method runs the application by calling SpringApplication.run().
 */
@SpringBootApplication
public class ProjectUppgift2Application {
    /**
     * This method starts the application.
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run (ProjectUppgift2Application.class, args);
    }

}
