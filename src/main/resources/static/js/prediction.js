const formJson = {
    education: document.getElementById("education"),
    experience: document.getElementById("experience"),
    location: document.getElementById("location"),
    jobTitle: document.getElementById("job-title"),
    age: document.getElementById("age"),
    gender: document.getElementById("gender"),
    predictionButton: document.getElementById("predictionButton")
}
let model = {};

async function request(endpoint, method, body) {
    return fetch(endpoint, {
        headers: {
            "Content-Type": "application/json"
        },
        method: method,
        body: JSON.stringify(body)
    })
        .then(res => res.json())
        .catch(err => console.error(err))
}

async function getModel() {
    await request("/api/candidates/model", "POST", null)
        .then(data => localStorage.setItem("model", JSON.stringify(data)))
    model = JSON.parse(localStorage.getItem("model"))
    localStorage.removeItem("model");
}


function vectorize(candidate) {
    let candidateVector = [];
    candidateVector[0] = model.educations[candidate.education];
    candidateVector[1] = candidate.experience;
    candidateVector[2] = model.locations[candidate.location];
    candidateVector[3] = model.jobTitles[candidate.jobTitle];
    candidateVector[4] = candidate.age;
    candidateVector[5] = model.genders[candidate.gender];
    candidateVector[6] = 0;
    return candidateVector;
}

function predict(e) {
    e.preventDefault();
    const requestBody = {
        education: formJson.education.value,
        experience: formJson.experience.value,
        location: formJson.location.value,
        jobTitle: formJson.jobTitle.value,
        age: formJson.age.value,
        gender: formJson.gender.value
    };

    const candidateVector = vectorize(requestBody);

    let salary = model.params[0];
    for(let i = 0; i < model.params.length - 1; ++i) {
        salary += model.params[i + 1] * candidateVector[i];
    }

    drawPrediction(salary);
}

function drawPrediction(salary) {
    const displayElement = document.getElementById("salary-prediction-div");
    displayElement.innerHTML = `
                <h2>Predicted Salary: $${salary.toFixed(2)}</h2>
                <h2>15% Salary Range: $${(salary * .85).toFixed(2)} - $${(salary * 1.15).toFixed(2)}</h2>
            `;
}

getModel();
formJson.predictionButton.addEventListener("click", (e) => predict(e))
