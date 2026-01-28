package com.david.literalura.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.david.literalura.model.Autor;
import com.david.literalura.model.DatosAutor;
import com.david.literalura.model.DatosGutendex;
import com.david.literalura.model.DatosLibro;
import com.david.literalura.model.Libro;
import com.david.literalura.repository.AutorRepository;
import com.david.literalura.repository.LibroRepository;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.transaction.Transactional;

@Service
public class LibroService {
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();

    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public DatosGutendex getDatoLibro(String tituloLibro) {
        var json = consumoAPI.obtenerDatos(Dotenv.load().get("APIGUTENDEX") + "?search=" + tituloLibro.replace(" ", "+"));
        return convierteDatos.obtenerDatos(json, DatosGutendex.class);
    }

    @Transactional
    public void buscarLibrosPorTitulo(String tituloLibro) {

        DatosGutendex datos = getDatoLibro(tituloLibro);

        if (datos.results().isEmpty()) {
            System.out.println("No se encontraron libros con ese título");
            return;
        }

        DatosLibro datoslibro = datos.results().get(0);

        Optional<Libro> libroExistente = libroRepository.findByTitle(datoslibro.title());
        if (libroExistente.isPresent()) {
            System.out.println("Este libro ya está registrado, no se puede registrar de nuevo.");
            return;
        } else {
            Libro libro = new Libro(datoslibro);

            List<Autor> autoresFinales = new ArrayList<>();

        for (DatosAutor da : datoslibro.authors()) {

            Autor autor = autorRepository
                .findByNameAndBirthYearAndDeathYear(
                    da.name(),
                    da.birth_year(),
                    da.death_year()
                )
                .orElseGet(() -> new Autor(da));

            autor.getLibros().add(libro);
            autoresFinales.add(autor);
        }

        libro.setAuthors(autoresFinales);
        libroRepository.save(libro);
        System.out.println("\n====== Libro ======" + libro.toString() + "\n=================\n"); 
        }  
    }

    @Transactional
    public void listarLibrosRegistrados() {
        List<Libro> libros = libroRepository.listarLibrosRegistrados();

        System.out.println("\n====== Libros registrados ======\n");
        for (Libro libro : libros) {
            System.out.println("-----------------------" +libro.toString() + "\n-----------------------\n");
        }
    }

    @Transactional
    public void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.listarAutoresRegistrados();

        System.out.println("\n====== Autores registrados ======\n");
        for (Autor autor : autores) {
            System.out.println("-----------------------" + autor.toString() + "\n-----------------------\n");
        }
    }

    @Transactional
    public void listarAutoresPorFecha(int year) {
        List<Autor> autores = autorRepository.listarAutoresPorFecha(year);

        System.out.println("\n====== Autores vivos en " + year + " ======\n");
        for (Autor autor : autores) {
            System.out.println("-----------------------" + autor.toString() + "\n-----------------------\n");
        }
    }

    @Transactional
    public void listarLibroPorIdioma(String idioma) {
        List<Libro> librosPorIdioma = libroRepository.findByIdioma(idioma);

        for (Libro libro : librosPorIdioma) {
            System.out.println("\n-----------------------" + libro.toString() + "\n-----------------------");
        }
    }
}