package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository repository;

    public List<Persona> findAll() { return repository.findAll(); }
    public Persona findById(Long id) { return repository.findById(id).orElse(null); }
    public Persona save(Persona persona) { return repository.save(persona); }
    public void delete(Long id) { repository.deleteById(id); }
}
