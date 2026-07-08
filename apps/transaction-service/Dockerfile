FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY gradlew settings.gradle build.gradle ./
COPY gradle ./gradle
RUN ./gradlew --no-daemon dependencies || true
COPY src ./src
RUN ./gradlew --no-daemon clean bootJar -x test

FROM eclipse-temurin:21-jre-alpine AS run
RUN addgroup -S app && adduser -S app -G app
USER app
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8085
HEALTHCHECK --interval=30s --timeout=3s \
    CMD wget -qo- http://localhost:8085/api/v1/health/ping || exit 1
ENTRYPOINT ["java","-jar","app.jar"]