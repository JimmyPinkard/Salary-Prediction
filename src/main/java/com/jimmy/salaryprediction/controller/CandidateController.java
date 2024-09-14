package com.jimmy.salaryprediction.controller;

import com.jimmy.salaryprediction.controller.request.CandidateRequest;
import com.jimmy.salaryprediction.model.Candidate;
import com.jimmy.salaryprediction.service.CandidateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/candidates")
@CrossOrigin(origins = {"http://localhost:3000/", "http://client:3000/", "http://127.0.0.1:3000"})
public class CandidateController {
    private CandidateService candidateService;

    @PostMapping("/all")
    public ResponseEntity<List<Candidate>> listCandidates() {
        return ResponseEntity.ok(candidateService.getAllCandidates());
    }

    @PostMapping("/predict-salary")
    public ResponseEntity<String> predictSalary(@RequestBody CandidateRequest candidateRequest) {
        double predictedSalary = candidateService.predictSalary(candidateRequest);
        return ResponseEntity.ok("{predictedSalary: " + predictedSalary + "}");
    }
}
