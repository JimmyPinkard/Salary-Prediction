package com.jimmy.salaryprediction.service;

import com.jimmy.salaryprediction.controller.request.CandidateRequest;
import com.jimmy.salaryprediction.model.CandidateVector;
import org.apache.commons.math3.stat.regression.MultipleLinearRegression;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class CandidateServiceTest {
    @Autowired
    private CandidateServiceImpl candidateService;

    @Test
    public void testGetAllCandidates() {
        assertNotNull(candidateService.getAllCandidates());
    }

    @Test
    public void testCandidatesVectorization() {

        List<CandidateVector> vectors = candidateService.vectorizeCandidates(candidateService.getAllCandidates());
        assertNotNull(vectors);
        for(CandidateVector vector : vectors) {
            System.out.println(vector);
        }
    }

    @Test
    public void testPrediction() {
        CandidateRequest candidateRequest = new CandidateRequest("Master", 30, "Suburban", "Engineer", 49, "Male");
        double pred = candidateService.predictSalary(candidateRequest);
        System.out.println(pred);
    }

    @Test
    public void testTransposeProduct() {
        CandidateVector[] candidateVectors = candidateService.getAllCandidates()
                .stream()
                .map(candidate -> candidateService.fromCandidate(candidate))
                .toArray(CandidateVector[]::new);
        double[][] candidateMatrix = CandidateVector.toDoubleMatrix(candidateVectors);
        double[] salaries = CandidateVector.toSalaryOnlyVector(candidateVectors);
        CandidateRegression multipleLinearRegression = new CandidateRegression();
        multipleLinearRegression.train();
        /*
        multipleLinearRegression.newSampleData(salaries, candidateMatrix);
        System.out.printf("\n%f = %f\n", multipleLinearRegression.predict(), salaries[999]);
        double[] params = multipleLinearRegression.estimateRegressionParameters();
        System.out.println(Arrays.toString(multipleLinearRegression.estimateResiduals()));
        */
        /*
        CandidateRegression candidateRegression = new CandidateRegression();
        int[][] transposeMatrix = candidateRegression.transposeMatrix(candidateMatrix);
        int[][] product = candidateRegression.matrixProduct(transposeMatrix, candidateMatrix);
        for(int[] vector : product) {
            System.out.println(Arrays.toString(vector));
        }
        double[][] augmentedMatrix = candidateRegression.augmentMatrix(product);
        double[][] solved = candidateRegression.gauss_jordan(augmentedMatrix, 6);
        for(double[] vector : solved) {
            System.out.println(Arrays.toString(vector));
        }
        */
        /* Symetric
        double[][] transposeOfProductMatrix = candidateRegression.transposeMatrix(product);
        assertEquals(product.length, transposeOfProductMatrix.length);
        assertEquals(product[0].length, transposeOfProductMatrix[0].length);
        for(int i = 0; i < transposeOfProductMatrix.length; i++) {
            assertArrayEquals(product[i], transposeOfProductMatrix[i], 0.001);
        }
        */
        /*

        int[] fits = candidateRegression.matrixVectorProduct(product, salaries);
        int[] salariesProduct = candidateRegression.matrixVectorProduct(transposeMatrix, salaries);
        System.out.println(Arrays.toString(fits));
        System.out.println(Arrays.toString(salariesProduct));
        */
    }

}
