package com.alura.literalura.principal;

import com.alura.literalura.model.*;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);

    private ConsumoAPI consumo = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    private final String URL_BASE = "https://gutendex.com/books/?search=";

    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void mostrarMenu() {

        int opcion = -1;

        while (opcion != 0) {

            System.out.println("""
            ===== LiterAlura =====

            1 - Buscar libro por titulo
            2 - Listar libros
            3 - Listar autores
            4 - Autores vivos en determinado año
            5 - Libros por idioma

            0 - Salir
            """);

            System.out.print("Seleccione una opción: ");

            String entrada = teclado.nextLine();

            if (entrada.isEmpty()) {
                opcion = -1;
            } else {
                opcion = Integer.parseInt(entrada);
            }

            switch (opcion) {

                case 1:
                    buscarLibro();
                    break;

                case 2:
                    listarLibros();
                    break;

                case 3:
                    listarAutores();
                    break;

                case 4:
                    autoresVivos();
                    break;

                case 5:
                    librosPorIdioma();
                    break;

                case 0:
                    System.out.println("Cerrando aplicación...");
                    break;

                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibro() {

        System.out.println("Ingrese el nombre del libro:");
        String titulo = teclado.nextLine();

        String json = consumo.obtenerDatos(URL_BASE + titulo.replace(" ", "%20"));

        DatosRespuesta datos = conversor.obtenerDatos(json, DatosRespuesta.class);

        Optional<DatosLibro> libroBuscado = datos.results().stream().findFirst();

        if (libroBuscado.isPresent()) {

            DatosLibro datosLibro = libroBuscado.get();

            DatosAutor datosAutor = datosLibro.authors().get(0);

            Autor autor = new Autor(
                    datosAutor.name(),
                    datosAutor.birth_year(),
                    datosAutor.death_year()
            );

            autorRepository.save(autor);

            Libro libro = new Libro(
                    datosLibro.title(),
                    datosLibro.languages().get(0),
                    datosLibro.download_count(),
                    autor
            );

            libroRepository.save(libro);

            System.out.println("Libro guardado en la base de datos");

        } else {
            System.out.println("Libro no encontrado");
        }
    }

    private void listarLibros() {

        List<Libro> libros = libroRepository.findAll();

        if (libros.isEmpty()) {
            System.out.println("No hay libros guardados");
        } else {
            libros.forEach(System.out::println);
        }
    }

    private void listarAutores() {

        List<Autor> autores = autorRepository.findAll();

        if (autores.isEmpty()) {
            System.out.println("No hay autores guardados");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void autoresVivos() {

        System.out.println("Ingrese el año:");
        int anio = Integer.parseInt(teclado.nextLine());

        List<Autor> autores = autorRepository.findAutoresVivos(anio);

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en ese año");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void librosPorIdioma() {

        System.out.println("Ingrese el idioma (es, en, fr, etc):");
        String idioma = teclado.nextLine();

        List<Libro> libros = libroRepository.findByIdioma(idioma);

        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en ese idioma");
        } else {
            libros.forEach(System.out::println);
        }
    }
}