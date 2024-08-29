package com.Esfe.Biblioteca.Servicios.Interfaces;

import com.Esfe.Biblioteca.Entidades.Multa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IMultaServices {

    Page<Multa> buscarTodosPaginados(Pageable pageable);

    List<Multa> obtenerTodos();

    Multa crearOeditar(Multa multa);

    Optional<Multa> buscarPorId(Integer id);

    void eliminarPorId(Integer id);

    int obtenerTotalMultas();
}
