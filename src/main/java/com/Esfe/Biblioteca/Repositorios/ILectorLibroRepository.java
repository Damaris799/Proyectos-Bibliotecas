package com.Esfe.Biblioteca.Repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.Esfe.Biblioteca.Entidades.LectorLibro;

public interface ILectorLibroRepository extends JpaRepository<LectorLibro, Integer> {
    Page<LectorLibro> findByOrderByLectorDesc(Pageable pageable);
}

