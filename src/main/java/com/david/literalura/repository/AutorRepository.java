package com.david.literalura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.david.literalura.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNameAndBirthYearAndDeathYear(String name, Integer birthYear, Integer deathYear);

    @Query("SELECT a FROM Autor a")
    List<Autor> listarAutoresRegistrados();

    @Query("SELECT a FROM Autor a WHERE a.birthYear <= :year AND (a.deathYear IS NULL OR a.deathYear > :year)")
    List<Autor> listarAutoresPorFecha(int year);
}