import React from 'react';
import './App.css';
import Header from './components/layout/Header';
import CandidateTable from './components/CandidateTable';

function App() {
  return (
    <div className="App">
      <Header title={"Home"}/>
      <CandidateTable />
    </div>
  );
}

export default App;
