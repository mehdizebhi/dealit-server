# Use the official OpenJDK 17 image as a base image
FROM eclipse-temurin:17-jdk

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/restful-0.0.1-SNAPSHOT.jar /app/restful-0.0.1-SNAPSHOT.jar

# Copy the application.properties file
COPY ./application.properties /app/application.properties

# Expose the port that your Spring Boot application runs on and mongodb default port
EXPOSE 8085
EXPOSE 27017

# Command to run the application
CMD ["java", "-jar", "/app/restful-0.0.1-SNAPSHOT.jar"]
