package com.fabrica.food.api.controller;


import static io.restassured.RestAssured.*;

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
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CozinhaControllerIT {

	@LocalServerPort
	private int port;

	@Autowired
	private Flyway flyway;

	@Before
	public void init(){
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
			.body("{\"nome\":\"AMERICANA\"}")
			.contentType(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}


}
