package com.jimmy.salaryprediction.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateRequest {
    private String education;
    private int experience;
    private String location;
    private String jobTitle;
    private int age;
    private String gender;
}
