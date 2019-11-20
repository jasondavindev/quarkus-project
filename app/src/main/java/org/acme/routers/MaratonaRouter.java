package org.acme.routers;

import java.net.URI;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.acme.controllers.MaratonaController;
import org.acme.models.Solution;

@Path("/maratona")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MaratonaRouter {
	@Inject
	private MaratonaController controller;

	@POST
	public Response save(final Solution solution, @Context final UriInfo uriInfo) {
		controller.save(solution);
		URI createdURI = uriInfo.getAbsolutePathBuilder().build();
		return Response.created(createdURI).build();
	}

	@GET
	@Path("/status/{status}")
	public List<Solution> filterByStatus(@PathParam("status") final String status) {
		return controller.filterByStatus(status);
	}

	@GET
	@Path("/problem/{problem}")
	public List<Solution> filterByProblem(@PathParam("problem") final String problem) {
		return controller.filterByProblem(problem);
	}

	@GET
	@Path("/date/{datetime}")
	public List<Solution> filterByDatetime(@PathParam("datetime") final long datetime) {
		return controller.filterByDatetime(datetime);
	}

	@GET
	public Set<Solution> show() {
		return controller.getSolutions();
	}
}
