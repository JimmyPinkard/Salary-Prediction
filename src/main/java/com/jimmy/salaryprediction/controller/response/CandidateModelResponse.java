package com.jimmy.salaryprediction.controller.response;

import com.jimmy.salaryprediction.model.Candidate;
import lombok.Data;

import java.util.Map;

@Data
public class CandidateModelResponse {
    private final double[] params;
    private final Map<String, Integer> genders = Candidate.genders;
    private final Map<String, Integer> locations = Candidate.locations;
    private final Map<String, Integer> educations = Candidate.educations;
    private final Map<String, Integer> jobTitles = Candidate.jobTitles;

    public CandidateModelResponse(double[] params) {
        this.params = params;
    }
}
