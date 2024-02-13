/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.ppj.backend.Webservice;

import com.ppj.backend.Entity.Account;
import com.ppj.backend.Facades.AccountFacade;
import com.ppj.backend.Facades.PermissionFacade;
import com.ppj.backend.Facades.StundeFacade;
import com.ppj.backend.Service.ResponseService;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author philipp.doering
 */
@Stateless
@LocalBean
@Path("/stunde")
public class StundeWebservice {

	private final Jsonb jsonb = JsonbBuilder.create();

	@EJB
	private StundeFacade stundeFacade;

	@EJB
	private PermissionFacade permissionFacade;

	@EJB
	private ResponseService responseService;

	@EJB
	private AccountFacade accountFacade;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{startdate}/{enddate}")
	public Response getStunden(
		@HeaderParam("Authorization") String token,
		@PathParam("startdate") String startdate,
		@PathParam("enddate") String enddate
	) {
		if (
			permissionFacade.isActive(token) == ""
		) return responseService.unauthorized();
		Account account = permissionFacade.getAccountByToken(token);
		return responseService.ok(
			jsonb.toJson(
				stundeFacade.getStundenByAccountAndDate(
					account,
					startdate,
					enddate
				)
			)
		);
	}

	@GET
	@Path("/{accountId}/{startdate}/{enddate}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStundeById(
		@HeaderParam("Authorization") String token,
		@PathParam("accoutId") int id,
		@PathParam("startdate") String startdate,
		@PathParam("enddate") String enddate
	) {
		Account account = accountFacade.getAccountById(id);
		return responseService.ok(
			jsonb.toJson(
				stundeFacade.getStundenByAccountAndDate(
					account,
					startdate,
					enddate
				)
			)
		);
	}

	@GET
	@Path("/teilnahme/{stundeId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTeilnahmenByStundeId(
		@HeaderParam("Authorization") String token,
		@PathParam("stundeId") int id
	) {
		if (
			permissionFacade.isActive(token) == ""
		) return responseService.unauthorized();
		return responseService.ok(
			jsonb.toJson(stundeFacade.getTeilnahmenByStundeId(id))
		);
	}

	
}
