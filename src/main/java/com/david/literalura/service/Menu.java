package com.david.literalura.service;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.stereotype.Service;

/*
    1. Buscar libro por título en la API Gutendex y registrarlo en la base de datos.
    2. Listar los libros registrados en la base de datos.
    3. Listar los autores registrados.
    4. Listar los autores vivos en un año determinado.
    5. Listar los libros por idioma.
 */

@Service
public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private final LibroService libroService;

    public Menu(LibroService libroService) {
        this.libroService = libroService;
    }

    public void mostrarMenu() {
        var opc = -1;
        var menu = """
                    \n====== MENÚ LIBROS ======
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un año determinado
                    5 - Listar libros por idioma
                    0 - Salir
                    ========================
                    """;

        String idiomas = """
                \nes - Español
                en - Ingles
                fr - Francés
                pt - Portugués
                """;
                    
        do {
            try {
                System.out.print(menu);
                opc = scanner.nextInt();
                scanner.nextLine();
                
                switch (opc) {
                    case 0:
                        System.out.println("Saliendo...");
                        break;
                    case 1:
                        System.out.print("Ingrese el título del libro: ");
                        String titulo = scanner.nextLine();
                        libroService.buscarLibrosPorTitulo(titulo);
                        break;
                    case 2:
                        libroService.listarLibrosRegistrados();
                        break;
                    case 3:
                        libroService.listarAutoresRegistrados();
                        break;
                    case 4:
                        System.out.print("Ingrese el año para listar los autores vivos en ese año: ");
                        int year = scanner.nextInt();
                        scanner.nextLine();
                        libroService.listarAutoresPorFecha(year);
                        break;
                    case 5:
                        System.out.print(idiomas + "\nIngrese el alias del idioma a buscar: ");
                        String idioma = scanner.nextLine();
                        if (!esIdiomaValido(idioma)) {
                            System.out.println("Idioma no válido. Intente de nuevo.");
                            break;
                        }
                        libroService.listarLibroPorIdioma(idioma);
                        break;    
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                        break;
                }                                                                                           
            } catch (InputMismatchException  e) {
                System.out.println("Error. No ingresaste un número entero.");
                scanner.next();
            }
        } while (opc != 0);
    }

    public boolean esIdiomaValido(String str) {
        String[] idiomasValidos = {"es", "en", "fr", "pt"};
        for (String idioma : idiomasValidos) {
            if (idioma.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
}