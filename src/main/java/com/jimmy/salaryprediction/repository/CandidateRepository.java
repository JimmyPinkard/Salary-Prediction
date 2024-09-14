package com.jimmy.salaryprediction.repository;

import com.jimmy.salaryprediction.model.Candidate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate, Long> {
    List<Candidate> findAll();
    @Query("SELECT DISTINCT education from Candidate")
    String[] listEducationTypes();
    @Query("SELECT DISTINCT location from Candidate")
    String[] listLocations();
    @Query(value = "SELECT DISTINCT Job_Title from Candidate", nativeQuery = true)
    String[] listJobTitles();
    @Query("SELECT DISTINCT gender from Candidate")
    String[] listGenders();
    @Query(value = "SELECT * FROM Candidate", nativeQuery = true)
    Candidate[] withoutOutliers();
}
