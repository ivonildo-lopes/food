package com.fabrica.food.api.controller;

import com.fabrica.food.utill.ResourceUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.flywaydb.core.Flyway;
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

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class RestauranteControllerIT {

	@LocalServerPort
	private int port;

	@Autowired
	private Flyway flyway;

	private static final int RESTAURANTE_ID_INEXISTENTE = 300;
	private static final int RESTAURANTE_ID_EXISTENTE = 2;
	private String jsonRestaurante;
	private String jsonRestauranteCozinhaNotExist;
	private String jsonRestauranteIdCozinhaNull;

	@Before
	public void init(){

		jsonRestaurante = ResourceUtil.getContentFromResource("/json/restaurante.json");
		jsonRestauranteCozinhaNotExist = ResourceUtil.getContentFromResource("/json/restauranteCozinhaIdNotExist.json");
		jsonRestauranteIdCozinhaNull = ResourceUtil.getContentFromResource("/json/restauranteCozinhaIdNull.json");

		enableLoggingOfRequestAndResponseIfValidationFails();

		RestAssured.port = port;
		RestAssured.basePath = "/v1/restaurantes";

		flyway.migrate();
	}

	@Test
	public void returnStatus200WhenFindAllRestaurantes(){
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
				.pathParam("id", RESTAURANTE_ID_EXISTENTE)
				.accept(ContentType.JSON)
		.when()
				.get("/{id}")
		.then()
				.statusCode(HttpStatus.OK.value())
				.body("data.nome", equalTo("ordones"));
	}

	@Test
	public void returnStatus404WhenFindByIdNotExist(){

		given()
				.pathParam("id", RESTAURANTE_ID_INEXISTENTE)
				.accept(ContentType.JSON)
				.when()
				.get("/{id}")
				.then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}


	@Test
	public void returnStatus400WhenFindByCozinhaIdNotExist(){
		given()
				.accept(ContentType.JSON)
				.body(jsonRestauranteIdCozinhaNull)
				.contentType(ContentType.JSON)
		.when()
				.post()
		.then()
				.statusCode(HttpStatus.BAD_REQUEST.value());

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
	public void returnStatus200WhenFindAllRestaurantesAndVerifyNome(){

		given()
				.accept(ContentType.JSON)
		.when()
				.get()
		.then()
//				.body("data", Matchers.hasSize(18))
				.body("data.nome", Matchers.hasItems("ordones"))
				.statusCode(HttpStatus.OK.value());
	}


	@Test
	public void returnStatus201WhenSaveRestaurante(){

		given()
				.accept(ContentType.JSON)
				.body(jsonRestaurante)
//			.body("{\"nome\":\"AMERICANA\"}")
				.contentType(ContentType.JSON)
				.when()
				.post()
				.then()
				.statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void returnStatus400WhenSaveRestauranteWithIdCozinhaNull(){

		given()
				.accept(ContentType.JSON)
				.body(jsonRestaurante)
				.contentType(ContentType.JSON)
		.when()
				.post()
		.then()
				.statusCode(HttpStatus.CREATED.value());
	}


}
