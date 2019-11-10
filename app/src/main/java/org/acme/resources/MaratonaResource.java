package org.acme.resources;

import java.util.Set;
import java.util.Collections;
import java.util.LinkedHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.models.Solution;
import org.acme.services.ValidationService;

@Path("/maratona")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MaratonaResource {
	private Set<Solution> solutions = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

	@POST
	public Set<Solution> save(Solution solution) {
		boolean executionResult = ValidationService.runValidation(solution);
		solution.setStatus(executionResult == true ? "SUCCESS" : "FAIL");
		solutions.add(solution);
		return solutions;
	}

	@GET
	public Set<Solution> show() {
		return solutions;
	}
}
