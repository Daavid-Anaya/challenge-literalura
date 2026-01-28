package com.david.literalura.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "libros",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "title")
    }
)
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(
        name = "libro_autor",
        uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"libro_id", "autor_id"})
        },
        joinColumns = @JoinColumn(name = "libro_id"),
        inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> authors = new ArrayList<>();
    @ElementCollection
    @CollectionTable(
        name = "libros_idiomas",
        joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "idioma")
    private List<String> languages;
    private int download_count;

    public Libro() {}

    public Libro(DatosLibro datoslibro) {
        this.title = datoslibro.title();
        this.languages = datoslibro.languages();
        this.download_count = datoslibro.download_count();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<Autor> getAuthors() { return authors; }
    public void setAuthors(List<Autor> authors) { this.authors = authors; }

    public List<String> getLanguages() { return languages; }
    public void setLanguages(List<String> languages) { this.languages = languages; }

    public int getDownload_count() { return download_count; }
    public void setDownload_count(int download_count) { this.download_count = download_count; }

    @Override
    public String toString() {
        return "\nTitulo: " + title + "\nAutores: " + authors.stream().map(Autor::getName).toList() + "\nIdiomas: " + languages + "\nTotal de descargas: " + download_count;
    } 
}