/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.ppj.backend.Webservice;

import com.ppj.backend.Entity.Account;
import com.ppj.backend.Entity.Unterricht;
import com.ppj.backend.Facades.AccountFacade;
import com.ppj.backend.Facades.PermissionFacade;
import com.ppj.backend.Facades.StundeFacade;
import com.ppj.backend.Facades.UnterrichtFacade;
import com.ppj.backend.Service.ResponseService;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.json.Json;
import jakarta.json.JsonReader;
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
import java.io.StringReader;
import java.util.List;

/**
 *
 * @author philipp.doering
 */
@Stateless
@LocalBean
@Path("/unterricht")
public class UnterrichtWebservice {

	@EJB
	private UnterrichtFacade unterrichtFacade;

	@EJB
	private StundeFacade stundeFacade;

	@EJB
	private ResponseService responseService;

	@EJB
	private PermissionFacade permissionFacade;

	@EJB
	AccountFacade accountFacade;

	private final Jsonb jsonb = JsonbBuilder.create();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUnterricht(@HeaderParam("Authorization") String token) {
		if (
			permissionFacade.isActive(token) == ""
		) return responseService.unauthorized();
		Account account = permissionFacade.getAccountByToken(token);
		return responseService.ok(
			jsonb.toJson(unterrichtFacade.getUnterrichtByAccount(account))
		);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUnterrichtById(@PathParam("id") int id) {
		try {
			Account account = accountFacade.getAccountById(id);
			List<Unterricht> unterrichtList = unterrichtFacade.getUnterrichtByAccount(
				account
			);
			return responseService.ok(jsonb.toJson(unterrichtList));
		} catch (Exception e) {
			return responseService.status(404, "Unterricht nicht gefunden");
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUnterricht(
		@HeaderParam("Authorization") String token,
		String json
	) {
		try {
			JsonReader jsonReader = Json.createReader(new StringReader(json));
			String unterrichtString = jsonReader
				.readObject()
				.getString("unterricht");
			String startDate = jsonReader.readObject().getString("startDate");
			String endDate = jsonReader.readObject().getString("endDate");
			Unterricht unterricht = jsonb.fromJson(
				unterrichtString,
				Unterricht.class
			);
			Unterricht unterrichtInDatenbank = unterrichtFacade.createUnterricht(
				unterricht
			);
			stundeFacade.createStunden(
				unterrichtInDatenbank,
				startDate,
				endDate
			);
			return responseService.ok("Unterricht erstellt");
		} catch (Exception e) {
			return responseService.status(
				400,
				"Fehler beim Erstellen des Unterrichts"
			);
		}
	}
}
