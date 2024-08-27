package com.Esfe.Biblioteca.Repositorios;

import com.Esfe.Biblioteca.Entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILibroRepository extends JpaRepository<Libro, Integer> {
}

