# -------- BUILD STAGE --------
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom first for dependency caching
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build jar
RUN mvn clean package -DskipTests


# -------- RUNTIME STAGE --------
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy built jar from builder
COPY --from=build /app/target/*.jar app.jar

# Port your app runs on
EXPOSE 8080

# Run app
ENTRYPOINT ["java","-jar","app.jar"]