package com.jimmy.salaryprediction.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Candidate {
    @CsvBindByName
    private String education;
    @CsvBindByName
    private int experience;
    @CsvBindByName
    private String location;
    @CsvBindByName(column = "Job_Title")
    private String jobTitle;
    @CsvBindByName
    private int age;
    @CsvBindByName
    private String gender;
    @CsvBindByName
    private double salary;
}
