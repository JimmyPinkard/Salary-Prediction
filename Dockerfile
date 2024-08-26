FROM amazoncorretto:17-alpine
RUN apk add python3 py3-pip mariadb-connector-c && apk add py3-pandas py3-scikit-learn
WORKDIR "/app"
COPY target/salary-prediction-0.0.1-SNAPSHOT.jar ./app.jar
COPY ML/src ./ML/src
COPY ./env/keystore.p12 /app/keystore.p12
#RUN ["pip3", "install", "mariadb", "pandas", "scikit-learn"]
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "./app.jar"]