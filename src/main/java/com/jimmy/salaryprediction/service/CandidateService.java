package com.jimmy.salaryprediction.service;

import com.jimmy.salaryprediction.controller.request.CandidateRequest;
import com.jimmy.salaryprediction.model.Candidate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CandidateService {
    Candidate[] getAllCandidates();
    void trainModel();
    double predictSalary(CandidateRequest candidateRequest);
}
