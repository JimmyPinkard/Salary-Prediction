package com.jimmy.salaryprediction.service;

import com.jimmy.salaryprediction.controller.request.CandidateRequest;
import com.jimmy.salaryprediction.controller.response.CandidateModelResponse;
import com.jimmy.salaryprediction.controller.response.CandidateResponse;
import com.jimmy.salaryprediction.model.Candidate;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.Reader;

@Service
public class CandidateServiceImpl implements CandidateService {
    private double[] params;

    public CandidateServiceImpl() {
        trainModel();
    }

    @Override
    public Candidate[] getAllCandidates() {
        String path = System.getenv().get("CSV_FILE");
        try (Reader reader = new FileReader(path)) {
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
    public CandidateModelResponse getModel() {
        return new CandidateModelResponse(params);
    }

    @Override
    public CandidateResponse predictSalary(CandidateRequest candidateRequest) {
        Candidate candidateVector = new Candidate(candidateRequest);
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
        OLSMultipleLinearRegression multipleLinearRegression = new OLSMultipleLinearRegression();
        Candidate[] vectors = getAllCandidates();
        double[][] candidateMatrix = Candidate.toDoubleMatrix(vectors);
        double[] salaries = Candidate.toSalaryOnlyVector(vectors);
        multipleLinearRegression.newSampleData(salaries, candidateMatrix);
        this.params = multipleLinearRegression.estimateRegressionParameters();
    }
}
