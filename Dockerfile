# Stage 1: Build
FROM gradle:8.14.4-jdk21 AS builder
WORKDIR /app

USER root
RUN apt-get update && apt-get install -y ca-certificates && update-ca-certificates


COPY . .

ARG SERVICE_NAME
ENV GRADLE_OPTS="-Dhttps.protocols=TLSv1.2,TLSv1.3"
RUN gradle :${SERVICE_NAME}:clean :${SERVICE_NAME}:build -x test --no-daemon

# Stage 2
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

ARG SERVICE_NAME
COPY --from=builder /app/${SERVICE_NAME}/build/libs/*-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]