FROM openjdk:17-jdk-alpine

WORKDIR /app

# Copy the JAR file (assuming it's named ai-task-manager.jar)
COPY target/ai-task-manager-2.2.0.jar /app/ai-task-manager-2.2.0.jar

# Expose the port your Spring Boot application runs on
EXPOSE 8081

# Start the Spring Boot application
ENTRYPOINT ["java", "-jar", "ai-task-manager-2.2.0.jar"]