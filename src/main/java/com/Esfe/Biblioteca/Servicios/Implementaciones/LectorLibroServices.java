package com.Esfe.Biblioteca.Servicios.Implementaciones;

import com.Esfe.Biblioteca.Entidades.LectorLibro;
import com.Esfe.Biblioteca.Repositorios.ILectorLibroRepository;
import com.Esfe.Biblioteca.Servicios.Interfaces.ILectorLibroServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LectorLibroServices implements ILectorLibroServices {

    @Autowired
    private ILectorLibroRepository lectorLibroRepository;

    @Override
    public Page<LectorLibro> buscarTodosPaginados(Pageable pageable) {
        return lectorLibroRepository.findAll(pageable);
    }

    @Override
    public List<LectorLibro> obtenerTodos() {
        return lectorLibroRepository.findAll();
    }

    @Override
    public LectorLibro crearOeditar(LectorLibro lectorLibro) {
        return lectorLibroRepository.save(lectorLibro);
    }

    @Override
    public Optional<LectorLibro> buscarPorId(Integer id) {
        return lectorLibroRepository.findById(id);
    }

    @Override
    public void eliminarPorId(Integer id) {
        lectorLibroRepository.deleteById(id);
    }
}

