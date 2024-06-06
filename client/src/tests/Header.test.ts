import {render, screen} from "@testing-library/vue";
import Header from "../components/layout/Header.vue";

test("Test header loads", () => {
    render(Header, {
        props: {
            title: "Header Test"
        }
    });
    const siteTitle = screen.getByRole("heading", {name: /Salary Prediction/i});
    const pageTitle = screen.getByRole("heading", {name: /Header Test/i});

    expect(siteTitle.innerText).toBe("Salary Prediction");
    expect(pageTitle.innerText).toBe("Header Test");
})