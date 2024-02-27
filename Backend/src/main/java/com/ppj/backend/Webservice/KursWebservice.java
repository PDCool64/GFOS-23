/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.ppj.backend.Webservice;

import com.ppj.backend.Entity.Account;
import com.ppj.backend.Entity.Kurs;
import com.ppj.backend.Entity.Stundeteilnahme;
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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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
	@Path("/teilnehmer/{kursId}/{email}") // http://localhost:8080/Backend/kurs/teilnehmer/1/1 -> POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addTeilnehmer(
		@HeaderParam("Authorization") String token,
		@PathParam("kursId") int kursId,
		@PathParam("email") String accountId,
		String json
	) {
		if (
			permissionFacade.isActive(token) == ""
		) return responseFacade.unauthorized();
		Account a = permissionFacade.getAccountByToken(token);
		Kurs k = kursFacade.getKursById(kursId);
		if (k == null) return responseFacade.status(
			422,
			"Kurs konnte nicht gefunden werden."
		);
		if (k.getLeiter() != a) return responseFacade.status(
			401,
			"{\"error\": \"Sie sind nicht berechtigt, Teilnehmer hinzuzufügen.\"}"
		);
		Account teilnehmer = accountFacade.getAccountByEmail(accountId);
		if (teilnehmer == null) return responseFacade.status(
			422,
			"{\"error\": \"Teilnehmer konnte nicht gefunden werden.\"}"
		);
		kursFacade.addTeilnehmer(kursId, teilnehmer);
		return responseFacade.ok("{\"success\": \"Teilnehmer hinzugefügt.\"}");
	}

	@GET
	@Path("/teilnehmer/{accountId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTeilnahmenByAccount(
		@HeaderParam("Authorization") String token,
		@PathParam("accountId") int accountId
	) {
		if (permissionFacade.isActive(token) == "") return responseFacade.ok(
			"Token ist ungültig"
		);
		return responseFacade.ok(
			jsonb.toJson(kursFacade.getKurseByAccountId(accountId))
		);
	}

	@GET
	@Path("/leiter")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLeiterKurse(
		@HeaderParam("Authorization") String token,
		@PathParam("accountId") int accountId
	) {
		if (permissionFacade.isActive(token) == "") return responseFacade.ok(
			"Token ist ungültig"
		);
		List<Kurs> kurse = new LinkedList<Kurs>();
		Account a = permissionFacade.getAccountByToken(token);
		kurse = kursFacade.getKurseByLeiter(a);
		return responseFacade.ok(jsonb.toJson(kurse));
	}

	@GET
	@Path("/teilnahmen/{kursId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTeilnahmenByKurs(
		@HeaderParam("Authorization") String token,
		@PathParam("kursId") int kursId
	) {
		if (permissionFacade.isActive(token) == "") return responseFacade.ok(
			"Token ist ungültig"
		);
		return responseFacade.ok(
			jsonb.toJson(kursFacade.getTeilnahmenByKurs(kursId))
		);
	}

	@GET
	@Path("/leiter/{kursId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response isLeiter(
		@HeaderParam("Authorization") String token,
		@PathParam("kursId") int kursId
	) {
		if (permissionFacade.isActive(token) == "") return responseFacade.ok(
			"Token ist ungültig"
		);
		Account a = permissionFacade.getAccountByToken(token);
		Kurs k = kursFacade.getKursById(kursId);
		return responseFacade.ok(jsonb.toJson(k.getLeiter() == a));
	}

	@DELETE
	@Path("/teilnehmer/{kursId}/{accountId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTeilnehmer(
		@HeaderParam("Authorization") String token,
		@PathParam("kursId") int kursId,
		@PathParam("accountId") int accountId
	) {
		if (permissionFacade.isActive(token) == "") return responseFacade.ok(
			"Token ist ungültig"
		);
		Account a = permissionFacade.getAccountByToken(token);
		Kurs k = kursFacade.getKursById(kursId);
		if (k == null) return responseFacade.status(
			422,
			"Kurs konnte nicht gefunden werden."
		);
		if (k.getLeiter() != a) return responseFacade.status(
			401,
			"{\"error\": \"Sie sind nicht berechtigt, Teilnehmer zu löschen.\"}"
		);
		Account teilnehmer = accountFacade.getAccountById(accountId);
		if (teilnehmer == null) return responseFacade.status(
			422,
			"{\"error\": \"Teilnehmer konnte nicht gefunden werden.\"}"
		);
		kursFacade.deleteTeilnehmer(k, accountId);
		return responseFacade.ok("{\"success\": \"Teilnehmer gelöscht.\"}");
	}

	@GET
	@Path("/stats/{kursId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStats(
		@HeaderParam("Authorization") String token,
		@PathParam("kursId") int kursId
	) {
		if (permissionFacade.isActive(token) == "") return responseFacade.ok(
			"Token ist ungültig"
		);
		Kurs k = kursFacade.getKursById(kursId);
		if (k == null) return responseFacade.status(
			422,
			"Kurs konnte nicht gefunden werden."
		);
		HashMap<String, List<Stundeteilnahme>> stats = kursFacade.getStats(k);
		return responseFacade.ok(jsonb.toJson(stats));
	}
}
