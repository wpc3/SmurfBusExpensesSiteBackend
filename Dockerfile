# Use an official Java 17 base image (change to 11 or 21 if needed)
FROM eclipse-temurin:17-jdk

# Set working directory inside the container
WORKDIR /app

# Copy your code into the container
COPY . .

# Make Maven wrapper executable
RUN chmod +x mvnw

# Build the app
RUN ./mvnw clean package -DskipTests

# Expose the default Spring Boot port
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "target/ExpensesTracker-0.0.1-SNAPSHOT.jar"]
