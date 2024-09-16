package com.jimmy.salaryprediction.service;

import com.jimmy.salaryprediction.controller.request.CandidateRequest;
import com.jimmy.salaryprediction.controller.response.CandidateResponse;
import com.jimmy.salaryprediction.model.Candidate;
import com.jimmy.salaryprediction.model.CandidateVector;
import com.jimmy.salaryprediction.repository.CandidateRepository;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

@Service
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;
    private final OLSMultipleLinearRegression multipleLinearRegression;
    private double[] params;


    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
        this.multipleLinearRegression = new OLSMultipleLinearRegression();
        trainModel();
    }

    @Override
    public Candidate[] getAllCandidates() {
        return candidateRepository.findAll().toArray(Candidate[]::new);
    }

    @Override
    public CandidateResponse[] getAllCandidateResponses() {
        Candidate[] candidates = getAllCandidates();
        CandidateResponse[] candidateResponses = new CandidateResponse[candidates.length];
        for (int i = 0; i < candidates.length; i++) {
            candidateResponses[i] = new CandidateResponse(candidates[i]);
        }
         return candidateResponses;
    }

    @Override
    public CandidateResponse predictSalary(CandidateRequest candidateRequest) {
        CandidateVector candidateVector = new CandidateVector(candidateRequest);
        double[] vec = candidateVector.toDoubleArray();
        double result = 0;
        for(int i = 0; i < params.length - 1; i++) {
            result += params[i + 1] * vec[i];
        }
        result = Precision.round(result + params[0], 2);
        CandidateResponse candidateResponse = new CandidateResponse(candidateRequest, result);
        return candidateResponse;
    }

    @Override
    public void trainModel() {
        Candidate[] candidates = getAllCandidates();
        CandidateVector[] vectors = vectorizeCandidates(candidates);
        double[][] candidateMatrix = CandidateVector.toDoubleMatrix(vectors);
        double[] salaries = CandidateVector.toSalaryOnlyVector(vectors);
        multipleLinearRegression.newSampleData(salaries, candidateMatrix);
        this.params = multipleLinearRegression.estimateRegressionParameters();
    }

    public CandidateVector[] vectorizeCandidates(Candidate[] candidates) {
        CandidateVector[] vectors = new CandidateVector[candidates.length];
        for (int i = 0; i < candidates.length; i++) {
            vectors[i] = new CandidateVector(candidates[i]);
        }
        return vectors;
    }


}
