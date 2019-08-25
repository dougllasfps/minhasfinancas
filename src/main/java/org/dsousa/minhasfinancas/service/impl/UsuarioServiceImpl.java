package org.dsousa.minhasfinancas.service.impl;

import java.util.Optional;

import org.dsousa.minhasfinancas.exception.RegraNegocioException;
import org.dsousa.minhasfinancas.model.entity.Usuario;
import org.dsousa.minhasfinancas.model.repository.UsuarioRepository;
import org.dsousa.minhasfinancas.service.UsuarioService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
	
	private final UsuarioRepository repository;

	@Override
	public Usuario salvar(Usuario usuario) {
		validarEmail( usuario );
		return repository.save(usuario);
	}

	@Override
	public void validarEmail( Usuario usuario ) {
		String email = usuario.getEmail();
		Optional<Usuario> result = repository.findByEmail(email);
		
		if(result.isPresent()) {
			throw new RegraNegocioException("Email já cadastrado para outro usuário.");
		}
	}

}
