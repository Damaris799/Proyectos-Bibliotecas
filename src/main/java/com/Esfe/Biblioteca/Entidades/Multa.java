package com.Esfe.Biblioteca.Entidades;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
    @Table(name = "multas")
    public class Multa {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @ManyToOne
        @JoinColumn(name = "lector_id", nullable = false)
        private Lector lector;

        @ManyToOne
        @JoinColumn(name = "libro_id", nullable = false)
        private Libro libro;

        private LocalDate fechaMulta;

        private Double monto;

        @Column(length = 255)
        private String estado;

        // Getters y Setters

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

        public LocalDate getFechaMulta() {
            return fechaMulta;
        }

        public void setFechaMulta(LocalDate fechaMulta) {
            this.fechaMulta = fechaMulta;
        }

        public Double getMonto() {
            return monto;
        }

        public void setMonto(Double monto) {
            this.monto = monto;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }
    }
