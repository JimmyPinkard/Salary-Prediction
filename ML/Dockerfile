FROM python:3-bookworm

WORKDIR /app
COPY src/ src/

RUN ["pip3", "install", "mariadb", "pandas", "scikit-learn"]

ENTRYPOINT ["python", "src/main.py", "High School", "28", "Suburban", "Engineer", "49", "Male"]