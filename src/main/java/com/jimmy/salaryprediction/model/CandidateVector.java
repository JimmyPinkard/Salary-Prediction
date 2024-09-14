package com.jimmy.salaryprediction.model;

import com.jimmy.salaryprediction.controller.request.CandidateRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CandidateVector {
    private static final Map<String, Integer> genders = new HashMap<>();
    private static final Map<String, Integer> locations = new HashMap<>();
    private static final Map<String, Integer> educations = new HashMap<>();
    private static final Map<String, Integer> jobTitles = new HashMap<>();

    static {
        loadMaps();
    }

    public double[] toDoubleArray() {
        return new double[]{education, experience, location, jobTitle, age, gender};
    }

    public static double[][] toDoubleMatrix(CandidateVector[] candidateVectors) {
        double[][] matrix = new double[candidateVectors.length][6];
        for(int i = 0; i < candidateVectors.length; i++) {
            matrix[i] = candidateVectors[i].toDoubleArray();
        }
        return matrix;
    }

    public static double[] toSalaryOnlyVector(CandidateVector[] candidateVectors) {
        double[] vector = new double[candidateVectors.length];
        for(int i = 0; i < candidateVectors.length; i++) {
            vector[i] = candidateVectors[i].getSalary();
        }
        return vector;
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

    private int education;
    private int experience;
    private int location;
    private int jobTitle;
    private int age;
    private int gender;
    private double salary;

    public CandidateVector(Candidate candidate) {
        genVector(candidate.getEducation(), candidate.getExperience(), candidate.getLocation(), candidate.getJobTitle(), candidate.getAge(), candidate.getGender(), candidate.getSalary());
    }

    public CandidateVector(CandidateRequest request) {
        genVector(request.getEducation(), request.getExperience(), request.getLocation(), request.getJobTitle(), request.getAge(), request.getGender(), 0);
    }

    private void genVector(String education, int experience, String location, String jobTitle, int age, String gender, double salary) {
        this.setEducation(educations.get(education));
        this.setExperience(experience);
        this.setLocation(locations.get(location));
        this.setJobTitle(jobTitles.get(jobTitle));
        this.setAge(age);
        this.setGender(genders.get(gender));
        this.setSalary(salary);
    }
}
