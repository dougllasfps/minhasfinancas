package org.dsousa.minhasfinancas.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.dsousa.minhasfinancas.exception.RegraNegocioException;
import org.dsousa.minhasfinancas.model.entity.Usuario;
import org.dsousa.minhasfinancas.model.repository.UsuarioRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

	@SpyBean
	UsuarioService service;
	@MockBean
	UsuarioRepository repository;
	@Mock
	Usuario usuario;
	
	String email = "usuario@email.com";
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test(expected = Test.None.class)
	public void deveValidarUmEmail() {
		//cenário
		Mockito.when(usuario.getEmail()).thenReturn(email);
		
		//teste
		service.validarEmail(usuario);
	}
	
	@Test
	public void deveLancarErroQuandoJaExistirUsuarioCadastradoComOEmail() {
		
		//cenário
		Mockito.when(usuario.getEmail()).thenReturn(email);
		Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));
		
		//teste
		try {
			service.validarEmail(usuario);
			Assert.fail();
		}catch (Exception e) {
			Assertions.assertThat(e).isInstanceOf(RegraNegocioException.class).hasMessage("Email já cadastrado para outro usuário.");
		}
	}
	
	@Test
	public void deveSalvarUmUsuario() {
		//cenário
		Mockito.doNothing().when(service).validarEmail(usuario);
		Mockito.when(repository.save(usuario)).thenReturn(usuario);
		
		//teste
		Usuario usuarioSalvo = service.salvar(usuario);
		
		//verificações
		Assertions.assertThat(usuarioSalvo).isEqualTo(usuario);
		Mockito.verify(repository, Mockito.times(1)).save(usuario);
	}
	
	
}
