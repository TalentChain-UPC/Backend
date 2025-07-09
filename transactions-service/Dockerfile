FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/transactions-service-0.0.1-SNAPSHOT.jar /app/transactions-service-0.0.1-SNAPSHOT.jar
EXPOSE 8120
ENTRYPOINT ["java", "-jar", "transactions-service-0.0.1-SNAPSHOT.jar"]