package com.Esfe.Biblioteca.Servicios.Interfaces;

import com.Esfe.Biblioteca.Entidades.Lector;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ILectorServices {
    Page<Lector> buscarTodosPaginados(Pageable pageable);
    List<Lector> obtenerTodos();
    Lector crearOEditar(Lector lector);
    Optional<Lector> buscarPorId(Integer id);
    void eliminarPorId(Integer id);
}

