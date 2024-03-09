package com.jimmy.salaryprediction.service;

import com.jimmy.salaryprediction.model.Candidate;
import com.jimmy.salaryprediction.repository.CandidateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CandidateServiceImpl implements CandidateService {
    private CandidateRepository candidateRepository;

    @Override
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }
}
