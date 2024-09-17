FROM openjdk:17
WORKDIR "/home/app"
COPY . .
COPY src/main/resources/Candidate.csv /home/app/Candidate.csv
RUN ["./mvnw", "package", "-DskipTests"]
RUN ["useradd", "-s", "/bin/bash", "app"]
RUN ["chown", "-R", "app", "/home/app"]
RUN mv target/salary-prediction-0.0.1-SNAPSHOT.jar .
RUN rm -rf src ReadMe.md Dockerfile docker-compose.yml mvnw.cmd pom.xml mvnw target .gitignore
USER app
ENV CSV_FILE /home/app/Candidate.csv
ENTRYPOINT ["java", "-jar", "salary-prediction-0.0.1-SNAPSHOT.jar"]