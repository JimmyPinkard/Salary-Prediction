package com.jimmy.salaryprediction.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public abstract class BaseControllerTest {
    private TestRestTemplate testRestTemplate;
    private String url;
    @LocalServerPort
    private int port;

    @BeforeEach
    protected void setUp() {
        testRestTemplate = new TestRestTemplate();
        this.url = "https://localhost:" + port;
    }

    protected <Request> ResponseEntity<Map> failingPostRequest(String endpoint, Request body) {
        return testRestTemplate.postForEntity(url + endpoint, body, Map.class);
    }
}
