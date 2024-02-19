FROM openjdk:21

VOLUME /tmp
EXPOSE 5000

COPY target/CarService-0.0.1-SNAPSHOT.jar CarService.jar
ENTRYPOINT ["java","-jar","/CarService.jar"]

# Use an official Maven runtime as a parent image
#FROM maven:3.8.4-openjdk-21-slim

# Set the working directory in the container
#WORKDIR /usr/src/app

# Copy the rest of the application's code into the container at /usr/src/app
#COPY . .

# Run Maven to build the application
# RUN mvn clean package -DskipTests

# # Set the entry point of the container to run the application
# CMD ["java", "-jar", "target/CarService-0.0.1-SNAPSHOT.jar"]
