package com.jimmy.salaryprediction.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public abstract class BaseControllerTest {
    private RestTemplate restTemplate;
    private String url;
    @LocalServerPort
    private int port;

    @BeforeEach
    protected void setUp() throws Exception {
        this.restTemplate = new RestTemplate();
        this.url = "https://127.0.0.1:" + port;
        //this.url = "https://localhost:" + port;
    }

    protected <Request, Response> ResponseEntity<Response> makePostRequest(String endpoint, Request body, Class<Response> responseClass) {
        return restTemplate.postForEntity(url + endpoint, body, responseClass);
    }
}
