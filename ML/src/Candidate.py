edu_dict = {}
location_dict = {}
job_title_dict = {}
gender_dict = {}

def education_number(key):
    if key not in edu_dict:
       edu_dict[key] = len(edu_dict) + 1
    return edu_dict[key]

def location_number(key):
    if key not in location_dict:
        location_dict[key] = len(location_dict) + 1
    return location_dict[key]

def title_number(key):
    if key not in job_title_dict:
        job_title_dict[key] = len(job_title_dict) + 1
    return job_title_dict[key]

def gender_number(key):
    if key not in gender_dict:
        gender_dict[key] = len(gender_dict) + 1
    return gender_dict[key]

class Candidate:
    def __init__(self, education, experience, location, job_title, age, gender, salary):
        self.education = education
        self.experience = int(experience)
        self.location = location
        self.job_title = job_title
        self.age = int(age)
        self.gender = gender
        self.salary = float(salary)

    def __str__(self):
        return f'{self.education, self.experience, self.location, self.job_title, self.age, self.gender, self.salary}'

    def to_processable(self):
        arr = [None] * 7
        arr[0] = education_number(self.education)
        arr[1] = self.experience
        arr[2] = location_number(self.location)
        arr[3] = title_number(self.job_title)
        arr[4] = self.age
        arr[5] = gender_number(self.gender)
        arr[6] = self.salary
        return arr

    def worth_statement(self):
        return (f"A {self.age} year old {self.gender} {self.job_title} with an education level of {self.education} "
                f"and {self.experience} years of experience located in a {self.location} area "
                f"can expect to earn ${self.salary}")
