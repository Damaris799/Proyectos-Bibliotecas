package com.Esfe.Biblioteca.Repositorios;

import com.Esfe.Biblioteca.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUSuarioRepository extends JpaRepository<Usuario, Integer> {
}
