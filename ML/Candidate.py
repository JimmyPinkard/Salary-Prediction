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

    def education_number(self):
        match self.education:
            case "High School":
                return 1
            case "Bachelor":
                return 2
            case "Master":
                return 3
            case "PhD":
                return 4

    def location_number(self):
        match self.location:
            case "Rural":
                return 1
            case "Suburban":
                return 2
            case "Urban":
                return 3

    def title_number(self):
        match self.job_title:
            case "Analyst":
                return 1
            case "Engineer":
                return 2
            case "Manager":
                return 3
            case "Director":
                return 4

    def gender_number(self):
        match self.gender:
            case "Female": return 1
            case "Male": return 2

    def to_processable(self):
        arr = [None] * 7
        arr[0] = self.education_number()
        arr[1] = self.experience
        arr[2] = self.location_number()
        arr[3] = self.title_number()
        arr[4] = self.age
        arr[5] = self.gender_number()
        arr[6] = self.salary
        return arr

    def worth_statement(self):
        return (f"A {self.age} year old {self.gender} {self.job_title} with an education level of {self.education} "
                f"and {self.experience} years of experience located in a {self.location} area "
                f"can expect to earn ${self.salary}")
