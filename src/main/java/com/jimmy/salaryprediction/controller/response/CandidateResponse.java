package com.jimmy.salaryprediction.controller.response;

import com.jimmy.salaryprediction.controller.request.CandidateRequest;
import com.jimmy.salaryprediction.model.Candidate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateResponse extends CandidateRequest {
    private double salary;

    public CandidateResponse(Candidate candidate) {
        super(candidate);
        setSalary(candidate.getSalary());
    }

    public CandidateResponse(CandidateRequest c, double salary) {
        super(c.getEducation(), c.getExperience(), c.getLocation(), c.getJobTitle(), c.getAge(), c.getGender());
        setSalary(salary);
    }
}
