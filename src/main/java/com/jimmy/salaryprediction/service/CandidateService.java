package com.jimmy.salaryprediction.service;

import com.jimmy.salaryprediction.model.Candidate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CandidateService {
    List<Candidate> getAllCandidates();
}
