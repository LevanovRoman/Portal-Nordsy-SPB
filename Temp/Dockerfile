# Build stage
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /build
COPY ../pom.xml .
RUN mvn dependency:go-offline
COPY ../src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:21.0.2_13-jdk-jammy

ARG PROFILE=development
#ARG APP_VERSION=0.0.1-SNAPSHOT

WORKDIR /app
COPY --from=build /build/target/portal-nordsy-spb-*.jar /app/

EXPOSE 8080

ENV DB_URL=jdbc:postgresql://localhost:5432/portal
ENV ACTIVE_PROFILE=${PROFILE}
#ENV JAR_VERSION=${APP_VERSION}

CMD java -jar -Dspring.profiles.active=${ACTIVE_PROFILE} -Dspring.datasource.url=${DB_URL} portal-nordsy-spb-0.0.1-SNAPSHOT.jar
