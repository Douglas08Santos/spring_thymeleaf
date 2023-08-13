package com.example.demo.controllers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.models.Professor;
import com.example.demo.models.Status;

@Controller
public class ProfessorController {
    
    @GetMapping("/professores")
    public ModelAndView index() {
        Professor professor1 = new Professor("Nome 1",  new BigDecimal(5000), Status.ATIVO);
        Professor professor2 = new Professor("Nome 2",  new BigDecimal(10000), Status.APOSENTADO);
        Professor professor3 = new Professor("Nome 3",  new BigDecimal(7500), Status.INATIVO);
        Professor professor4 = new Professor("Nome 4",  new BigDecimal(9500), Status.AFASTADO);
        
        List<Professor> professores = Arrays.asList(professor1, professor2, professor3, professor4);

        // viewName = endereço da pagina template
        ModelAndView mv = new ModelAndView("professor/index");
        
        mv.addObject("professores", professores);
        return mv;
    }
}
