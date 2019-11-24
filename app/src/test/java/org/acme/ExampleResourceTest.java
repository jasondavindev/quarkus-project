package org.acme;


import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.CoreMatchers.*;

import javax.ws.rs.core.MediaType;

import org.acme.models.Solution;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ExampleResourceTest {

    @Test
    public void testEmpty() {
        given()
          .when().get("/maratona")
          .then()
             .statusCode(200)
             .body(is("[]"));
    }

	private Solution newSolutionB() {
		Solution solution = new Solution();
		solution.setFilename("script.py");
		solution.setProblem("b");
		solution.setSourcecode("YSA9IGlucHV0KCkKCmlmIGEgPT0gIjUgMyI6CglwcmludCgiMiA0IikKZWxpZiBhID09ICI2IDYiOgoJcHJpbnQoIioiKQ==");
		return solution;
	}
    
    private Solution newSolutionA() {
    	Solution solution = new Solution();
    	solution.setFilename("script.py");
    	solution.setProblem("a");
    	solution.setSourcecode("ZW50cmFkYSA9IGlucHV0KCkKdmFsb3JlcyA9IGVudHJhZGEuc3BsaXQoIiAiKQpBPWludCh2YWxvcmVzWzBdKQpCPWludCh2YWxvcmVzWzFdKQpDPWludCh2YWxvcmVzWzJdKQppZiAoQT09QiBhbmQgQT09QyBhbmQgQj09Qyk6CiAgICAgICAgVmVuY2Vkb3IgPSAnKicKZWxpZiAoQSE9QiBhbmQgQj09Qyk6CiAgICAgICAgVmVuY2Vkb3IgPSAnQScKZWxpZiAoQT09QyBhbmQgQiE9QSk6CiAgICAgICAgVmVuY2Vkb3IgPSAnQicKZWxpZiAoQT09QiBhbmQgQyE9QSk6CiAgICAgICAgVmVuY2Vkb3IgPSAnQycKcHJpbnQoVmVuY2Vkb3Ip");
    	return solution;
    }
    
    @Test
    public void shouldAddFileAStatusSuccess() {
    	Solution solution = newSolutionA();
        given()
	        .body(solution)
	        .contentType(MediaType.APPLICATION_JSON)
	        .when()
	        .post("/maratona")
	        .then()
	        .statusCode(CREATED.getStatusCode());

		given()
				.when().get("/maratona/problem/a")
				.then()
				.statusCode(OK.getStatusCode())
				.body(containsString("SUCCESS"))
				.extract().asString();

		given()
				.when().get("/maratona/date/" + solution.getTimestamp())
				.then()
				.statusCode(OK.getStatusCode())
				.body(containsString(String.valueOf(solution.getTimestamp())))
				.extract().asString();

		given()
			.when().get("/maratona/status/SUCCESS")
			.then()
			.statusCode(OK.getStatusCode())
			.body(containsString(String.valueOf(solution.getTimestamp())))
			.extract().asString();

    }

	@Test
	public void shouldAddFileAStatusFail() {
		Solution solution = newSolutionB();
		solution.setProblem("a");
		given()
				.body(solution)
				.contentType(MediaType.APPLICATION_JSON)
				.when()
				.post("/maratona")
				.then()
				.statusCode(CREATED.getStatusCode());

		given()
				.when().get("/maratona/status/FAIL")
				.then()
				.statusCode(OK.getStatusCode())
				.body(containsString(String.valueOf(solution.getTimestamp())))
				.extract().asString();

	}

	@Test
	public void shouldAddFileBStatusSuccess() {
		Solution solution = newSolutionB();
		given()
				.body(solution)
				.contentType(MediaType.APPLICATION_JSON)
				.when()
				.post("/maratona")
				.then()
				.statusCode(CREATED.getStatusCode());

		given()
				.when().get("/maratona/problem/b")
				.then()
				.statusCode(OK.getStatusCode())
				.body(containsString("SUCCESS"))
				.extract().asString();

		given()
				.when().get("/maratona/status/SUCCESS")
				.then()
				.statusCode(OK.getStatusCode())
				.body(containsString(String.valueOf(solution.getTimestamp())))
				.extract().asString();

	}

	@Test
	public void shouldAddFileBStatusFail() {
		Solution solution = newSolutionA();
		solution.setProblem("b");
		given()
				.body(solution)
				.contentType(MediaType.APPLICATION_JSON)
				.when()
				.post("/maratona")
				.then()
				.statusCode(CREATED.getStatusCode());

		given()
				.when().get("/maratona/status/FAIL")
				.then()
				.statusCode(OK.getStatusCode())
				.body(containsString(String.valueOf(solution.getTimestamp())))
				.extract().asString();

	}

}