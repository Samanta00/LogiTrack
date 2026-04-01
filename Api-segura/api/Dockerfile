# Etapa 1: build
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: runtime com SSL
FROM eclipse-temurin:17-jdk-focal
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
COPY src/main/resources/keystore.p12 keystore.p12
# Porta HTTPS
EXPOSE 8443
ENTRYPOINT ["java","-jar","app.jar"]
