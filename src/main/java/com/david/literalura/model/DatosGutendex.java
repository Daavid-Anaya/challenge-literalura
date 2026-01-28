package com.david.literalura.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosGutendex(
    int count,
    List<DatosLibro> results
) {
    
}