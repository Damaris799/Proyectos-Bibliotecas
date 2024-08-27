package com.Esfe.Biblioteca.Servicios.Interfaces;

import com.Esfe.Biblioteca.Entidades.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ILibroServices {
    Page<Libro> buscarTodosPaginados(Pageable pageable);
    List<Libro> obtenerTodos();
    Libro crearOEditar(Libro libro);
    Optional<Libro> buscarPorId(Integer id);
    void eliminarPorId(Integer id);
}

