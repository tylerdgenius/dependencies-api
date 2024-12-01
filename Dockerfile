FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/dependencies-0.0.1-SNAPSHOT.jar /app/app.jar

ARG PORT=9000

ENV PORT=$PORT

EXPOSE $PORT

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
