//import {render, screen} from "@testing-library/react";
//import CandidateTable, {Candidate} from "../components/CandidateTable";
import CandidateMockData from "./mocks/CandidateMockData.json"
import util from "../util";

//@ts-ignore
util.request = (endpoint:string, method:string, body:any) => {
//@ts-ignore
    return new Promise((resolve, reject) => {
            resolve(CandidateMockData);
    });
};

/*
test("Test table loads", async () => {
    render(<CandidateTable />);

    const candidateTable = await screen.findAllByRole("cell", {name: /PhD/i})
        .then(res => res[0]);
    expect(candidateTable).toBeInTheDocument();
})
*/