import {render, screen} from "@testing-library/react";
import Header from "../components/layout/Header";

test("Test header loads", () => {
    render(<Header title={"Header Test"} />);
    const siteTitle = screen.getByRole("heading", {name: /Salary Prediction/i});
    const pageTitle = screen.getByRole("heading", {name: /Header Test/i});

    expect(siteTitle).toBeInTheDocument();
    expect(pageTitle).toBeInTheDocument();
})