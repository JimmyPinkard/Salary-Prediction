package com.jimmy.salaryprediction.service;

import com.jimmy.salaryprediction.controller.request.CandidateRequest;
import com.jimmy.salaryprediction.controller.response.CandidateResponse;
import com.jimmy.salaryprediction.model.Candidate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CandidateService {
    Candidate[] getAllCandidates();
    void trainModel();
    CandidateResponse predictSalary(CandidateRequest candidateRequest);
}
