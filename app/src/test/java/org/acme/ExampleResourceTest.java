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
    
    private Solution newSolution() {
    	Solution solution = new Solution();
    	solution.setFilename("script.py");
    	solution.setProblem("a");
    	solution.setSourcecode("cHJpbnQoaW50KGlucHV0KCkpICogMik=");
    	return solution;
    }
    
    @Test
    public void shouldAddFileStatusFail() {
    	Solution solution = newSolution();
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
			.body(containsString("FAIL"))
			.extract().asString();

    }

}