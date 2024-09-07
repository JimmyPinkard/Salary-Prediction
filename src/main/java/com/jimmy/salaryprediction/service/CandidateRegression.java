package com.jimmy.salaryprediction.service;

import com.jimmy.salaryprediction.model.Candidate;
import com.jimmy.salaryprediction.model.CandidateVector;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.spark.ml.regression.DecisionTreeRegressor;
import org.apache.spark.ml.util.DatasetUtils;
import org.apache.spark.network.protocol.Encoders;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Arrays;

public class CandidateRegression extends OLSMultipleLinearRegression {
    /*
    private SparkSession sparkSession;

    public CandidateRegression() {
        super();
        this.sparkSession = SparkSession.builder().getOrCreate();
    }

    public void train() {
        Dataset<Row> candidateDataset = sparkSession.sql("SELECT * FROM Candidate");
        candidateDataset.show();
    }
    */

    /*
    private int epochs;
    private double beta;
    private double learningRate;
    private double[] weights;

    public CandidateRegression() {
        this.epochs = 1;
        this.beta = 0.0;
        this.learningRate = 0.0001;
        this.weights = new double[6];
    }

    public double predict(CandidateVector candidateVector) {
        double result = 0.0;
        result += weights[0] * candidateVector.getEducation();
        result += weights[1] * candidateVector.getExperience();
        result += weights[2] * candidateVector.getLocation();
        result += weights[3] * candidateVector.getJobTitle();
        result += weights[4] * candidateVector.getAge();
        result += weights[5] * candidateVector.getGender();
        return result + beta;
    }

    public void train(CandidateVector[] candidates) {
        for (CandidateVector candidate : candidates) {
            double error = candidate.getSalary() - predict(candidate);
            weights[0] += learningRate * (error - candidate.getEducation());
            weights[1] += learningRate * (error - candidate.getExperience());
            weights[2] += learningRate * (error - candidate.getLocation());
            weights[3] += learningRate * (error - candidate.getJobTitle());
            weights[4] += learningRate * (error - candidate.getAge());
            weights[5] += learningRate * (error - candidate.getGender());
            beta += learningRate * error;
        }
    }
    */

    /*
    public int[][] transposeMatrix(int[][] matrix) {
        int[][] transposedMatrix = new int[matrix[0].length][matrix.length];
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                transposedMatrix[j][i] = matrix[i][j];
            }
        }
        return transposedMatrix;
    }

    public int[][] matrixProduct(int[][] A, int[][] B) {
        int[][] matrix = new int[A.length][B[0].length];
        for(int row = 0; row < A.length; row++) {
            for(int column = 0; column < B[0].length; column++) {
                int sum = 0;
                for(int k = 0; k < A[0].length; k++) {
                    sum += A[row][k] * B[k][column];
                }
                matrix[row][column] = sum;
            }
        }
        return matrix;
    }

    public int[] matrixVectorProduct(int[][] matrix, int[] vector) {
        int[] product = new int[vector.length];
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                product[i] = matrix[i][j] * vector[i];
            }
        }
        return product;
    }

    public double[][] augmentMatrix(int[][] matrix) {
        double[][] augmentedMatrix = new double[matrix[0].length][matrix.length * 2];
        for(int i = 0; i < augmentedMatrix.length; i++) {
            for(int j = 0; j < augmentedMatrix[0].length; j++) {
                if(j == matrix[0].length) {
                    augmentedMatrix[i][matrix[0].length + i] = 1.0;
                    break;
                }
                augmentedMatrix[i][j] = matrix[i][j];
            }
        }
        return augmentedMatrix;
    }

    public double[][] gauss_jordan(double[][] a, int n)
    {
        int i, j, k = 0, c, flag = 0, m = 0;
        double pro = 0;
        double[][] result = new double[a.length][a[0].length];
        System.arraycopy(a, 0, result, 0, a.length);
        // Performing elementary operations
        for (i = 0; i < n; i++)
        {
            if (result[i][i] == 0)
            {
                c = 1;
                while ((i + c) < n && result[i + c][i] == 0)
                    c++;
                if ((i + c) == n)
                {
                    flag = 1;
                    break;
                }
                for (j = i, k = 0; k <= n; k++)
                {
                    double temp = result[j][k];
                    result[j][k] = result[j+c][k];
                    result[j+c][k] = temp;
                }
            }

            for (j = 0; j < n; j++)
            {

                // Excluding all i == j
                if (i != j)
                {

                    // Converting Matrix to reduced row
                    // echelon form(diagonal matrix)
                    double p = result[j][i] / result[i][i];

                    for (k = 0; k <= n; k++)
                        result[j][k] = result[j][k] - (result[i][k]) * p;
                }
            }
        }
        return result;
    }
     */

}
