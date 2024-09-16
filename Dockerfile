FROM eclipse-temurin:17-jdk-jammy
WORKDIR "/app"
COPY . .
ENTRYPOINT ["./mvnw", "spring-boot:run"]