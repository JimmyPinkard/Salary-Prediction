import pandas as pd
from sklearn.linear_model import LinearRegression

from db import load_candidates


def load_data():
    column_names = "Education,Experience,Location,Job_Title,Age,Gender,Salary"
    data = load_candidates(column_names)
    frame = pd.DataFrame(columns=[column_names.split(',')], data=data)
    return frame


def train():
    content_data = load_data()
    content_data.drop_duplicates()
    x = content_data.drop(columns=["Salary"])
    y = content_data["Salary"]

    regressor = LinearRegression()
    regressor.fit(x, y)
    return regressor
