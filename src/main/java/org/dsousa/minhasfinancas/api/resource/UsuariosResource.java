package org.dsousa.minhasfinancas.api.resource;

import org.dsousa.minhasfinancas.api.dto.UsuarioDTO;
import org.dsousa.minhasfinancas.exception.RegraNegocioException;
import org.dsousa.minhasfinancas.model.entity.Usuario;
import org.dsousa.minhasfinancas.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RequestMapping("/api/usuarios")
@RestController
@AllArgsConstructor
public class UsuariosResource {

	private final UsuarioService service;
	
	@PostMapping
	public ResponseEntity salvar( @RequestBody UsuarioDTO body ) {
		Usuario entity = converter(body);
		try {
			service.salvar(entity);
			return new ResponseEntity( entity.getId(), HttpStatus.CREATED);
		}catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	private Usuario converter(UsuarioDTO dto) {
		return Usuario.builder().nome(dto.getNome()).email(dto.getEmail()).senha(dto.getSenha()).build();
	}
}
