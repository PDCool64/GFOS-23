/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.ppj.backend.Webservice;

import jakarta.ejb.Stateless;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.ppj.backend.Entity.Account;
import com.ppj.backend.Entity.Kurs;
import com.ppj.backend.Facades.KursFacade;

import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;

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

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createKurs(String json) {
		try {
			Kurs k = jsonb.fromJson(json, Kurs.class);
			Kurs kursAusDatenbank = kursFacade.createKurs(k);
			if (kursAusDatenbank == null) {
				return Response
					.ok("Kurs konnte nicht erstellt werden.")
					.build();
			}
			else {
				return Response
					.ok(jsonb.toJson(kursAusDatenbank))
					.build();
			}
		}
		catch (Exception e) {
			return Response
				.ok("Json konnte nicht geparst werden.")
				.build();
		}
	}

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllKurse() {
		try {
			return Response
				.ok(jsonb.toJson(kursFacade.getAllKurse()))
				.build();
		}
		catch (Exception e) {
			return Response
				.ok("Kurse konnten nicht geladen werden.")
				.build();
		}
	}

	@GET
	@Path("/{id}")
	public Response getKursById(
		@PathParam("id")
		int id
	){
		try {
			return Response
				.ok(jsonb.toJson(kursFacade.getKursById(id)))
				.build();
		}
		catch (Exception e) {
			return Response
				.ok("Kurs konnte nicht geladen werden.")
				.build();
		}
	}

	@GET
	@Path("/checkincode/{checkincode}")
	public Response getKursByCheckinCode(
		@PathParam("checkincode")
		String checkincode
	){
		try {
			return Response
				.ok(jsonb.toJson(kursFacade.getKursByCheckinCode(checkincode)))
				.build();
		}
		catch (Exception e) {
			return Response
				.ok("Kurs konnte nicht geladen werden.")
				.build();
		}
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)	
	public Response updateKurs(String json) {
		try {
			Kurs k = jsonb.fromJson(json, Kurs.class);
			boolean kursInDatenbank = kursFacade.updateKurs(k);
			if (kursInDatenbank != false) {
				return Response
					.ok("Kurs wurde geupdated.")
					.build();
			}
			return Response
				.ok("Kurs konnte nicht geupdated werden.")
				.build();
		}
		catch (Exception e) {
			return Response
				.ok("Json konnte nicht geparst werden.")
				.build();
		}
	}

	@DELETE
	@Path("/{id}")
	public Response deleteKurs(
		@PathParam("id")
		int id
	){
		try {
			Kurs k = kursFacade.getKursById(id);
			boolean kursInDatenbank = kursFacade.deleteKurs(k);
			if (kursInDatenbank != false) {
				return Response
					.ok("Kurs wurde gelöscht.")
					.build();
			}
			return Response
				.ok("Kurs konnte nicht gelöscht werden.")
				.build();
		}
		catch (Exception e) {
			return Response
				.ok("Kurs konnte nicht gelöscht werden.")
				.build();
		}
	}

	 @POST
	 @Path("/leitung/{kursId}")
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Response setLeitung(
		@PathParam("kursId")
		int kursId,
		String json
		){
		 try {
			 Account k = jsonb.fromJson(json, Account.class);
			 boolean kursInDatenbank = kursFacade.setLeitung(kursId, k);
			 if (!kursInDatenbank) {
				 return Response
					 .ok("Leitung wurde gesetzt.")
					 .build();
			 }
			 return Response
				 .ok("Leitung konnte nicht gesetzt werden.")
				 .build();
		 }
		 catch (Exception e) {
			 return Response
				 .ok("Json konnte nicht geparst werden.")
				 .build();
		 }
	 }

	 @POST
	 @Path("/teilnehmer/{kursId}")
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Response addTeilnehmer(
		@PathParam("kursId")
		int kursId,
		String json
		){
		 try {
			 Account k = jsonb.fromJson(json, Account.class);
			 boolean kursInDatenbank = kursFacade.addTeilnehmer(kursId, k);
			 if (!kursInDatenbank) {
				 return Response
					 .ok("Teilnehmer wurde hinzugefügt.")
					 .build();
			 }
			 return Response
				 .ok("Teilnehmer konnte nicht hinzugefügt werden.")
				 .build();
		 }
		 catch (Exception e) {
			 return Response
				 .ok("Json konnte nicht geparst werden.")
				 .build();
		 }
	 }
}
