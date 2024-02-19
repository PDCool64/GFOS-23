/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.ppj.backend.Webservice;

import com.ppj.backend.Entity.Account;
import com.ppj.backend.Entity.Stunde;
import com.ppj.backend.Entity.Stundeteilnahme;
import com.ppj.backend.Facades.AccountFacade;
import com.ppj.backend.Facades.PermissionFacade;
import com.ppj.backend.Facades.StundeFacade;
import com.ppj.backend.Service.ResponseService;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

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
		Account a = permissionFacade.getAccountByToken(token);
		Stunde s = stundeFacade.getStundeById(id);
		if(s == null) {
			return responseService.status(404, "{\"message\": \"Stunde nicht gefunden\"}");
		}
		if (s.getUnterricht().getKurs().getLeiter().getId() != a.getId()) {
			return responseService.status(403, "{\"message\": \"Keine Berechtigung\"}");
		}
		return responseService.ok(
			jsonb.toJson(stundeFacade.getTeilnahmenByStundeId(id))
		);
	}

	@PUT
	@Path("/teilnahme/{stundeId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTeilnahme(
		@HeaderParam("Authorization") String token,
		@PathParam("stundeId") int id,
		String json
	) {
		if (
			permissionFacade.isActive(token) == ""
		) return responseService.unauthorized();
		Jsonb jsonb = JsonbBuilder.create();
		List<Stundeteilnahme> stundenteilnahmenList = jsonb.fromJson(
			json,
			new ArrayList<Stundeteilnahme>() {}
				.getClass()
				.getGenericSuperclass()
		);
		List<Stundeteilnahme> updated = stundeFacade.updateTeilnahmen(
			stundenteilnahmenList
		);
		return responseService.ok(jsonb.toJson(updated));
	}

	@GET
	@Path("/aktuell")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAktuelleStunde(
		@HeaderParam("Authorization") String token
	) {
		if (
			permissionFacade.isActive(token) == ""
		) return responseService.unauthorized();
		Account a = permissionFacade.getAccountByToken(token);
		System.out.println(a.getId());
		return responseService.ok(
			jsonb.toJson(stundeFacade.getAktuelleStunde(a))
		);
	}

	@POST
	@Path("/checkin/{stundeId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkin(
		@HeaderParam("Authorization") String token,
		@PathParam("stundeId") int id,
		String json
	) {
		if (
			permissionFacade.isActive(token) == ""
		) return responseService.unauthorized();
		JsonReader reader = Json.createReader(new StringReader(json));
		JsonObject jsonObject = reader.readObject();
		jsonObject.getString("code");
		Account account = permissionFacade.getAccountByToken(token);
		Stunde s = stundeFacade.getStundeById(id);
		if (s == null) {
			return responseService.status(
				404,
				"{\"message\": \"Stunde nicht gefunden\"}"
			);
		}
		if (!s.getCheckincode().equals(jsonObject.getString("code"))) {
			System.out.println(
				s.getCheckincode() + " " + jsonObject.getString("code")
			);
			return responseService.status(
				421,
				"{\"message\": \"Checkin fehlgeschlagen\"}"
			);
		}

		if (stundeFacade.checkin(account, s)) {
			return responseService.ok("{\"message\": \"Checkin erfolgreich\"}");
		} else {
			return responseService.status(
				422,
				"{\"message\": \"Checkin fehlgeschlagen\"}"
			);
		}
	}
}
