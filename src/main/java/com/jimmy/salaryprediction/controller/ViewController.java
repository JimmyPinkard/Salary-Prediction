package com.jimmy.salaryprediction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/visualizations")
    public String visualizations() {
        return "visualizations.html";
    }
}
