package com.ppj.backend.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author
 */
@Path("tests")
public class JakartaEE10Resource {

	@GET
	public Response ping() {
		return Response.ok("ping Jakarta EE").build();
	}

	@GET
	@Path("lol")
	public Response lol() {
		return Response.ok("lol").build();
	}
}
