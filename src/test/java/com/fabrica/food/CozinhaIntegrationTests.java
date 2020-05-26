package com.fabrica.food;

import com.fabrica.food.domain.model.Cozinha;
import com.fabrica.food.domain.service.CozinhaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CozinhaIntegrationTests {

	@Autowired
	private CozinhaService service;

	@Test
	public void saveWithSuccess(){
		// cenary
		Cozinha cozinha = new Cozinha(null,"Chinesa",null);

		//action
		cozinha = this.service.save(cozinha);

		// validation
		assertThat(cozinha).isNotNull();
		assertThat(cozinha.getId()).isNotNull();
	}

//	@Test
//	public void saveWithError(){
//
//	}

}
