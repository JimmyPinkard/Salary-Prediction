const formJson = {
    education: document.getElementById("education"),
    experience: document.getElementById("experience"),
    location: document.getElementById("location"),
    jobTitle: document.getElementById("job-title"),
    age: document.getElementById("age"),
    gender: document.getElementById("gender"),
    predictionButton: document.getElementById("predictionButton")
}

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

    request("/api/candidates/predict-salary", "POST", requestBody)
        .then(data => {
            const displayElement = document.getElementById("salary-prediction-div");
            displayElement.innerHTML = `
                <h2>Predicted Salary: $${data.salary}</h2>
                <h2>15% Salary Range: $${(data.salary * .85).toFixed(2)} - $${(data.salary * 1.15).toFixed(2)}</h2>
            `;
        })
}

formJson.predictionButton.addEventListener("click", (e) => predict(e))
