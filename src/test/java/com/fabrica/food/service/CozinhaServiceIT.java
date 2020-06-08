package com.fabrica.food.service;

import com.fabrica.food.domain.exception.BadValueException;
import com.fabrica.food.domain.exception.ConflictException;
import com.fabrica.food.domain.exception.NoContentException;
import com.fabrica.food.domain.model.Cozinha;
import com.fabrica.food.domain.service.CozinhaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import static org.assertj.core.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
public class CozinhaServiceIT {

	@Autowired
	private CozinhaService service;

	@Test
	public void saveWithSuccess(){
		// cenary
		Cozinha cozinha = new Cozinha(null,"CHINESA",null);

		//action
		cozinha = this.service.save(cozinha);

		// validation
		assertThat(cozinha).isNotNull();
		assertThat(cozinha.getId()).isNotNull();
	}

	@Test(expected = TransactionSystemException.class)
	public void saveWithErrorWithNomeNull(){
		Cozinha cozinha = new Cozinha(null,null,null);
		cozinha = this.service.save(cozinha);
	}

	@Test(expected = TransactionSystemException.class)
	public void saveWithErrorWithNomeEmpty(){
		Cozinha cozinha = new Cozinha(null,"",null);
		cozinha = this.service.save(cozinha);
	}

	@Test(expected = BadValueException.class)
	public void saveWithErrorWithCozinhaNull(){
		Cozinha cozinha = null;
		cozinha = this.service.save(cozinha);
	}

	@Test
	public void updateWithSuccess(){
		// cenary
		Long idCozinha = 1l;
		Cozinha cozinha = new Cozinha(null,"CHILENA",null);

		//action
		Cozinha cozinhaAtualizada = this.service.update(idCozinha,cozinha);

		// validation
		assertThat(cozinhaAtualizada).isNotNull();
		assertThat(cozinhaAtualizada.getId()).isNotNull();
		assertThat(cozinhaAtualizada.getNome()).isEqualTo("CHILENA");
	}

	@Test(expected = TransactionSystemException.class)
	public void updateWithErrorWithNomeNull(){
		Cozinha cozinha = new Cozinha(null,null,null);
		cozinha = this.service.update(1l,cozinha);

	}

	@Test(expected = TransactionSystemException.class)
	public void updateWithErrorWithNomeEmpty(){
		Cozinha cozinha = new Cozinha(null,"",null);
		cozinha = this.service.update(1l,cozinha);
	}

	@Test(expected = BadValueException.class)
	public void updateWithErrorWithCozinhaNull(){
		this.service.update(1l,null);
	}

	@Test(expected = BadValueException.class)
	public void updateWithErrorWithIdNull(){
		Cozinha cozinha = new Cozinha(null,"Chinesa",null);
		this.service.update(null,cozinha);
	}

	@Test
	public void deleteWithSuccess(){
		// cenary
		Cozinha cozinha = new Cozinha(null,"Americana",null);

		//action
		cozinha = this.service.save(cozinha);

		this.service.delete(cozinha.getId());

	}

	@Test(expected = NoContentException.class)
	public void deleteWithErrorCozinhaNotExist(){
		Cozinha cozinha = new Cozinha(99999l,null,null);
		this.service.delete( cozinha.getId());
	}

	@Test(expected = ConflictException.class)
	public void deleteWithErrorCozinhaWhenUsed(){
		Cozinha cozinha = new Cozinha(1l,"teste",null);
		this.service.delete( cozinha.getId());
	}


	@Test
	public void findByIdWithSuccess(){
		Cozinha cozinha = this.service.findById(1l);
		assertThat(cozinha).isNotNull();
		assertThat(cozinha.getId()).isNotNull();
	}

	@Test(expected = BadValueException.class)
	public void findByIdWithErrorIdNull(){
		Cozinha cozinha = this.service.findById(null);
	}

	@Test(expected = NoContentException.class)
	public void findByIdWithErrorIdNotExists(){
		Cozinha cozinha = this.service.findById(99999l);
	}


}
