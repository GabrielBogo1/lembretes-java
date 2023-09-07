package br.com.uniamerica.lembretespessoa;

import br.com.uniamerica.lembretespessoa.controller.LembreteController;
import br.com.uniamerica.lembretespessoa.controller.PessoaController;
import br.com.uniamerica.lembretespessoa.entity.LembreteEntity;
import br.com.uniamerica.lembretespessoa.entity.PessoaEntity;
import br.com.uniamerica.lembretespessoa.repository.LembreteRepository;
import br.com.uniamerica.lembretespessoa.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.Assert;
import java.util.Optional;

@SpringBootTest
class LembretesPessoaApplicationTests {

	@Test
	void contextLoads() {
	}
	@MockBean
	PessoaRepository pessoaRepository;

	@Autowired
 	PessoaController controller = new PessoaController();

	@Autowired
	LembreteController controllerLembrete = new LembreteController();

	@MockBean
	LembreteRepository lembreteRepository;

	@BeforeEach
	void injectPessoa () {
		Optional < PessoaEntity> pessoa = Optional.of(new PessoaEntity(1L, "Teste"));
		Long id = 1L;
		Mockito.when(pessoaRepository.findById(id)).thenReturn(pessoa);
	}

	@Test
	void testControllerPessoa (){
		var  pessoaController = controller.teste(1L);

		Long id = pessoaController.getBody().getId().longValue();
		System.out.println(id);
		Assert.assertEquals(1L, id, 0);
	}


	@BeforeEach
	void injectLembrete(){
		Optional<LembreteEntity> lembrete = Optional.of(new LembreteEntity("testeLembrete", 2L));
		Long id = 2L;
		Mockito.when(lembreteRepository.findById(id)).thenReturn(lembrete);
	}

	@Test
	void testControllerLembrete(){
		var lembreteController = controllerLembrete.findByIdPath(2L);
		Long id = lembreteController.getBody().getId().longValue();
		System.out.println(id);
		Assert.assertEquals(2l,id,0);
	}



}
