package com.Esfe.Biblioteca.Entidades;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Ingrese el nombre del libro")
    @Size(max = 100, message = "El nombre no debe exceder los 100 caracteres")
    private String nombre;

    @NotBlank(message = "Ingrese el autor del libro")
    @Size(max = 100, message = "El autor no debe exceder los 100 caracteres")
    private String autor;

    @NotBlank(message = "Ingrese la editorial del libro")
    @Size(max = 100, message = "La editorial no debe exceder los 100 caracteres")
    private String editorial;

    @Column(name = "anio_publicado")
    @Nullable
    private Integer anioPublicado;

    @ManyToMany(mappedBy = "libros")
    private Set<Lector> lectores = new HashSet<>();

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Nullable
    public Integer getAnioPublicado() {
        return anioPublicado;
    }

    public void setAnioPublicado(@Nullable Integer anioPublicado) {
        this.anioPublicado = anioPublicado;
    }
}

