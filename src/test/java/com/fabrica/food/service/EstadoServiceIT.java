package com.fabrica.food.service;

import com.fabrica.food.domain.dto.EstadoDto;
import com.fabrica.food.domain.exception.BadValueException;
import com.fabrica.food.domain.exception.NegocioException;
import com.fabrica.food.domain.exception.NoContentException;
import com.fabrica.food.domain.model.Estado;
import com.fabrica.food.domain.service.EstadoService;
import io.restassured.RestAssured;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
public class EstadoServiceIT {

	@Autowired
	private EstadoService service;
	Map<String, Object> mapBodyRequest = new HashMap<>();

	@Autowired
	private Flyway flyway;

	@Before
	public void init(){
		flyway.migrate();
	}

	@Test
	public void saveWithSuccess(){

		Estado estado = new Estado(null,"BAHIA");
		estado = this.service.save(estado);
		assertThat(estado).isNotNull();
		assertThat(estado.getId()).isNotNull();
	}

	@Test
	public void saveCustomWithSuccess(){
		mapBodyRequest.put("nome","BAHIA2");

		Object estado = mapBodyRequest;

		EstadoDto estadoDto = (EstadoDto) this.service.saveCustom(estado);
		assertThat(estadoDto).isNotNull();
		assertThat(estadoDto.getId()).isNotNull();
	}

	@Test(expected = BadValueException.class)
	public void saveWithErrorWithNomeNull(){
		Estado estado = new Estado(null,null);
		estado = this.service.save(estado);
	}

	@Test(expected = TransactionSystemException.class)
	public void saveWithErrorWithNomeEmpty(){
		Estado estado = new Estado(null,"");
		estado = this.service.save(estado);
	}

	@Test(expected = BadValueException.class)
	public void saveWithErrorWithEstadoNull(){
		Estado estado = null;
		estado = this.service.save(estado);
	}

	@Test(expected = NegocioException.class)
	public void saveWithErrorEstadoExist(){
		Estado estado = new Estado(null,"ALAGOAS");
		this.service.save(estado);
	}

	@Test
	public void updateWithSuccess(){

		Long idEstado = 1l;
		Estado estado = new Estado(null,"AMAZONAS");

		Estado estadoAtualizada = this.service.update(idEstado,estado);

		assertThat(estadoAtualizada).isNotNull();
		assertThat(estadoAtualizada.getId()).isNotNull();
		assertThat(estadoAtualizada.getNome()).isEqualTo("AMAZONAS");
	}

	@Test
	public void updateCustomWithSuccess(){

		Long idEstado = 1l;

		mapBodyRequest.put("id",1l);
		mapBodyRequest.put("nome","BAHIA2");

		Object estado = mapBodyRequest;

		EstadoDto estadoAtualizada = (EstadoDto) this.service.updateCustom(idEstado,estado);

		assertThat(estadoAtualizada).isNotNull();
		assertThat(estadoAtualizada.getId()).isNotNull();
		assertThat(estadoAtualizada.getNome()).isEqualTo("BAHIA2");
	}

	@Test(expected = BadValueException.class)
	public void updateWithErrorWithNomeNull(){
		Estado estado = new Estado(null,null);
		estado = this.service.update(1l,estado);

	}

	@Test(expected = BadValueException.class)
	public void updateCustomWithErrorNomeNull(){

		Long idEstado = 1l;

		mapBodyRequest.put("id",1l);
		mapBodyRequest.put("nome",null);

		Object estado = mapBodyRequest;

		EstadoDto estadoAtualizada = (EstadoDto) this.service.updateCustom(idEstado,estado);
	}

	@Test(expected = TransactionSystemException.class)
	public void updateWithErrorWithNomeEmpty(){
		Estado estado = new Estado(null,"");
		estado = this.service.update(1l,estado);
	}

	@Test(expected = BadValueException.class)
	public void updateWithErrorWithEstadoNull(){
		this.service.update(1l,null);
	}

	@Test(expected = BadValueException.class)
	public void updateWithErrorWithIdNull(){
		Estado estado = new Estado(null,"Chinesa");
		this.service.update(null,estado);
	}

	@Test(expected = NegocioException.class)
	public void updateWithErrorEstadoExist(){
		Estado estado = new Estado(5l,"ALAGOAS");
		this.service.update(5l,estado);
	}

	@Test
	public void deleteWithSuccess(){

		Estado estado = new Estado(null,"Americana");
		estado = this.service.save(estado);
		this.service.delete(estado.getId());

	}

	@Test(expected = NoContentException.class)
	public void deleteWithErrorEstadoNotExist(){
		Estado estado = new Estado(99999l,null);
		this.service.delete( estado.getId());
	}


	@Test(expected = DataIntegrityViolationException.class)
	public void deleteWithErrorEstadoWhenUsed(){
		Estado estado = new Estado(2l,null);
		this.service.delete( estado.getId());
	}


	@Test
	public void findByIdWithSuccess(){
		Estado estado = this.service.findById(1l);
		assertThat(estado).isNotNull();
		assertThat(estado.getId()).isNotNull();
	}

	@Test(expected = BadValueException.class)
	public void findByIdWithErrorIdNull(){
		Estado estado = this.service.findById(null);
	}

	@Test(expected = NoContentException.class)
	public void findByIdWithErrorIdNotExists(){
		Estado estado = this.service.findById(99999l);
	}


}
