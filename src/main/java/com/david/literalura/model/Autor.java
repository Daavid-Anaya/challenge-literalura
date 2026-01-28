package com.david.literalura.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "autores",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"name", "birthYear", "deathYear"}
        )
    }
)
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;
    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    private List<Libro> libros = new ArrayList<>();

    public Autor() {}

    public Autor(DatosAutor a) {
        this.name = a.name();
        this.birthYear = a.birth_year();
        this.deathYear = a.death_year();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getBirthYear() { return birthYear; }
    public void setBirthYear(Integer birthYear) { this.birthYear = birthYear; }

    public Integer getDeathYear() { return deathYear; }
    public void setDeathYear(Integer deathYear) { this.deathYear = deathYear; } 

    public void setLibros(List<Libro> libros) { this.libros = libros; }
    public List<Libro> getLibros() { return libros; }

    @Override
    public String toString() {
        return "\nNombre: " + name + "\nAño de nacimiento: " + birthYear + "\nAño de fallecimiento: " + deathYear + "\nLibros: " + libros.stream().map(Libro::getTitle).toList();
    }    
}