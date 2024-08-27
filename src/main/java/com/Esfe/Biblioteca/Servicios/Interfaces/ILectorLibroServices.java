package com.Esfe.Biblioteca.Servicios.Interfaces;


import com.Esfe.Biblioteca.Entidades.LectorLibro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ILectorLibroServices {
    Page<LectorLibro> buscarTodosPaginados(Pageable pageable);
    List<LectorLibro> obtenerTodos();
    LectorLibro crearOeditar(LectorLibro lectorLibro);
    Optional<LectorLibro> buscarPorId(Integer id);
    void eliminarPorId(Integer id);
}

