/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.ppj.backend.Webservice;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.ejb.EJB;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
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

import com.ppj.backend.Entity.Account;
import com.ppj.backend.Entity.Kurs;
import com.ppj.backend.Facades.KursFacade;
import com.ppj.backend.Facades.PermissionFacade;



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

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createKurs(
		@HeaderParam("Authorization")
		String token,
		String json
	){
		if(!permissionFacade.isActive(token)) return Response.ok("Token ist ungültig").build();
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
	public Response getAllKurse(
		@HeaderParam("Authorization")
		String token
	){
		if(!permissionFacade.isActive(token)) return Response.ok("Token ist ungültig").build();
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
		@HeaderParam("Authorization")
		String token,
		@PathParam("id")
		int id
	){
		if(!permissionFacade.isActive(token)) return Response.ok("Token ist ungültig").build();
		try {
			Kurs kurs = kursFacade.getKursById(id);
			if (kurs == null) return Response.ok("Kurs konnte nicht gefunden werden").build();
			return Response
				.ok(jsonb.toJson(jsonb.toJson(kurs)))
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
		@HeaderParam("Authorization")
		String token,
		@PathParam("checkincode")
		String checkincode
	){
		if(!permissionFacade.isActive(token)) return Response.ok("Token ist ungültig").build();
		try {
			Kurs kurs = kursFacade.getKursByCheckinCode(checkincode);
			if (kurs == null) return Response.ok("Kurs konnte nicht gefunden werden").build();
			return Response
				.ok(jsonb.toJson(jsonb.toJson(kurs)))
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
	public Response updateKurs(
		@HeaderParam("Authorization")
		String token,
		String json
	){
		if(!permissionFacade.isActive(token)) return Response.ok("Token ist ungültig").build();
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
		@HeaderParam("Authorization")
		String token, 
		@PathParam("id")
		int id
	){
		if(!permissionFacade.isActive(token)) return Response.ok("Token ist ungültig").build();
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
		@HeaderParam("Authorization")
		String token,
		@PathParam("kursId")
		int kursId,
		String json
		){
		if(!permissionFacade.isActive(token)) return Response.ok("Token ist ungültig").build();
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
		@HeaderParam("Authorization")
		String token,
		@PathParam("kursId")
		int kursId,
		String json
		){
		if(!permissionFacade.isActive(token)) return Response.ok("Token ist ungültig").build();
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
