FROM openjdk:17-jdk-slim
WORKDIR /app
COPY /target/identity-service-0.0.1-SNAPSHOT.jar /app/identity-service-0.0.1-SNAPSHOT.jar
EXPOSE 8100
ENTRYPOINT ["java", "-jar", "identity-service-0.0.1-SNAPSHOT.jar"]