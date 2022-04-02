FROM openjdk:14-jdk-alpine
ARG JAR_FILE=build/libs/*SNAPSHOT.jar
COPY ${JAR_FILE} customer-backend.jar
ENTRYPOINT ["java","-jar","customer-backend.jar"]