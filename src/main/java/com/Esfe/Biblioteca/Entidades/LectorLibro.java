package com.Esfe.Biblioteca.Entidades;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "lectores_libros")
public class LectorLibro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "lector_id")
    private Lector lector;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;

    private LocalDate diaPrestamo;
    private LocalDate diaDevolucion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Lector getLector() {
        return lector;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public LocalDate getDiaPrestamo() {
        return diaPrestamo;
    }

    public void setDiaPrestamo(LocalDate diaPrestamo) {
        this.diaPrestamo = diaPrestamo;
    }

    public LocalDate getDiaDevolucion() {
        return diaDevolucion;
    }

    public void setDiaDevolucion(LocalDate diaDevolucion) {
        this.diaDevolucion = diaDevolucion;
    }
}


