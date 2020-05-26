package com.fabrica.food.api.controller;


import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CozinhaControllerIT {

	@LocalServerPort
	private int port;

	@Test
	public void returnStatus200WhenFindAllCozinhas(){
			enableLoggingOfRequestAndResponseIfValidationFails();

			given()
				.basePath("/v1/cozinhas")
				.port(port)
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void returnStatus200WhenFindAllCozinhasAndVerifyNome(){
		enableLoggingOfRequestAndResponseIfValidationFails();

		given()
				.basePath("/v1/cozinhas")
				.port(port)
				.accept(ContentType.JSON)
		.when()
				.get()
		.then()
//				.body("data", Matchers.hasSize(18))
				.body("data.nome", Matchers.hasItems("INDIANA"))
				.statusCode(HttpStatus.OK.value());
	}


}
