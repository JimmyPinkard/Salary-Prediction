package com.jimmy.salaryprediction.service;

import com.jimmy.salaryprediction.controller.request.CandidateRequest;
import com.jimmy.salaryprediction.model.CandidateVector;
import com.jimmy.salaryprediction.repository.CandidateRepository;
import org.apache.commons.math3.stat.regression.MultipleLinearRegression;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class CandidateServiceTest {
    @Autowired
    private CandidateServiceImpl candidateService;
    @Autowired
    private CandidateRepository candidateRepository;

    @Test
    public void testGetAllCandidates() {
        assertNotNull(candidateService.getAllCandidates());
    }

    @Test
    public void testCandidatesVectorization() {

        List<CandidateVector> vectors = candidateService.vectorizeCandidates(candidateService.getAllCandidates());
        assertNotNull(vectors);
        for(CandidateVector vector : vectors) {
            System.out.println(vector);
        }
    }

    @Test
    public void testPrediction() {
        CandidateRequest candidateRequest = new CandidateRequest("Master", 30, "Suburban", "Engineer", 49, "Male");
        double pred = candidateService.predictSalary(candidateRequest);
        System.out.println(pred);
    }

    @Test
    public void testTransposeProduct() {
        CandidateVector[] candidateVectors = Arrays.stream(candidateRepository.withoutOutliers())
                .map(candidate -> candidateService.fromCandidate(candidate))
                .toArray(CandidateVector[]::new);

        double[][] candidateMatrix = CandidateVector.toDoubleMatrix(candidateVectors);
        double[] salaries = CandidateVector.toSalaryOnlyVector(candidateVectors);
        CandidateRegression multipleLinearRegression = new CandidateRegression();
        multipleLinearRegression.train(candidateMatrix, salaries);
        candidateVectors = candidateService.getAllCandidates()
                .stream()
                .map(candidate -> candidateService.fromCandidate(candidate))
                .toArray(CandidateVector[]::new);
        double averagePercentage = 0;
        int start = 0, end = 1000;
        double lowest = Double.MAX_VALUE;
        double highest = Double.MIN_VALUE;
        int tenPercent = 0;
        for(int i = start; i < end; i++) {
            CandidateVector vector = candidateVectors[i];
            double prediction = multipleLinearRegression.predict(vector.toDoubleArray());
            double percentage = 100 * (prediction / vector.getSalary());
            if(percentage >= 90 && percentage <= 110) {
                ++tenPercent;
            }
            lowest = Math.min(lowest, percentage);
            highest = Math.max(highest, percentage);
            averagePercentage += percentage;
            //System.out.printf("%d. Expected %f = Predicted %f = Percentage %f\n", i + 1, vector.getSalary(), prediction, percentage);
        }
        averagePercentage /= end - start;
        System.out.printf("Average Percentage of Accuracy: %f\n", averagePercentage);
        System.out.printf("Within 10%%: %d\n", tenPercent);
        System.out.printf("Lowest Inaccuracy: %f\n", lowest);
        System.out.printf("Highest Inaccuracy: %f\n", highest);
        CandidateRequest candidateRequest = new CandidateRequest("High School", 28, "Suburban", "Engineer", 49, "Male");
        double prediction = candidateService.predictSalary(candidateRequest);
        System.out.println(prediction);
    }

}
