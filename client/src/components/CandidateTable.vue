<script setup lang="ts">

import {request} from "../util.ts";
import CandidateMockData from "../tests/mocks/CandidateMockData.json"

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

let candidates:Candidate[] = CandidateMockData;

function getCandidates() {
  return request("/api/candidates/all", "POST", null);
}


</script>

<template>
  <div class="App">
    <Header title="Candidate Table" />
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
      <tbody v-for="candidate in candidates">
      <tr key={{candidate.id}}>
        <td>{{candidate.education}}</td>
        <td>{{candidate.experience}}</td>
        <td>{{candidate.location}}</td>
        <td>{{candidate.jobTitle}}</td>
        <td>{{candidate.age}}</td>
        <td>{{candidate.gender}}</td>
        <td>{{candidate.salary}}</td>
      </tr>
      </tbody>
    </table>
  </div>

</template>

<style scoped>

</style>