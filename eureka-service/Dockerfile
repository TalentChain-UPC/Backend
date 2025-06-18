FROM openjdk:17-jdk-slim
WORKDIR /app
COPY /target/eureka-service-0.0.1-SNAPSHOT.jar /app/eureka-service-0.0.1-SNAPSHOT.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "eureka-service-0.0.1-SNAPSHOT.jar"]