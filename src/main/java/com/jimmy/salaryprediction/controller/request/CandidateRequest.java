package com.jimmy.salaryprediction.controller.request;

import com.jimmy.salaryprediction.model.Candidate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateRequest {
    private String education;
    private int experience;
    private String location;
    private String jobTitle;
    private int age;
    private String gender;

    public CandidateRequest(Candidate candidate) {
        this.setEducation(candidate.getEducation());
        this.setExperience(candidate.getExperience());
        this.setLocation(candidate.getLocation());
        this.setJobTitle(candidate.getJobTitle());
        this.setAge(candidate.getAge());
        this.setGender(candidate.getGender());
    }
}
