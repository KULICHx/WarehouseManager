FROM openjdk:22-jdk-slim-buster
ARG JAR_FILE=target/*.jar
COPY target/warehousemanager-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]