FROM openjdk:17-jdk-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} search-service-0.0.1-SNAPSHOT.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/search-service-0.0.1-SNAPSHOT.jar"]
