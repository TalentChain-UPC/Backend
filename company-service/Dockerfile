FROM openjdk:17-jdk-slim
WORKDIR /app
COPY /target/company-service-0.0.1-SNAPSHOT.jar /app/company-service-0.0.1-SNAPSHOT.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "company-service-0.0.1-SNAPSHOT.jar"]