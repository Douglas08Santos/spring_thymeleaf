package com.example.demo.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.ProfessorDTO;
import com.example.demo.models.Professor;
import com.example.demo.models.Status;
import com.example.demo.service.ProfessorService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/professores")
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
    
    @GetMapping("")
    public ModelAndView index() {
        // viewName = endereço da pagina template
        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("professores", service.listAll());
        return mv;
    }

    @GetMapping("/add")
    public ModelAndView addProfessor(ProfessorDTO professorDTO) {
        ModelAndView mv = new ModelAndView("professores/add");
        mv.addObject("statusProfessor", Status.values());
        return mv;
    }

    @PostMapping("")
    public ModelAndView create(@Valid ProfessorDTO professorDTO, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result.getErrorCount());
            ModelAndView mv = new ModelAndView("professores/add");
            mv.addObject("statusProfessor", Status.values());
            return mv;
        } else {
            Professor newProfessor = service.insert(professorDTO.toProfessor());    
            // chamar o get da função index
            return new ModelAndView("redirect:/professores/"+newProfessor.getId());
        }
    }

    @GetMapping("/{id}")
    public ModelAndView details(@PathVariable Long id) {
        Optional<Professor> optional = service.findById(id);

        if (optional.isPresent()) {
            ModelAndView mv = new ModelAndView("professores/details");
            System.out.println(optional.get());
            mv.addObject("professor", optional.get());
            return mv;
        } else {
            System.out.println("Professor com o id " + id + " não encontrado!!");
            return new ModelAndView("redirect:/professores");
        }
        

        
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, ProfessorDTO professorDTO) {
        Optional<Professor> optional = service.findById(id);

        if (optional.isPresent()) {
            ModelAndView mv = new ModelAndView("professores/edit");
            System.out.println(optional.get());
            professorDTO.fromProfessor(optional.get());
            mv.addObject("professorId", optional.get().getId());
            mv.addObject("statusProfessor", Status.values());
            return mv;
        } else {
            System.out.println("Professor com o id " + id + " não encontrado!!");
            return new ModelAndView("redirect:/professores");
        }
         
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, @Valid ProfessorDTO professorDTO, BindingResult result) {
        if (result.hasErrors()) {
            return null;
        } else {
            //Verificar se o professor com o id passado já existe
            Optional<Professor> optional = service.findById(id);
            if (optional.isPresent()) {
                Professor professor = optional.get();
                professor.setName(professorDTO.getN());
                professor.setSalary(professorDTO.getSl());
                professor.setStatus(professorDTO.getSt());

                service.insert(professor);
                return new ModelAndView("redirect:/professores/"+professor.getId());
            } else {
                System.out.println("Professor com o id " + id + " não encontrado!!");
                return new ModelAndView("redirect:/professores");
            }
            
        }      
    }
}
