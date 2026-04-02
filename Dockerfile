# Stage 1: Build
FROM gradle:8.14.4-jdk21 AS builder
WORKDIR /app

USER root
RUN apt-get update && apt-get install -y ca-certificates && update-ca-certificates

# 1. 설정 파일 복사 (캐시 활용)
COPY build.gradle settings.gradle ./
# 만약 루트에 gradle 폴더가 있다면 복사
COPY gradle ./gradle

# 2. 의존성 미리 다운로드
RUN gradle dependencies --no-daemon || true

# 3. 소스 전체 복사
COPY . .

ARG SERVICE_NAME
ENV GRADLE_OPTS="-Dhttps.protocols=TLSv1.2,TLSv1.3"

# 4. [중요] compileJava가 아닌 bootJar를 실행하여 JAR를 생성합니다.
RUN gradle :${SERVICE_NAME}:bootJar --no-daemon

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

ARG SERVICE_NAME
# 5. 빌드된 JAR를 복사 (파일명 패턴 매칭)
COPY --from=builder /app/${SERVICE_NAME}/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]