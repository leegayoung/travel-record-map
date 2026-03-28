# Stage 1: Build
FROM eclipse-temurin:21-jdk-jammy AS builder
WORKDIR /app

# 루트에서 필요한 모든 설정을 복사
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle settings.gradle ./

# 모든 공통 모듈 및 현재 서비스 모듈 복사
COPY common-core ./common-core
COPY common-security ./common-security
COPY common-kafka ./common-kafka
COPY persistence-common ./persistence-common
COPY web-common ./web-common
COPY auth ./auth

# 빌드 실행
RUN ./gradlew :auth:clean :auth:build -x test

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# 빌드된 결과물 복사
COPY --from=builder /app/auth/build/libs/*-SNAPSHOT.jar app.jar

# 포트 노출
EXPOSE 8083

# 컨테이너 실행 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]
