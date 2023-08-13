package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfessorController {
    
    @GetMapping("/professores")
    public String index() {
        return "professor/index";
    }
}
