package com.Esfe.Biblioteca.Servicios.Implementaciones;

import com.Esfe.Biblioteca.Entidades.Lector;
import com.Esfe.Biblioteca.Repositorios.ILectorRepository;
import com.Esfe.Biblioteca.Servicios.Interfaces.ILectorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LectorServices implements ILectorServices {

    @Autowired
    private ILectorRepository lectorRepository;

    @Override
    public Page<Lector> buscarTodosPaginados(Pageable pageable) {
        return lectorRepository.findAll(pageable);
    }

    @Override
    public List<Lector> obtenerTodos() {
        return lectorRepository.findAll();
    }

    @Override
    public Lector crearOEditar(Lector lector) {
        return lectorRepository.save(lector);
    }

    @Override
    public Optional<Lector> buscarPorId(Integer id) {
        return lectorRepository.findById(id);
    }

    @Override
    public void eliminarPorId(Integer id) {
        lectorRepository.deleteById(id);
    }
}

