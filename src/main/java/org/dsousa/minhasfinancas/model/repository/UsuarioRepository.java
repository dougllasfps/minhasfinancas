package org.dsousa.minhasfinancas.model.repository;

import java.util.Optional;

import org.dsousa.minhasfinancas.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByEmail( String email );
	
	boolean existsByEmail( String email );
	
}
