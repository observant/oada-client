# Getting started with Observant OADA Client application

## Requirements
* JDK 8 or newer
* Maven 3.2.5 or newer (not tested with older version)

## Build

After cloning the repository run command
```
mvn clean package
```
This will build executable war file located in ```target``` folder.

## Running the application

To run application use command
```
java -jar target/observant-oada-client.war
```

This will start the application from the command line with default settings provided by ```oada-client.properties```
file in the application's classpath. Open URL [http://localhost:9977/testfarms/](http://localhost:9977/testfarms/) 
in your browser to access the application.

## Overridig default settings
To override default properties for the application copy file ```oada-client.properties``` to the folder 
```~/.observant``` and update property values as needed.

Command line arguments and environment variables are also supported. See 
[Spring Boot documentation](http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-external-config)
for more information.
