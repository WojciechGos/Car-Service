FROM openjdk:21
VOLUME /tmp
EXPOSE 5000
COPY target/CarService-0.0.1-SNAPSHOT.jar CarService.jar
ENTRYPOINT ["java","-jar","/CarService.jar"]