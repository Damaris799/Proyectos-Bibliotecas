package com.Esfe.Biblioteca.Servicios.Implementaciones;

import com.Esfe.Biblioteca.Entidades.Multa;
import com.Esfe.Biblioteca.Repositorios.IMultaRepository;
import com.Esfe.Biblioteca.Servicios.Interfaces.IMultaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MultaServices implements IMultaServices {

    @Autowired
    private IMultaRepository multaRepository;

    @Override
    public Page<Multa> buscarTodosPaginados(Pageable pageable) {
        return multaRepository.findAll(pageable);
    }

    @Override
    public List<Multa> obtenerTodos() {
        return multaRepository.findAll();
    }

    @Override
    public Multa crearOeditar(Multa multa) {
        return multaRepository.save(multa);
    }

    @Override
    public Optional<Multa> buscarPorId(Integer id) {
        return multaRepository.findById(id);
    }

    @Override
    public void eliminarPorId(Integer id) {
        multaRepository.deleteById(id);
    }

    @Override
    public int obtenerTotalMultas() {
        return (int) multaRepository.count();
    }


}
