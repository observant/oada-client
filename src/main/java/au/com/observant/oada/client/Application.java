package au.com.observant.oada.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Entry point for the Spring Boot application.
 */
@SpringBootApplication
@PropertySources(value = { @PropertySource(value = "classpath:oada-client.properties", ignoreResourceNotFound = false),
        @PropertySource(value = "file:${user.home}/.observant/oada-client.properties", ignoreResourceNotFound = true), })
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
    }
}
