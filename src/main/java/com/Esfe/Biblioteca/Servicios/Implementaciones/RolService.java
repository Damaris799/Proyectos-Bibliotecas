package com.Esfe.Biblioteca.Servicios.Implementaciones;

import com.Esfe.Biblioteca.Entidades.Rol;
import com.Esfe.Biblioteca.Repositorios.IRolRepository;
import com.Esfe.Biblioteca.Servicios.Interfaces.IRolService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class RolService implements IRolService {
    @Autowired
    private IRolRepository rolRepository;
    @Override
    public List<Rol> obtenerTodos() {
        return rolRepository.findAll();
    }
    @Override
    public Optional<Rol> buscarPorId(Integer id) {
        return rolRepository.findById(id);
    }
}
