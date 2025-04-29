FROM eclipse-temurin:21-jdk-jammy as builder
LABEL authors="charity"
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN --mount=type=cache,target=/root/.m2 mvn -B package --file pom.xml

FROM eclipse-temurin:21-jre-alpine as runner
WORKDIR /app
COPY --from=builder /app/target/gatherers-*-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
