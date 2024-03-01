package com.jimmy.salaryprediction.service;

import com.jimmy.salaryprediction.repository.CandidateRepository;
import lombok.AllArgsConstructor;
import org.jvnet.hk2.annotations.Service;

@Service
@AllArgsConstructor
public class CandidateServiceImpl implements CandidateService {
    private CandidateRepository candidateRepository;
}
