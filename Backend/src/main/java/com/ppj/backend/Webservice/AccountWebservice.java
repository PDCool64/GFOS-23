/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/EjbWebService.java to edit this template
 */
package com.ppj.backend.Webservice;

import com.ppj.backend.Entity.Account;
import com.ppj.backend.Facades.AccountFacade;
import com.ppj.backend.Facades.PermissionFacade;
import com.ppj.backend.Service.HashingService;
import com.ppj.backend.Service.ResponseService;

import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
	private ResponseService responseFacade;
	
	@EJB
	private HashingService hashingService;

	@PersistenceContext
	private EntityManager em;

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(
		@HeaderParam("Authorization") String token,
		@HeaderParam("password") String password,
		String json
	) {
		if (!permissionFacade.isActive(token)) 
			return responseFacade
				.status(401, "{\"error\": \"Token ist ungültig\"}");
		try {
			Account a = jsonb.fromJson(json, Account.class);
			if (accountFacade.getAccountByEmail(a.getEmail()) != null){ 
				return responseFacade
					.status(400, "{\"error\": \"Account existiert bereits.\"}");
			}		
			
			a.setPassworthash(hashingService.convertStringToHash(password));
			Account accountAusDatenbank = accountFacade.createAccount(a);
			return responseFacade.ok(jsonb.toJson(accountAusDatenbank));
		} catch (Exception e) {
			return responseFacade.status(
				422,
				"{\"error\": \"JSON-String konnte nicht geparsed werden.\", \"your request\": " +
				json +
				", \"errorMessage\": \"" +
				e.getMessage() +
				"\"}"
			);
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
