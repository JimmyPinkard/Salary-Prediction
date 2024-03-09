package com.jimmy.salaryprediction.controller;

import com.jimmy.salaryprediction.model.Candidate;
import com.jimmy.salaryprediction.service.CandidateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class ViewController {

    private CandidateService candidateService;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @PostMapping("/api/candidate/all")
    public ResponseEntity<List<Candidate>> listCandidates() {
        return ResponseEntity.ok(candidateService.getAllCandidates());
    }
}
