package com.david.literalura.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Id;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
    @Id
    Long id,
    String title,
    List<DatosAutor> authors,
    List<String> languages,
    int download_count
) {
    
}