package com.jimmy.salaryprediction.controller;

import com.jimmy.salaryprediction.controller.request.CandidateRequest;
import com.jimmy.salaryprediction.controller.response.CandidateResponse;
import com.jimmy.salaryprediction.model.Candidate;
import com.jimmy.salaryprediction.service.CandidateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/candidates")
@CrossOrigin(origins = {"https://localhost:3000/", "https://client:3000/", "https://127.0.0.1:3000"})
public class CandidateController {
    private CandidateService candidateService;

    @PostMapping("/all")
    public ResponseEntity<CandidateResponse[]> listCandidates() {
        return ResponseEntity.ok(candidateService.getAllCandidateResponses());
    }

    @PostMapping("/predict-salary")
    public ResponseEntity<CandidateResponse> predictSalary(@RequestBody CandidateRequest candidateRequest) {
        CandidateResponse candidateResponse = candidateService.predictSalary(candidateRequest);
        return ResponseEntity.ok(candidateResponse);
    }
}
