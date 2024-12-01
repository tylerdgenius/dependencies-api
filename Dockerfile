# FROM openjdk:17-jdk-alpine
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/dependencies-0.0.1-SNAPSHOT.jar /app/app.jar

ARG PORT=9000

ENV PORT=$PORT

EXPOSE $PORT

CMD ["java", "-jar", "/app/app.jar"]
