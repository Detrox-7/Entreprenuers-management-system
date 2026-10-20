FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q dependency:go-offline
COPY src ./src
RUN mvn -q clean package -DskipTests

FROM tomcat:10.1-jdk25-temurin-jammy
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /app/target/EntrepreneursManagementSystem.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
