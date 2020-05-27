package com.fabrica.food.service;

import com.fabrica.food.domain.dto.CidadeDto;
import com.fabrica.food.domain.dto.EstadoDto;
import com.fabrica.food.domain.exception.BadValueException;
import com.fabrica.food.domain.exception.NegocioException;
import com.fabrica.food.domain.exception.NoContentException;
import com.fabrica.food.domain.model.Cidade;
import com.fabrica.food.domain.model.Estado;
import com.fabrica.food.domain.service.CidadeService;
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

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
public class CidadeServiceIT {

	@Autowired
	private CidadeService service;
	Map<String, Object> mapBodyRequest = new HashMap<>();

	@Autowired
	private Flyway flyway;

	@Before
	public void init(){
		flyway.migrate();
	}

	@Test
	public void saveWithSuccess(){

		Estado estado = new Estado(3l,null);

		Cidade cidade = new Cidade(null,"CANINDE",estado);
		cidade = this.service.save(cidade);
		assertThat(cidade).isNotNull();
		assertThat(cidade.getId()).isNotNull();
	}

	@Test
	public void saveCustomWithSuccess(){
		mapBodyRequest.put("nome","BAHIA2");
		mapBodyRequest.put("idEstado",1l);

		Object cidade = mapBodyRequest;

		CidadeDto cidadeDto = (CidadeDto) this.service.saveCustom(cidade);
		assertThat(cidadeDto).isNotNull();
		assertThat(cidadeDto.getId()).isNotNull();
	}

	@Test(expected = BadValueException.class)
	public void saveWithErrorWithNomeNull(){
		Estado estado = new Estado(3l,null);

		Cidade cidade = new Cidade(null,null,estado);

		cidade = this.service.save(cidade);
	}

	@Test(expected = TransactionSystemException.class)
	public void saveWithErrorWithNomeEmpty(){
		Estado estado = new Estado(3l,null);

		Cidade cidade = new Cidade(null,"",estado);
		cidade = this.service.save(cidade);
	}

	@Test(expected = BadValueException.class)
	public void saveCustomWithErrorWithNomeEmpty(){

		mapBodyRequest.put("id",9l);
		mapBodyRequest.put("nome","");
		mapBodyRequest.put("idEstado",9l);

		Object cidade = mapBodyRequest;

		this.service.saveCustom(cidade);
	}

	@Test(expected = BadValueException.class)
	public void saveWithErrorWithCidadeNull(){
		Cidade cidade = null;
		cidade = this.service.save(cidade);
	}

	@Test(expected = NegocioException.class)
	public void saveWithErrorCidadeExistInEstado(){
		Estado estado = new Estado(3l,null);

		Cidade cidade = new Cidade(null,"FORTALEZA",estado);

		this.service.save(cidade);
	}

	@Test(expected = NegocioException.class)
	public void saveWithErrorEstadoNull(){
		Cidade cidade = new Cidade(null,"FORTALEZA",null);
		this.service.save(cidade);
	}

	@Test(expected = NegocioException.class)
	public void saveWithErrorEstadoIdNull(){
		Estado estado = new Estado(null,"CEARA");
		Cidade cidade = new Cidade(null,"FORTALEZA",null);
		this.service.save(cidade);
	}

	@Test
	public void updateWithSuccess(){

		Long idCidade = 9l;

		Estado estado = new Estado(4l,null);

		Cidade cidade = new Cidade(null,"CAJUEIRO 2",estado);

		Cidade cidadeAtualizada = this.service.update(idCidade,cidade);

		assertThat(cidadeAtualizada).isNotNull();
		assertThat(cidadeAtualizada.getId()).isNotNull();
		assertThat(cidadeAtualizada.getNome()).isEqualTo("CAJUEIRO 2");
	}

	@Test
	public void updateCustomWithSuccess(){

		Long idCidade = 9l;

		mapBodyRequest.put("id",9l);
		mapBodyRequest.put("nome","CAJUEIRO 2");
		mapBodyRequest.put("idEstado",4l);

		Object cidade = mapBodyRequest;

		CidadeDto cidadeAtualizada = (CidadeDto) this.service.updateCustom(idCidade,cidade);

		assertThat(cidadeAtualizada).isNotNull();
		assertThat(cidadeAtualizada.getId()).isNotNull();
		assertThat(cidadeAtualizada.getNome()).isEqualTo("CAJUEIRO 2");
	}

	@Test(expected = BadValueException.class)
	public void updateWithErrorWithNomeNull(){
		Cidade cidade = new Cidade(null,null,null);
		cidade = this.service.update(1l,cidade);
	}

	@Test(expected = BadValueException.class)
	public void updateCustomWithErrorNomeNull(){

		Long idCidade = 1l;

		mapBodyRequest.put("id",1l);
		mapBodyRequest.put("nome",null);

		Object cidade = mapBodyRequest;

		CidadeDto cidadeAtualizada = (CidadeDto) this.service.updateCustom(idCidade,cidade);
	}

	@Test(expected = BadValueException.class)
	public void updateWithErrorWithNomeEmpty(){
		Cidade cidade = new Cidade(null,"",null);
		cidade = this.service.update(1l,cidade);
	}

	@Test(expected = BadValueException.class)
	public void updateWithErrorWithCidadeNull(){
		this.service.update(1l,null);
	}

	@Test(expected = BadValueException.class)
	public void updateWithErrorWithIdNull(){
		Cidade cidade = new Cidade(null,"CAJUEIRO",null);
		this.service.update(null,cidade);
	}

	@Test(expected = NegocioException.class)
	public void updateWithErrorCidadeExistInEstado(){
		Estado estado = new Estado(4l,"ALAGOS");

		Cidade cidade = new Cidade(5l,"CAJUEIRO",estado);
		this.service.update(5l,cidade);
	}

	@Test(expected = BadValueException.class)
	public void updateCustomWithErrorWithNomeEmpty(){

		mapBodyRequest.put("id",9l);
		mapBodyRequest.put("nome","");
		mapBodyRequest.put("idEstado",9l);

		Object cidade = mapBodyRequest;

		this.service.updateCustom(1l, cidade);
	}

	@Test
	public void deleteWithSuccess(){
		Cidade cidade = new Cidade(10l,null,null);
		this.service.delete(cidade.getId());
	}

	@Test(expected = NoContentException.class)
	public void deleteWithErrorCidadeNotExist(){
		Cidade cidade = new Cidade(99999l,null,null);
		this.service.delete( cidade.getId());
	}


	@Test(expected = DataIntegrityViolationException.class)
	public void deleteWithErrorCidadeWhenUsed(){
		Cidade cidade = new Cidade(1l,null,null);
		this.service.delete( cidade.getId());
	}


	@Test
	public void findByIdWithSuccess(){
		Cidade cidade = this.service.findById(1l);
		assertThat(cidade).isNotNull();
		assertThat(cidade.getId()).isNotNull();
	}

	@Test(expected = BadValueException.class)
	public void findByIdWithErrorIdNull(){
		Cidade cidade = this.service.findById(null);
	}

	@Test(expected = NoContentException.class)
	public void findByIdWithErrorIdNotExists(){
		Cidade cidade = this.service.findById(99999l);
	}


}
