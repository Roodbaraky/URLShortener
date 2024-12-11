
#FROM node:22
#
#WORKDIR ./URLShortener
#
#RUN ng build --configuration production
#
#COPY ./dist/urlshortener/browser ../resources/META-INF/resources
#
#WORKDIR ../
#
#RUN ./mvnw clean package

FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy the Quarkus application files
COPY ./target/quarkus-app/quarkus-run.jar /app/quarkus-run.jar
COPY ./target/quarkus-app/ /app/

EXPOSE 8080

# Run the application
CMD ["java", "-jar", "/app/quarkus-run.jar"]