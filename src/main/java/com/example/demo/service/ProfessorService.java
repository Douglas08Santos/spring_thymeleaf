package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.models.Professor;
import com.example.demo.repositories.ProfessorRepository;

@Service
public class ProfessorService {
    ProfessorRepository repository;

    public ProfessorService(ProfessorRepository repository) {
        this.repository = repository;
    }

    public Professor insert(Professor professor) {
        return repository.save(professor);
    }

    public List<Professor> listAll() {
        return repository.findAll();
    }

    public Optional<Professor> findById(Long id){
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    
}
