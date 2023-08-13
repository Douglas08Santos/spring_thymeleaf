package com.example.demo.dto;

import java.math.BigDecimal;

import com.example.demo.models.Professor;
import com.example.demo.models.Status;

import lombok.ToString;

// DTO -  Data Transfer Object
@ToString
public class ProfessorDTO {
    private String n;
    private BigDecimal sl;
    private Status st;

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
