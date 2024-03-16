FROM amazoncorretto:17
WORKDIR "/app"
COPY target/salary-prediction-0.0.1-SNAPSHOT.jar ./app.jar
COPY ./env/keystore.p12 /app/keystore.p12
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "./app.jar"]