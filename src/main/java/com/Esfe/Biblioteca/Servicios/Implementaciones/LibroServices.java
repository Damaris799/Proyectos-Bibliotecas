package com.Esfe.Biblioteca.Servicios.Implementaciones;

import com.Esfe.Biblioteca.Entidades.Libro;
import com.Esfe.Biblioteca.Repositorios.ILibroRepository;
import com.Esfe.Biblioteca.Servicios.Interfaces.ILibroServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServices implements ILibroServices {

    @Autowired
    private ILibroRepository libroRepository;

    @Override
    public Page<Libro> buscarTodosPaginados(Pageable pageable) {
        return libroRepository.findAll(pageable);
    }

    @Override
    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    @Override
    public Libro crearOEditar(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public Optional<Libro> buscarPorId(Integer id) {
        return libroRepository.findById(id);
    }

    @Override
    public void eliminarPorId(Integer id) {
        libroRepository.deleteById(id);
    }
}

