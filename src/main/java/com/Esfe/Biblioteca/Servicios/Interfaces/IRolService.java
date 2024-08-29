package com.Esfe.Biblioteca.Servicios.Interfaces;

import com.Esfe.Biblioteca.Entidades.Rol;

import java.util.List;
import java.util.Optional;

public interface IRolService {
    List<Rol> obtenerTodos();
    Optional<Rol> buscarPorId(Integer id);
}
