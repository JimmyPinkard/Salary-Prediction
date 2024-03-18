import sys
import os
import warnings
from Candidate import Candidate

import pandas as pd
from sklearn.tree import DecisionTreeRegressor
import mariadb


def create_server_connection():
    connection = None
    try:
        connection = mariadb.connect(
            host=os.environ["MYSQL_URL"],
            user=os.environ["MYSQL_USER"],
            passwd=os.environ["MYSQL_PASSWORD"],
            port=3306,
            database="salary_prediction"
        )
        print("MySQL Database connection successful")
    except mariadb.Error as err:
        print(f"Error: '{err}'")

    return connection


def load_candidates():
    connection = create_server_connection()
    cursor = connection.cursor()
    cursor.execute("SELECT * FROM Candidate")
    result = cursor.fetchall()
    data = []
    for s in result:
        line = str(s).strip("(").strip(")").replace(" ", "").replace("'", "").replace("HighSchool", "High School").split(",")
        candidate = Candidate(line[1], line[2], line[3], line[4], line[5], line[6], line[7])
        data.append(candidate.to_processable())
    return data


def load_data():
    data = load_candidates()
    row_names = "Education,Experience,Location,Job_Title,Age,Gender,Salary"
    frame = pd.DataFrame(columns=[row_names.split(',')], data=data)
    return frame


def train():
    content_data = load_data()
    content_data.drop_duplicates()
    x = content_data.drop(columns=["Salary"])
    y = content_data["Salary"]

    regressor = DecisionTreeRegressor()
    regressor.fit(x, y)
    return regressor


def ui(model):
    candidate = Candidate(sys.argv[1], sys.argv[2], sys.argv[3], sys.argv[4], sys.argv[5], sys.argv[6], -1)
    predicted_salary = round(model.predict([candidate.to_processable()[0:6]])[0], 2)
    candidate.salary = predicted_salary
    print(candidate.worth_statement())


def print_help():
    print("Run as 'python main.py <Education> <Experience> <Location> <Job_Title> <Age> <Gender>'", file=sys.stderr)
    print("Education is 'High School', 'Bachelor', 'Master', or 'PhD'")
    print("Experience is your years of experience")
    print("Location is 'Rural', 'Suburban', or 'Urban'")
    print("Job_Title is 'Analyst', 'Engineer', 'Manager', or 'Director'")
    print("Age is your age in years")
    print("Gender is either 'Male' or 'Female'")
    exit(1)


def main():
    warnings.filterwarnings("ignore")
    if len(sys.argv) != 7:
        print_help()
    model = train()
    ui(model)


if __name__ == '__main__':
    main()
