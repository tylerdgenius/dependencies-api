FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/dependencies-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
