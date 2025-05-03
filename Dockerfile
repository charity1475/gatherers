FROM eclipse-temurin:21-jre-alpine AS runner
WORKDIR /app
COPY out/artifacts/gatherers_jar/gatherers.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
