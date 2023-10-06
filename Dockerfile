FROM gradle:latest AS BUILD
WORKDIR /opt/app
COPY . .
RUN gradle build -x test

FROM openjdk:17.0.1-jdk-slim
ARG JAR_FILE=/opt/app/build/libs/*SNAPSHOT.jar
COPY --from=BUILD ${JAR_FILE} backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "backend.jar"]
