FROM amazoncorretto:17
WORKDIR "/app"
COPY target/salary-prediction-0.0.1-SNAPSHOT.jar ./app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "./app.jar"]