FROM eclipse-temurin:21-jre-alpine AS runner
COPY artifacts/app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
