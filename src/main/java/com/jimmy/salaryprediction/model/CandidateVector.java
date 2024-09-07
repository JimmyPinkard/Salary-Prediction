package com.jimmy.salaryprediction.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Arrays;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CandidateVector {
    private int education;
    private int experience;
    private int location;
    private int jobTitle;
    private int age;
    private int gender;
    private double salary;

    public double[] toDoubleArray() {
        return new double[]{education, experience, location, jobTitle, age, gender};
    }

    public int[] toSalariedDoubleArray() {
        return new int[]{education, experience, location, jobTitle, age, gender, (int)salary};
    }

    public static double[][] toDoubleMatrix(CandidateVector[] candidateVectors) {
        double[][] matrix = new double[candidateVectors.length][7];
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
}
