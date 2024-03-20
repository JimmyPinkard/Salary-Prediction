package com.jimmy.salaryprediction.controller;

import com.jimmy.salaryprediction.model.Candidate;
import com.jimmy.salaryprediction.service.CandidateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = {"https://localhost:3000", "https://client:3000/"})
public class CandidateController {
    private CandidateService candidateService;

    @PostMapping("/api/candidates/all")
    public ResponseEntity<List<Candidate>> listCandidates() {
        return ResponseEntity.ok(candidateService.getAllCandidates());
    }
}
