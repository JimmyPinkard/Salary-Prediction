package com.jimmy.salaryprediction.controller;

import com.jimmy.salaryprediction.controller.request.LoginRequest;
import com.jimmy.salaryprediction.exceptions.ApiError;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserControllerTest extends BaseControllerTest {
    @Autowired
    private UserController userController;

    @Test
    public void testLoginUserDoesNotExist() throws Exception {
        LoginRequest loginRequest = new LoginRequest("not-a-user", "password");
        //ResponseEntity<Map> responseEntity = failingPostRequest("/api/users/login", loginRequest);
        //System.out.println(responseEntity);
        assertThrows(ApiError.class, () -> userController.login(loginRequest));
    }
}
