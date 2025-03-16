package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // 👈 Usa solo ID per evitare il loop!
@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include // 👈 Usa solo l'ID in hashCode() ed equals()
    private Long id;

    private String nome;
    private String cognome;
    private String email;

    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("persona")
    private Indirizzo indirizzo;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("persona")
    private List<Telefono> telefoni = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "persona_progetto",
            joinColumns = @JoinColumn(name = "persona_id"),
            inverseJoinColumns = @JoinColumn(name = "progetto_id"))
    @JsonIgnoreProperties("persone")
    private Set<Progetto> progetti = new HashSet<>();
}
