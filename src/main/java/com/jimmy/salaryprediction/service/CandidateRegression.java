package com.jimmy.salaryprediction.service;

import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

public class CandidateRegression extends OLSMultipleLinearRegression {
    private double[] betas;

    public void train(double[][] candidateMatrix, double[] salaries) {
        this.newSampleData(salaries, candidateMatrix);
        this.betas = this.calculateBeta().toArray();
    }

    public double predict(double[] candidateVector) {
        double result = 0;
        for(int i = 0; i < betas.length - 1; i++) {
            result += betas[i + 1] * candidateVector[i];
        }
        result += betas[0];
        return result;
    }
}
