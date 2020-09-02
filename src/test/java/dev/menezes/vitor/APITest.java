package dev.menezes.vitor;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APITest {

	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "http://localhost:8001/tasks-backend";
	}
	
	@Test
	public void deveRetornarTarefas() {
		RestAssured.given()
		.when()
			.get("/todo")
		.then()
			.statusCode(200);
	}
	
	@Test
	public void deveAdicionarTarefasComSucesso() {
		RestAssured.given()
			.body("{\"task\":\"Teste via api\",\"dueDate\":\"2021-09-03\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("/todo")
		.then()
			.statusCode(201);
	}
	
	@Test
	public void naoDeveAdicionarTarefasComSucesso() {
		RestAssured.given()
			.body("{\"task\":\"Teste via api\",\"dueDate\":\"2010-09-03\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("/todo")
		.then()
			.statusCode(400)
			.body("message",CoreMatchers.is("Due date must not be in past"));
	}
	
}


