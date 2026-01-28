package com.david.literalura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.david.literalura.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTitle(String title);

    @Query("SELECT l FROM Libro l")
    List<Libro> listarLibrosRegistrados();

    @Query("SELECT l FROM Libro l JOIN l.languages i WHERE i = :idioma")
    List<Libro> findByIdioma(String idioma);
}