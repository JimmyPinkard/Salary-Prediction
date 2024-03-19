import os
import mariadb

from Candidate import Candidate


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
    except mariadb.Error as err:
        print(f"Error: '{err}'")

    return connection


def load_candidates(column_names):
    connection = create_server_connection()
    cursor = connection.cursor()
    sql = "".join(["SELECT ", column_names, " FROM Candidate;"])
    cursor.execute(sql)
    result = cursor.fetchall()
    data = []
    for s in result:
        line = str(s).strip("(").strip(")").replace(" ", "").replace("'", "").replace("HighSchool", "High School").split(",")
        candidate = Candidate(line[0], line[1], line[2], line[3], line[4], line[5], line[6])
        data.append(candidate.to_processable())
    cursor.close()
    connection.close()
    return data
