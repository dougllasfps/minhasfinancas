package org.dsousa.minhasfinancas.model.repository;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import org.dsousa.minhasfinancas.model.entity.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository repository;
	
	@Test
	public void deveSalvarUmUsuario() {
		
		// cenário
		Usuario usuario = new Usuario();
		usuario.setDataCadastro(LocalDate.now());
		usuario.setEmail("usuario@email.com");
		usuario.setNome("usuario");
		usuario.setSenha("senha");
		
		//execucao 
		repository.save(usuario);
		
		//verificacao
		assertNotNull(usuario.getId());
		
	}
}
