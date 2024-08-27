package com.Esfe.Biblioteca.Entidades;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lectores")
public class Lector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Ingrese el nombre del lector")
    @Size(max = 100, message = "El nombre no debe exceder los 100 caracteres")
    private String nombre;

    @NotBlank(message = "Ingrese el apellido del lector")
    @Size(max = 100, message = "El apellido no debe exceder los 100 caracteres")
    private String apellido;

    @NotBlank(message = "Ingrese el correo electrónico del lector")
    @Size(max = 100, message = "El correo electrónico no debe exceder los 100 caracteres")
    private String email;

    @Column(name = "telefono")
    @Nullable
    private String telefono;


    @ManyToMany
    @JoinTable(
            name = "lectores_libros",
            joinColumns = @JoinColumn(name = "lector_id"),
            inverseJoinColumns = @JoinColumn(name = "libro_id")
    )

    private Set<Libro> libros = new HashSet<>();

    public Set<Libro> getLibros() {
        return libros;
    }

    public void setLibros(Set<Libro> libros) {
        this.libros = libros;
    }

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Nullable
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(@Nullable String telefono) {
        this.telefono = telefono;
    }
}

