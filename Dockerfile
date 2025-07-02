FROM gradle:8.8.0-jdk17-alpine AS builder

WORKDIR /build

COPY build.gradle settings.gradle ./
COPY src ./src

RUN gradle bootJar --no-daemon

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=builder /build/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]