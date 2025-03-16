package com.example.demo.service;

import com.example.demo.entity.Persona;
import com.example.demo.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository repository;

    public List<Persona> findAll() {
        return repository.findAll();
    }

    public Optional<Persona> findById(Long id) {
        return repository.findById(id);
    }



    public Persona save(Persona persona) {
        if (persona.getIndirizzo() != null) {
            persona.getIndirizzo().setPersona(persona);
        }
        return repository.save(persona);
    }


    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Persona con ID " + id + " non trovata.");
        }
        repository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }



    public List<Persona> search(String query) {
        return repository.findByNomeContainingIgnoreCaseOrCognomeContainingIgnoreCase(query, query);
    }
}
