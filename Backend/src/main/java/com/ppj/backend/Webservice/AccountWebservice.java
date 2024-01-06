/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/EjbWebService.java to edit this template
 */
package com.ppj.backend.Webservice;

import com.ppj.backend.Entity.Account;
import com.ppj.backend.Facades.AccountFacade;
import com.ppj.backend.Facades.PermissionFacade;
import com.ppj.backend.Facades.ResponseFacade;

import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author phili
 */
@Stateless
@LocalBean
@Path("/account")
public class AccountWebservice {

	private final Jsonb jsonb = JsonbBuilder.create();

	@EJB
	private AccountFacade accountFacade;

	@EJB
	private PermissionFacade permissionFacade;

	@EJB
	private ResponseFacade responseFacade;

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(
		@HeaderParam("Authorization") String token,
		String json
	) {
		if (!permissionFacade.isActive(token)) return Response
			.status(401, "Token ist ungültig")
			.build();
		try {
			System.out.println(token);
			Account a = jsonb.fromJson(json, Account.class);
			Account accountAusDatenbank = accountFacade.createAccount(a);
			if (accountAusDatenbank == null) {
				return Response
					.ok("Account konnte nicht erstellt werden.")
					.build();
			} else {
				return responseFacade.ok(jsonb.toJson(accountAusDatenbank));
			}
		} catch (Exception e) {
			return responseFacade
				.status(422, "JSON-String konnte nicht geparsed werden.");
		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(
		@HeaderParam("Authorization") String token,
		@PathParam("id") int id
	) {
		if (!permissionFacade.isActive(token)) return Response
			.ok("Token ist ungültig")
			.build();
		Account a = accountFacade.getAccountById(id);
		if (a == null) {
			return responseFacade.ok("Account konnte nicht gefunden werden.");
		} else {
			return responseFacade.ok(jsonb.toJson(a));
		}
	}
}
