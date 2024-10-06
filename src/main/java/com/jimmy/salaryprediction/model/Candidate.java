package com.jimmy.salaryprediction.model;

import com.jimmy.salaryprediction.controller.request.CandidateRequest;
import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Candidate {
    @CsvBindByName
    private String education;
    @CsvBindByName
    private int experience;
    @CsvBindByName
    private String location;
    @CsvBindByName(column = "Job_Title")
    private String jobTitle;
    @CsvBindByName
    private int age;
    @CsvBindByName
    private String gender;
    @CsvBindByName
    private double salary;

    public Candidate(CandidateRequest candidateRequest) {
        setEducation(candidateRequest.getEducation());
        setExperience(candidateRequest.getExperience());
        setLocation(candidateRequest.getLocation());
        setJobTitle(candidateRequest.getJobTitle());
        setAge(candidateRequest.getAge());
        setGender(candidateRequest.getGender());
        setSalary(0);
    }

    public double[] toDoubleArray() {
        return new double[]{educations.get(education), experience, locations.get(location), jobTitles.get(jobTitle), age, genders.get(gender)};
    }

    public static double[][] toDoubleMatrix(Candidate[] candidates) {
        double[][] matrix = new double[candidates.length][6];
        for(int i = 0; i < candidates.length; i++) {
            matrix[i] = candidates[i].toDoubleArray();
        }
        return matrix;
    }

    public static double[] toSalaryOnlyVector(Candidate[] candidates) {
        double[] vector = new double[candidates.length];
        for(int i = 0; i < candidates.length; i++) {
            vector[i] = candidates[i].getSalary();
        }
        return vector;
    }

    public static final Map<String, Integer> genders = new HashMap<>(4);
    public static final Map<String, Integer> locations = new HashMap<>(3);
    public static final Map<String, Integer> educations = new HashMap<>(4);
    public static final Map<String, Integer> jobTitles = new HashMap<>(4);

    static {
        loadMaps();
    }

    public static void loadMaps() {
        educations.put("High School", 1);
        educations.put("Bachelor", 2);
        educations.put("Master", 3);
        educations.put("PhD", 4);

        locations.put("Rural", 1);
        locations.put("Suburban", 2);
        locations.put("Urban", 3);

        jobTitles.put("Analyst", 1);
        jobTitles.put("Engineer", 2);
        jobTitles.put("Manager", 3);
        jobTitles.put("Director", 4);

        genders.put("Male", 2);
        genders.put("Female", 1);
    }
}
