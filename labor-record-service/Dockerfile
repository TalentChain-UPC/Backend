FROM openjdk:17-jdk-slim
WORKDIR /app
COPY /target/labor-record-service-0.0.1-SNAPSHOT.jar /app/labor-record-service-0.0.1-SNAPSHOT.jar
EXPOSE 8105
ENTRYPOINT ["java", "-jar", "labor-record-service-0.0.1-SNAPSHOT.jar"]