package com.example.demo.repository;

import com.example.demo.dto.PersonaDTO;
import com.example.demo.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    List<Persona> findByNomeContainingOrCognomeContainingOrEmailContaining(String nome, String cognome, String email);
    List<PersonaDTO> findAllBy();
    List<Persona> findByNomeContainingIgnoreCaseOrCognomeContainingIgnoreCase(String nome, String cognome);

}
