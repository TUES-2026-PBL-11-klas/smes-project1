# ===== STAGE 1: BUILD =====
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Копираме pom.xml от backend
COPY src/main/backend/pom.xml . 
RUN mvn dependency:go-offline

# Копираме Java код и resources
COPY src/main/backend/src ./src
COPY src/main/backend/resources ./resources

# Build
RUN mvn clean package -DskipTests

# ===== STAGE 2: RUNTIME =====
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]