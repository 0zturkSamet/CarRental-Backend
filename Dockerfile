# Multi-stage build for optimized image size
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build

WORKDIR /app

# Copy pom.xml and download dependencies (cached layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests

# Production stage
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Create non-root user for security
RUN addgroup -g 1001 -S appuser && adduser -u 1001 -S appuser -G appuser

# Copy the jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Create logs directory
RUN mkdir -p logs && chown -R appuser:appuser /app

# Switch to non-root user
USER appuser

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:9090/car-rental/api/actuator/health || exit 1

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "app.jar"]
