FROM openjdk:17-jdk-slim
WORKDIR /app
COPY /target/transactions-service-0.0.1-SNAPSHOT.jar /app/transactions-service-0.0.1-SNAPSHOT.jar
EXPOSE 8120
ENTRYPOINT ["java", "-jar", "transactions-service-0.0.1-SNAPSHOT.jar"]