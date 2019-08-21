package org.dsousa.minhasfinancas.model.repository;

import org.dsousa.minhasfinancas.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
