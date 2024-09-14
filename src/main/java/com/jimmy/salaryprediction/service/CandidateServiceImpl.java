package com.jimmy.salaryprediction.service;

import com.jimmy.salaryprediction.controller.request.CandidateRequest;
import com.jimmy.salaryprediction.model.Candidate;
import com.jimmy.salaryprediction.model.CandidateVector;
import com.jimmy.salaryprediction.repository.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;
    private CandidateRegression candidateRegression;
    private final Map<String, Integer> genders;
    private final Map<String, Integer> locations;
    private final Map<String, Integer> educations;
    private final Map<String, Integer> jobTitles;


    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
        this.genders = new HashMap<>();
        this.locations = new HashMap<>();
        this.educations = new HashMap<>();
        this.jobTitles = new HashMap<>();
        this.candidateRegression = new CandidateRegression();
        loadMaps();
        trainModel();
    }

    @Override
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    @Override
    public double predictSalary(CandidateRequest candidateRequest) {
        return candidateRegression.predict(fromCandidateRequest(candidateRequest).toDoubleArray());
    }

    public void trainModel() {
        Candidate[] candidates = candidateRepository.withoutOutliers();
        CandidateVector[] vectors = vectorizeCandidates(List.of(candidates)).toArray(CandidateVector[]::new);
        double[][] candidateMatrix = CandidateVector.toDoubleMatrix(vectors);
        double[] salaries = CandidateVector.toSalaryOnlyVector(vectors);
        candidateRegression.train(candidateMatrix, salaries);
    }


    public List<CandidateVector> vectorizeCandidates(List<Candidate> candidates) {
        List<CandidateVector> vectors = new ArrayList<>(candidates.size());
        for (Candidate candidate : candidates) {
            vectors.add(fromCandidate(candidate));
        }
        return vectors;
    }

    public CandidateVector fromCandidate(Candidate candidate) {
        CandidateVector candidateVector = new CandidateVector();
        candidateVector.setEducation(educations.get(candidate.getEducation()));
        candidateVector.setExperience(candidate.getExperience());
        candidateVector.setLocation(locations.get(candidate.getLocation()));
        candidateVector.setJobTitle(jobTitles.get(candidate.getJobTitle()));
        candidateVector.setAge(candidate.getAge());
        candidateVector.setGender(genders.get(candidate.getGender()));
        candidateVector.setSalary(candidate.getSalary());
        return candidateVector;
    }

    public CandidateVector fromCandidateRequest(CandidateRequest candidate) {
        CandidateVector candidateVector = new CandidateVector();
        candidateVector.setEducation(educations.get(candidate.getEducation()));
        candidateVector.setExperience(candidate.getExperience());
        candidateVector.setLocation(locations.get(candidate.getLocation()));
        candidateVector.setJobTitle(jobTitles.get(candidate.getJobTitle()));
        candidateVector.setAge(candidate.getAge());
        candidateVector.setGender(genders.get(candidate.getGender()));
        candidateVector.setSalary(0);
        return candidateVector;
    }

    public void loadMaps() {
        String[] workingList = candidateRepository.listEducationTypes();
        for(int i = 0; i < workingList.length; ++i) {
            educations.put(workingList[i], i + 1);
        }
        workingList = candidateRepository.listLocations();
        for(int i = 0; i < workingList.length; ++i) {
            locations.put(workingList[i], i + 1);
        }
        workingList = candidateRepository.listJobTitles();
        for(int i = 0; i < workingList.length; ++i) {
            jobTitles.put(workingList[i], i + 1);
        }
        workingList = candidateRepository.listGenders();
        for(int i = 0; i < workingList.length; ++i) {
            genders.put(workingList[i], i + 1);
        }
    }
}
