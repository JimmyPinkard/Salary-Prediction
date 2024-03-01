package com.jimmy.salaryprediction.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
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
    private float salary;
}
