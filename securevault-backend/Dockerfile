# ===============================
# BUILD STAGE
# ===============================
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies first (better caching)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build Spring Boot jar
RUN mvn clean package -DskipTests


# ===============================
# RUN STAGE
# ===============================
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy jar from build stage (no hardcoded name)
COPY --from=build /app/target/*.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]
