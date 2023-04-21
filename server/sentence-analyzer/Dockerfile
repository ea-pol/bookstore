FROM eclipse-temurin:17-jdk-alpine
WORKDIR /sentence-analyzer

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline
COPY src ./src
CMD ["./mvnw", "spring-boot:run"]
