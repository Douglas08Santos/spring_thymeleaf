package com.example.demo.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.ProfessorDTO;
import com.example.demo.models.Professor;
import com.example.demo.models.Status;
import com.example.demo.service.ProfessorService;

import jakarta.validation.Valid;

@Controller
public class ProfessorController {
    ProfessorService service;

    // Inicializando o db com alguns dados
    private void init() {
        Professor professor1 = new Professor("Nome 1",  new BigDecimal(5000), Status.ATIVO);
        Professor professor2 = new Professor("Nome 2",  new BigDecimal(10000), Status.APOSENTADO);
        Professor professor3 = new Professor("Nome 3",  new BigDecimal(7500), Status.INATIVO);
        Professor professor4 = new Professor("Nome 4",  new BigDecimal(950000001), Status.AFASTADO);

        service.insert(professor1);
        service.insert(professor2);
        service.insert(professor3);
        service.insert(professor4);
    }
    public ProfessorController(ProfessorService service) {
        this.service = service;
        List<Professor> professores = service.listAll();
        if (professores.isEmpty()) {
            init();
        }
    }
    
    @GetMapping("/professor")
    public ModelAndView index() {
        // viewName = endereço da pagina template
        ModelAndView mv = new ModelAndView("professor/index");
        mv.addObject("professores", service.listAll());
        return mv;
    }

    @GetMapping("/professor/new")
    public ModelAndView addProfessor() {
        ModelAndView mv = new ModelAndView("professor/add");
        mv.addObject("statusProfessor", Status.values());
        return mv;
    }

    @PostMapping("/professor")
    public String create(@Valid ProfessorDTO professorDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/professor/new";
        } else {
            service.insert(professorDTO.toProfessor());    
            // chamar o get da função index
            return "redirect:/professor";
        }
        
    }
}
