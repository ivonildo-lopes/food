package com.fabrica.food.api.controller;

import static io.restassured.RestAssured.*;

import com.fabrica.food.utill.ResourceUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.flywaydb.core.Flyway;
import static org.hamcrest.Matchers.equalTo;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CozinhaControllerIT {

	@LocalServerPort
	private int port;

	@Autowired
	private Flyway flyway;


	private static final int COZINHA_ID_INEXISTENTE = 300;
	private static final int COZINHA_ID_EXISTENTE = 2;
	private String jsonCozinha;

	@Before
	public void init(){

		jsonCozinha = ResourceUtil.getContentFromResource("/json/cozinha.json");

		// essa linha abaixo mostrar o log da requisição quando o teste falha
		enableLoggingOfRequestAndResponseIfValidationFails();

		RestAssured.port = port;
		RestAssured.basePath = "/v1/cozinhas";

		flyway.migrate();
	}

	@Test
	public void returnStatus200WhenFindAllCozinhas(){
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}


	@Test
	public void returnStatus200WhenFindById(){

		given()
				.pathParam("id",COZINHA_ID_EXISTENTE)
				.accept(ContentType.JSON)
		.when()
				.get("/{id}")
		.then()
				.statusCode(HttpStatus.OK.value())
				.body("data.nome", equalTo("INDIANA"));
	}

	@Test
	public void returnStatus404WhenFindByIdNotExist(){

		given()
				.pathParam("id", COZINHA_ID_INEXISTENTE)
				.accept(ContentType.JSON)
				.when()
				.get("/{id}")
				.then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}

	@Test(expected = IllegalArgumentException.class)
	public void returnStatusErrorWhenFindByIdisNull(){

		given()
				.pathParam("id", null)
				.accept(ContentType.JSON)
				.when()
				.get("/{id}")
				.then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}

	@Test
	public void returnStatus200WhenFindAllCozinhasAndVerifyNome(){

		given()
				.accept(ContentType.JSON)
		.when()
				.get()
		.then()
//				.body("data", Matchers.hasSize(18))
				.body("data.nome", Matchers.hasItems("INDIANA"))
				.statusCode(HttpStatus.OK.value());
	}


	@Test
	public void returnStatus201WhenSaveCozinha(){

		given()
			.accept(ContentType.JSON)
			.body(jsonCozinha)
//			.body("{\"nome\":\"AMERICANA\"}")
			.contentType(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}


}
