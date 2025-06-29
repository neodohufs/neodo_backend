FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY neodo_backend/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
