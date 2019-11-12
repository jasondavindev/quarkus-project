package org.acme.resources;

import java.util.Set;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.acme.controllers.MaratonaController;
import org.acme.models.Solution;

@Path("/maratona")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MaratonaResource {
	private MaratonaController controller;

	public MaratonaResource() {
		controller = new MaratonaController();
	}

	@POST
	public Set<Solution> save(Solution solution) {
		return controller.save(solution);
	}

	@GET
	@Path("/status/{status}")
	public List<Solution> filterByStatus(@PathParam("status") String status) {
		return controller.filterByStatus(status);
	}

	@GET
	@Path("/problem/{problem}")
	public List<Solution> filterByProblem(@PathParam("problem") String problem) {
		return controller.filterByProblem(problem);
	}

	@GET
	@Path("/date/{datetime}")
	public List<Solution> filterByDatetime(@PathParam("datetime") long datetime) {
		return controller.filterByDatetime(datetime);
	}

	@GET
	public Set<Solution> show() {
		return controller.getSolutions();
	}
}
