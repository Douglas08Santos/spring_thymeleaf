package com.example.demo.dto;

import java.math.BigDecimal;

import com.example.demo.models.Professor;
import com.example.demo.models.Status;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.ToString;

// DTO -  Data Transfer Object
@ToString
public class ProfessorDTO {
    @NotBlank
    @NotNull
    private String n;
    @NotNull
    @DecimalMin("1")
    private BigDecimal sl;
    private Status st;

    
    public ProfessorDTO() {
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public BigDecimal getSl() {
        return sl;
    }
    public void setSl(BigDecimal sl) {
        this.sl = sl;
    }
    public Status getSt() {
        return st;
    }
    public void setSt(Status st) {
        this.st = st;
    }

    public Professor toProfessor() {
        Professor professor = new Professor();
        professor.setName(this.n);
        professor.setSalary(this.sl);
        professor.setStatus(this.st);
        return professor;
        
    }
}
