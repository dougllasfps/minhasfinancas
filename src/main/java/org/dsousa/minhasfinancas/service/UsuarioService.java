package org.dsousa.minhasfinancas.service;

import org.dsousa.minhasfinancas.model.entity.Usuario;

public interface UsuarioService {

	Usuario salvar( Usuario usuario );
	
	void validarEmail(Usuario usuario);
}
