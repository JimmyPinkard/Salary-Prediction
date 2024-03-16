import React, { useState, useEffect } from 'react';
import { request } from '../util';

export interface Candidate{
  id:number;
  education:string;
  experience:number;
  location:string;
  jobTitle:string;
  age:number;
  gender:string;
  salary:number;
}

function getCandidates() {
    return request("/api/candidates/all", "POST", null);
}

function CandidateTable() {
  const [candidates, setCandidates] = useState<Candidate[]>([]);
    useEffect(() => {
        getCandidates()
            .then((r) => {
                setCandidates(r);
            });
    }, []);
  const candidateRows = candidates.map((candidate:Candidate) => {
    return (
      <tr key={candidate.id}>
        <td>{candidate.education}</td>
        <td>{candidate.experience}</td>
        <td>{candidate.location}</td>
        <td>{candidate.jobTitle}</td>
        <td>{candidate.age}</td>
        <td>{candidate.gender}</td>
        <td>{candidate.salary}</td>
      </tr>
    );
    });
  return (
  <div>
    <table border={1}>
      <thead>
      <tr>
        <th>Education</th>
        <th>Experience</th>
        <th>Location</th>
        <th>Job Title</th>
        <th>Age</th>
        <th>Gender</th>
        <th>Salary</th>
      </tr>
      </thead>
      <tbody>
        {candidateRows}
      </tbody>
    </table>
  </div>
  );
}

export default CandidateTable;
