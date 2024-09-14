package com.jimmy.salaryprediction.controller;

import com.jimmy.salaryprediction.controller.request.CandidateRequest;
import com.jimmy.salaryprediction.controller.response.CandidateResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CandidateControllerTest extends BaseControllerTest {
    /*
    @Test
    public void testPrediction() {
        CandidateRequest request = new CandidateRequest();
        String endpoint = "/api/candidates/predict-salary";
        ResponseEntity<CandidateResponse> response = makePostRequest(endpoint, request, CandidateResponse.class);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    */
}
