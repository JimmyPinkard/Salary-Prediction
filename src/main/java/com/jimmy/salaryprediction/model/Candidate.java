package com.jimmy.salaryprediction.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Candidate {
    @Id
    private Long id;
    private String education;
    private int experience;
    private String location;
    @Column(name = "Job_Title")
    private String jobTitle;
    private int age;
    private String gender;
    private double salary;
}
