# Use an official Maven runtime as a parent image
FROM maven:3.9.4-eclipse-temurin-21

# Set the working directory in the container
WORKDIR /usr/src/app

# Copy the rest of the application's code into the container at /usr/src/app
COPY . .

# Run Maven to build the application
RUN mvn clean package -DskipTests

# Set the entry point of the container to run the application
CMD ["java", "-jar", "target/CarService-0.0.1-SNAPSHOT.jar"]
