FROM eclipse-temurin:21-jre-alpine AS runner
ARG JAR_FILE

COPY ${JAR_FILE} .
ENTRYPOINT ["java", "-jar", "app.jar"]
