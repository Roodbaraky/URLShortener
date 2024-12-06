FROM openjdk:21-jdk-slim

WORKDIR /app


COPY ./target/url-shortener-1.0.0-SNAPSHOT.jar /app/quarkus-run.jar

CMD ["java", "-jar", "/app/quarkus-run.jar"]
