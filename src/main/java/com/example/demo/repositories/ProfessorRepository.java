package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{
    
}
