<script setup lang="ts">

import { ref } from 'vue'

import {request} from "../util.ts";
import CandidateMockData from "../tests/mocks/CandidateMockData.json"
import Nav from "./layout/Nav.vue";

let candidates = ref(CandidateMockData.slice(0, 10));

function getCandidates() {
  return request("/api/candidates/all", "POST", null);
}

async function updateCandidates() {
  candidates.value = await getCandidates()
}

</script>

<template>
  <div class="candidate-table">
    <Header title="Candidate Table" />
    <Nav />
    <button @click="updateCandidates">Update Table</button>
    <table>
      <thead>
      <tr>
        <th>ID</th>
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
        <td>{{candidate.id}}</td>
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
table {
  border: 1px solid red;
  border-collapse: separate;
}
</style>