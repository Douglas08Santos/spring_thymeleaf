package com.example.demo.models;

import java.math.BigDecimal;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.ToString;

// O lombok getter e setter não funciona com o thymeleaf - triste :[
@Entity
@AllArgsConstructor
@ToString
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private BigDecimal salary;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)    
    private Status status;

    public Professor(String name, BigDecimal salary, Status status) {
        this.name = name;
        this.salary = salary;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public Status getStatus() {
        return status;
    }
}
