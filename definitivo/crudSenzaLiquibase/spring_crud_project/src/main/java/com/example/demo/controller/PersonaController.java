package com.example.demo.controller;

import com.example.demo.entity.Persona;
import com.example.demo.entity.Telefono;
import com.example.demo.service.PersonaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persone")
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    // ✅ Ottieni tutte le persone
    @GetMapping
    public ResponseEntity<List<Persona>> getAll() {
        List<Persona> persone = personaService.findAll();
        return persone.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(persone);
    }

    // ✅ Ottieni una persona per ID
    @GetMapping("/{id}")
    public ResponseEntity<Persona> getById(@PathVariable Long id) {
        return personaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    // ✅ Crea una persona con relazioni
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Persona persona) {
        if (persona.getIndirizzo() != null) persona.getIndirizzo().setPersona(persona);
        if (persona.getTelefoni() != null) persona.getTelefoni().forEach(t -> t.setPersona(persona));

        personaService.save(persona);
        return ResponseEntity.status(201).build();
    }

    // ✅ Aggiorna una persona esistente
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Persona persona) {
        Optional<Persona> existingPersonaOpt = personaService.findById(id);
        if (existingPersonaOpt.isEmpty()) return ResponseEntity.noContent().build();

        Persona existingPersona = existingPersonaOpt.get();
        persona.setId(id);

        if (persona.getIndirizzo() != null) persona.getIndirizzo().setPersona(persona);
        if (persona.getTelefoni() != null) persona.getTelefoni().forEach(t -> t.setPersona(persona));

        // Mantiene i progetti già associati
        persona.setProgetti(existingPersona.getProgetti());

        personaService.save(persona);
        return ResponseEntity.ok().build();
    }

    // ✅ Elimina una persona
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return personaService.findById(id)
                .map(p -> {
                    personaService.delete(id);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.noContent().build());
    }

    // ✅ Cerca una persona per nome o email
    @GetMapping("/search")
    public ResponseEntity<List<Persona>> search(@RequestParam String query) {
        List<Persona> result = personaService.search(query);
        return result.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(result);
    }


}
