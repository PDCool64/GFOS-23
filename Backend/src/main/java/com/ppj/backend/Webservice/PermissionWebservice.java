/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.ppj.backend.Webservice;

import com.ppj.backend.Entity.Account;
import com.ppj.backend.Facades.AccountFacade;
import com.ppj.backend.Facades.PermissionFacade;
import com.ppj.backend.Facades.PermissionFacade.TokenID;
import com.ppj.backend.Service.ResponseService;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.persistence.NoResultException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.StringReader;

/**
 *
 * @author philipp.doering
 */
@Stateless
@LocalBean
@Path("/login")
public class PermissionWebservice {

	private final Jsonb jsonb = JsonbBuilder.create();

	@EJB
	private PermissionFacade permissionFacade;

	@EJB
	private ResponseService responseService;

	@EJB
	private AccountFacade accountFacade;

	@GET
	public Response test() {
		return responseService.ok("test");
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(String body) {
		String email, password;
		try {
			JsonReader jsonReader = Json.createReader(new StringReader(body));
			JsonObject jsonObject = jsonReader.readObject();
			email = jsonObject.getString("email");
			password = jsonObject.getString("password");
		} catch (Exception e) {
			return responseService.status(400, "Bad Request");
		}
		TokenID tokenID;
		try {
			tokenID = permissionFacade.login(email, password);
		} catch (NoResultException e) {
			return responseService.status(403, "Account nicht gefunden");
		}
		if (tokenID == null) {
			return responseService.unauthorized("Falsche Anmeldedaten");
		} else {
			Account a = accountFacade.getAccountById(tokenID.id);
			JsonObject json = Json.createObjectBuilder()
				.add("token", tokenID.token)
				.add("account", jsonb.toJson(a))
				.build();
			return responseService.ok(json.toString());
		}
	}
}
