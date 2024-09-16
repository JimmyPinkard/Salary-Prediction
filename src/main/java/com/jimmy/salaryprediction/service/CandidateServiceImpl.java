package com.jimmy.salaryprediction.service;

import com.jimmy.salaryprediction.controller.request.CandidateRequest;
import com.jimmy.salaryprediction.controller.response.CandidateResponse;
import com.jimmy.salaryprediction.model.Candidate;
import com.jimmy.salaryprediction.model.CandidateVector;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Paths;

@Service
public class CandidateServiceImpl implements CandidateService {
    private final OLSMultipleLinearRegression multipleLinearRegression;
    private double[] params;


    public CandidateServiceImpl() {
        this.multipleLinearRegression = new OLSMultipleLinearRegression();
        trainModel();
    }

    @Override
    public Candidate[] getAllCandidates() {
        try (Reader reader = new FileReader(String.valueOf(Paths.get(ClassLoader.getSystemResource("Candidate.csv").toURI())))) {
            CsvToBean<Candidate> csvToBean  = new CsvToBeanBuilder<Candidate>(reader)
                    .withType(Candidate.class)
                    .build();
            return csvToBean.parse().toArray(Candidate[]::new);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
        return new CandidateResponse(candidateRequest, result);
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
