package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/persone")
public class PersonaController {
    @Autowired
    private PersonaService service;

    @GetMapping
    public List<Persona> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public Persona getById(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    public Persona create(@RequestBody Persona persona) { return service.save(persona); }

    @PutMapping("/{id}")
    public Persona update(@PathVariable Long id, @RequestBody Persona persona) {
        persona.setId(id);
        return service.save(persona);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
