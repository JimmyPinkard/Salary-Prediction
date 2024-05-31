package com.jimmy.salaryprediction.controller;

import com.jimmy.salaryprediction.model.Candidate;
import com.jimmy.salaryprediction.service.CandidateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/candidates")
@CrossOrigin(origins = {"http://localhost:3000/", "http://client:3000/"})
public class CandidateController {
    private CandidateService candidateService;

    @PostMapping("/all")
    public ResponseEntity<List<Candidate>> listCandidates() {
        return ResponseEntity.ok(candidateService.getAllCandidates());
    }
}
