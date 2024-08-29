package com.Esfe.Biblioteca.Repositorios;

import com.Esfe.Biblioteca.Entidades.Multa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMultaRepository extends JpaRepository<Multa, Integer> {
        Page<Multa> findByOrderByMontoDesc(Pageable pageable);
    }
