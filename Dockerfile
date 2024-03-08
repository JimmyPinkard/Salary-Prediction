FROM maven:3.9.6-amazoncorretto-17-debian

WORKDIR "/app"
#COPY . .
#RUN ["mvn", "-U", "install", "-Dspring.profiles.active=docker"]
ENTRYPOINT ["mvn", "spring-boot:run", "-Dspring.profiles.active=docker"]