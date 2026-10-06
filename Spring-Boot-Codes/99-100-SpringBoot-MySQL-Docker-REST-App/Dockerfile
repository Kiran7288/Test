# Use a base image as jdk 17
FROM eclipse-temurin:17-jdk-jammy

# Copy the Spring Boot jar file into the container
COPY target/99-100-SpringBoot-MySQL-Docker-REST-App-1.0.jar /usr/app/

# Set the working directory inside the container 
WORKDIR /usr/app/

# Expose the port the app runs on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT [ "java", "-jar", "99-100-SpringBoot-MySQL-Docker-REST-App-1.0.jar" ]