package com.jimmy.salaryprediction.controller;

import com.jimmy.salaryprediction.controller.request.CandidateRequest;
import com.jimmy.salaryprediction.controller.response.CandidateModelResponse;
import com.jimmy.salaryprediction.controller.response.CandidateResponse;
import com.jimmy.salaryprediction.service.CandidateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/candidates")
public class CandidateController {
    private CandidateService candidateService;

    @PostMapping("/model")
    public ResponseEntity<CandidateModelResponse> getModel() {
        return ResponseEntity.ok(candidateService.getModel());
    }

    @PostMapping("/predict-salary")
    public ResponseEntity<CandidateResponse> predictSalary(@RequestBody CandidateRequest candidateRequest) {
        CandidateResponse candidateResponse = candidateService.predictSalary(candidateRequest);
        return ResponseEntity.ok(candidateResponse);
    }
}
