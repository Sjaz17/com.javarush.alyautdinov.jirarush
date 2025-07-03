FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY ./resources ./resources
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]