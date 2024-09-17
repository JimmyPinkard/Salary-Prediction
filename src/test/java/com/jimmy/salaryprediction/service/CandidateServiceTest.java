package com.jimmy.salaryprediction.service;

import com.jimmy.salaryprediction.controller.request.CandidateRequest;
import com.jimmy.salaryprediction.controller.response.CandidateResponse;
import com.jimmy.salaryprediction.model.Candidate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class CandidateServiceTest {
    @Autowired
    private CandidateService candidateService;

    @Test
    public void testGetAllCandidates() {
        assertNotNull(candidateService.getAllCandidates());
    }

    @Test
    public void testPrediction() {
        /*
        CandidateRequest candidateRequest = new CandidateRequest("Master", 30, "Suburban", "Engineer", 49, "Male");
        CandidateResponse pred = candidateService.predictSalary(candidateRequest);
        System.out.println(pred.getSalary());
        */
        CandidateRequest candidateRequest = new CandidateRequest("High School", 28, "Suburban", "Engineer", 49, "Male");
        CandidateResponse pred = candidateService.predictSalary(candidateRequest);
        System.out.println(pred.getSalary());
    }

    @Test
    public void testAllPrediction() {
        Candidate[] candidates = candidateService.getAllCandidates();
        CandidateRequest[] candidateRequests = new CandidateRequest[candidates.length];
        for (int i = 0; i < candidates.length; i++) {
            Candidate candidate = candidates[i];
            candidateRequests[i] = new CandidateRequest(candidate);
        }
        candidateService.trainModel();
        double averagePercentage = 0;
        int start = 0, end = 1000;
        double lowest = Double.MAX_VALUE;
        double highest = Double.MIN_VALUE;
        int tenPercent = 0;
        for(int i = start; i < end; i++) {
            Candidate vector = candidates[i];
            CandidateResponse pred = candidateService.predictSalary(candidateRequests[i]);
            double prediction = pred.getSalary();
            double percentage = 100 * (prediction / vector.getSalary());
            if(percentage >= 90 && percentage <= 110) {
                ++tenPercent;
            }
            lowest = Math.min(lowest, percentage);
            highest = Math.max(highest, percentage);
            averagePercentage += percentage;
            System.out.printf("%d. Expected %f = Predicted %f = Percentage %f\n", i + 1, vector.getSalary(), prediction, percentage);
        }
        averagePercentage /= end - start;
        System.out.printf("Average Percentage of Accuracy: %f\n", averagePercentage);
        System.out.printf("Within 10%%: %d\n", tenPercent);
        System.out.printf("Lowest Inaccuracy: %f\n", lowest);
        System.out.printf("Highest Inaccuracy: %f\n", highest);
    }

}
