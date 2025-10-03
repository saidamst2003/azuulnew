
FROM eclipse-temurin:21-jdk-alpine
ADD target/*.jar backend-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","backend-0.0.1-SNAPSHOT.jar"]