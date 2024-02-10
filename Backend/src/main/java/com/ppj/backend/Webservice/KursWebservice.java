/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.ppj.backend.Webservice;

import com.ppj.backend.Entity.Account;
import com.ppj.backend.Entity.Kurs;
import com.ppj.backend.Facades.AccountFacade;
import com.ppj.backend.Facades.KursFacade;
import com.ppj.backend.Facades.PermissionFacade;
import com.ppj.backend.Service.ResponseService;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.json.*;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbException;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
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
import java.io.StringWriter;

/**
 *
 * @author philipp.doering
 */
@Stateless
@LocalBean
@Path("/kurs")
public class KursWebservice {

	private final Jsonb jsonb = JsonbBuilder.create();

	@EJB
	private KursFacade kursFacade;

	@EJB
	private PermissionFacade permissionFacade;

	@EJB
	private ResponseService responseFacade;

	@EJB
	private AccountFacade accountFacade;

	private JsonObjectBuilder copyJsonObject(JsonObject jsonObject) {
		JsonObjectBuilder builder = Json.createObjectBuilder();

		for (String key : jsonObject.keySet()) {
			builder.add(key, jsonObject.get(key));
		}

		return builder;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createKurs(
		@HeaderParam("Authorization") String token,
		String json
	) {
		if (permissionFacade.isActive(token) == "") return responseFacade.ok(
			"Token ist ungültig"
		);
		try {
			JsonReader jsonReader = Json.createReader(new StringReader(json));
			JsonObject jsonObject = jsonReader.readObject();
			String email = jsonObject.getString("leiter");
			Account leiter = accountFacade.getAccountByEmail(email);
			if (leiter == null) {
				return responseFacade.ok(
					"Leiter konnte nicht gefunden werden."
				);
			}

			Kurs k = new Kurs();
			k.setLeiter(leiter);
			k.setArt(jsonObject.getString("art"));
			k.setFach(jsonObject.getString("fach"));
			k.setNummer(jsonObject.getInt("nummer"));
			k.setStufe(jsonObject.getInt("stufe"));
			System.out.println(k.getFach());

			Kurs kursAusDatenbank = kursFacade.createKurs(k);
			if (kursAusDatenbank == null) {
				return responseFacade.status(
					422,
					"Kurs konnte nicht erstellt werden."
				);
			} else {
				return responseFacade.ok(jsonb.toJson(kursAusDatenbank));
			}
		} catch (JsonbException e) {
			e.printStackTrace();
			return responseFacade.ok("Json konnte nicht geparst werden.");
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllKurse(@HeaderParam("Authorization") String token) {
		if (permissionFacade.isActive(token) == "") return responseFacade.ok(
			"Token ist ungültig"
		);
		try {
			return responseFacade.ok(jsonb.toJson(kursFacade.getAllKurse()));
		} catch (JsonbException e) {
			e.printStackTrace();
			return responseFacade.ok("Kurse konnten nicht geladen werden.");
		}
	}

	@GET
	@Path("/{id}")
	public Response getKursById(
		@HeaderParam("Authorization") String token,
		@PathParam("id") int id
	) {
		if (permissionFacade.isActive(token) == "") return responseFacade.ok(
			"Token ist ungültig"
		);
		try {
			Kurs kurs = kursFacade.getKursById(id);
			if (kurs == null) return responseFacade.ok(
				"Kurs konnte nicht gefunden werden"
			);
			return responseFacade.ok(jsonb.toJson(jsonb.toJson(kurs)));
		} catch (JsonbException e) {
			return responseFacade.ok("Kurs konnte nicht geladen werden.");
		}
	}

	@GET
	@Path("/checkincode/{checkincode}")
	public Response getKursByCheckinCode(
		@HeaderParam("Authorization") String token,
		@PathParam("checkincode") String checkincode
	) {
		if (permissionFacade.isActive(token) == "") return responseFacade.ok(
			"Token ist ungültig"
		);
		try {
			Kurs kurs = kursFacade.getKursByCheckinCode(checkincode);
			if (kurs == null) return responseFacade.ok(
				"Kurs konnte nicht gefunden werden"
			);
			return responseFacade.ok(jsonb.toJson(jsonb.toJson(kurs)));
		} catch (JsonbException e) {
			return responseFacade.ok("Kurs konnte nicht geladen werden.");
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateKurs(
		@HeaderParam("Authorization") String token,
		String json
	) {
		if (permissionFacade.isActive(token) == "") return responseFacade.ok(
			"Token ist ungültig"
		);
		try {
			Kurs k = jsonb.fromJson(json, Kurs.class);
			boolean kursInDatenbank = kursFacade.updateKurs(k);
			if (kursInDatenbank != false) {
				return responseFacade.ok("Kurs wurde geupdated.");
			}
			return responseFacade.ok("Kurs konnte nicht geupdated werden.");
		} catch (JsonbException e) {
			return responseFacade.ok("Json konnte nicht geparst werden.");
		}
	}

	@DELETE
	@Path("/{id}")
	public Response deleteKurs(
		@HeaderParam("Authorization") String token,
		@PathParam("id") int id
	) {
		if (permissionFacade.isActive(token) == "") return responseFacade.ok(
			"Token ist ungültig"
		);
		try {
			Kurs k = kursFacade.getKursById(id);
			boolean kursInDatenbank = kursFacade.deleteKurs(k);
			if (kursInDatenbank != false) {
				return responseFacade.ok("Kurs wurde gelöscht.");
			}
			return responseFacade.ok("Kurs konnte nicht gelöscht werden.");
		} catch (JsonbException e) {
			return responseFacade.ok("Kurs konnte nicht gelöscht werden.");
		}
	}

	@POST
	@Path("/teilnehmer/{kursId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTeilnehmer(
		@HeaderParam("Authorization") String token,
		@PathParam("kursId") int kursId,
		String json
	) {
		if (permissionFacade.isActive(token) == "") return responseFacade.ok(
			"Token ist ungültig"
		);
		try {
			Account k = jsonb.fromJson(json, Account.class);
			boolean kursInDatenbank = kursFacade.addTeilnehmer(kursId, k);
			if (!kursInDatenbank) {
				return responseFacade.ok("Teilnehmer wurde hinzugefügt.");
			}
			return responseFacade.ok(
				"Teilnehmer konnte nicht hinzugefügt werden."
			);
		} catch (JsonbException e) {
			return responseFacade.ok("Json konnte nicht geparst werden.");
		}
	}
}
