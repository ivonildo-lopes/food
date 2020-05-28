package com.fabrica.food.service;

import com.fabrica.food.domain.dto.FormaPagamentoDto;
import com.fabrica.food.domain.exception.BadValueException;
import com.fabrica.food.domain.exception.NegocioException;
import com.fabrica.food.domain.exception.NoContentException;
import com.fabrica.food.domain.model.FormaPagamento;
import com.fabrica.food.domain.service.FormasPagamentoService;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
public class FormaPagamentoServiceIT {

	@Autowired
	private FormasPagamentoService service;
	Map<String, Object> mapBodyRequest = new HashMap<>();

	@Autowired
	private Flyway flyway;

	@Before
	public void init(){
		flyway.migrate();
	}

	@Test
	public void saveWithSuccess(){

		FormaPagamento formaPagamento = new FormaPagamento(null,"PAGUE SEGURO");
		formaPagamento = this.service.save(formaPagamento);
		assertThat(formaPagamento).isNotNull();
		assertThat(formaPagamento.getId()).isNotNull();
	}

	@Test
	public void saveCustomWithSuccess(){
		mapBodyRequest.put("descricao","PAGUE SEGURO");

		Object formaPagamento = mapBodyRequest;

		FormaPagamentoDto formaPagamentoDto = (FormaPagamentoDto) this.service.saveCustom(formaPagamento);
		assertThat(formaPagamentoDto).isNotNull();
		assertThat(formaPagamentoDto.getId()).isNotNull();
	}

	@Test(expected = BadValueException.class)
	public void saveWithErrorWithDescricaoNull(){

		FormaPagamento formaPagamento = new FormaPagamento(null,null);

		formaPagamento = this.service.save(formaPagamento);
	}

	@Test(expected = BadValueException.class)
	public void saveWithErrorWithDescricaoEmpty(){
		FormaPagamento formaPagamento = new FormaPagamento(null,"");
		formaPagamento = this.service.save(formaPagamento);
	}

	@Test(expected = BadValueException.class)
	public void saveCustomWithErrorWithNomeEmpty(){

		mapBodyRequest.put("id",9l);
		mapBodyRequest.put("descricao","");

		Object formaPagamento = mapBodyRequest;

		this.service.saveCustom(formaPagamento);
	}

	@Test(expected = BadValueException.class)
	public void saveWithErrorWithFormaPagamentoNull(){
		FormaPagamento formaPagamento = null;
		formaPagamento = this.service.save(formaPagamento);
	}

	@Test(expected = NegocioException.class)
	public void saveWithErrorFormaPagamentoExist(){
		FormaPagamento formaPagamento = new FormaPagamento(null,"DINHEIRO");
		this.service.save(formaPagamento);
	}

	@Test
	public void updateWithSuccess(){

		Long idFormaPagamento = 4l;

		FormaPagamento formaPagamento = new FormaPagamento(null,"PIC PAY 2");

		FormaPagamento formaPagamentoAtualizada = this.service.update(idFormaPagamento,formaPagamento);

		assertThat(formaPagamentoAtualizada).isNotNull();
		assertThat(formaPagamentoAtualizada.getId()).isNotNull();
		assertThat(formaPagamentoAtualizada.getDescricao()).isEqualTo("PIC PAY 2");
	}

	@Test
	public void updateCustomWithSuccess(){

		Long idFormaPagamento = 4l;

		mapBodyRequest.put("id",4l);
		mapBodyRequest.put("descricao","PIC PAY 2");

		Object formaPagamento = mapBodyRequest;

		FormaPagamentoDto formaPagamentoAtualizada = (FormaPagamentoDto) this.service.updateCustom(idFormaPagamento,formaPagamento);

		assertThat(formaPagamentoAtualizada).isNotNull();
		assertThat(formaPagamentoAtualizada.getId()).isNotNull();
		assertThat(formaPagamentoAtualizada.getDescricao()).isEqualTo("PIC PAY 2");
	}

	@Test(expected = BadValueException.class)
	public void updateWithErrorWithNomeNull(){
		FormaPagamento formaPagamento = new FormaPagamento(null,null);
		formaPagamento = this.service.update(1l,formaPagamento);
	}

	@Test(expected = BadValueException.class)
	public void updateCustomWithErrorNomeNull(){

		Long idFormaPagamento = 1l;

		mapBodyRequest.put("id",1l);
		mapBodyRequest.put("descricao",null);

		Object formaPagamento = mapBodyRequest;

		FormaPagamentoDto formaPagamentoAtualizada = (FormaPagamentoDto) this.service.updateCustom(idFormaPagamento,formaPagamento);
	}

	@Test(expected = BadValueException.class)
	public void updateWithErrorWithNomeEmpty(){
		FormaPagamento formaPagamento = new FormaPagamento(null,"");
		formaPagamento = this.service.update(1l,formaPagamento);
	}

	@Test(expected = BadValueException.class)
	public void updateWithErrorWithFormaPagamentoNull(){
		this.service.update(1l,null);
	}

	@Test(expected = BadValueException.class)
	public void updateWithErrorWithIdNull(){
		FormaPagamento formaPagamento = new FormaPagamento(null,"PAG SEGURO");
		this.service.update(null,formaPagamento);
	}

	@Test(expected = NegocioException.class)
	public void updateWithErrorFormaPagamentoExist(){
		FormaPagamento formaPagamento = new FormaPagamento(4l,"DINHEIRO");
		this.service.update(4l,formaPagamento);
	}

	@Test(expected = BadValueException.class)
	public void updateCustomWithErrorWithNomeEmpty(){

		mapBodyRequest.put("id",1l);
		mapBodyRequest.put("descricao","");

		Object formaPagamento = mapBodyRequest;

		this.service.updateCustom(1l, formaPagamento);
	}

	@Test
	public void deleteWithSuccess(){
		FormaPagamento formaPagamento = new FormaPagamento(4l,null);
		this.service.delete(formaPagamento.getId());
	}

	@Test(expected = NoContentException.class)
	public void deleteWithErrorFormaPagamentoNotExist(){
		FormaPagamento formaPagamento = new FormaPagamento(99999l,null);
		this.service.delete( formaPagamento.getId());
	}


	@Test(expected = DataIntegrityViolationException.class)
	public void deleteWithErrorFormaPagamentoWhenUsed(){
		FormaPagamento formaPagamento = new FormaPagamento(1l,null);
		this.service.delete( formaPagamento.getId());
	}


	@Test
	public void findByIdWithSuccess(){
		FormaPagamento formaPagamento = this.service.findById(1l);
		assertThat(formaPagamento).isNotNull();
		assertThat(formaPagamento.getId()).isNotNull();
	}

	@Test(expected = BadValueException.class)
	public void findByIdWithErrorIdNull(){
		FormaPagamento formaPagamento = this.service.findById(null);
	}

	@Test(expected = NoContentException.class)
	public void findByIdWithErrorIdNotExists(){
		FormaPagamento formaPagamento = this.service.findById(99999l);
	}


}
