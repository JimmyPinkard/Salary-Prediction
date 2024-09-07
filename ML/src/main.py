import sys
import warnings
from Candidate import Candidate
from ai import train


def ui(model):
    candidate = Candidate(sys.argv[1], sys.argv[2], sys.argv[3], sys.argv[4], sys.argv[5], sys.argv[6], -1)
    predicted = model.predict([candidate.to_processable()[0:6]])[0][0]
    predicted_salary = round(predicted[0], 2)
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
