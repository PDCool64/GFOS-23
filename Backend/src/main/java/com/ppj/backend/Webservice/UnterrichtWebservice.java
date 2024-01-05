/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.ppj.backend.Webservice;

import jakarta.ejb.Stateless;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.websocket.server.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import com.ppj.backend.Entity.Unterricht;
import com.ppj.backend.Facades.PermissionFacade;
import com.ppj.backend.Facades.UnterrichtFacade;

import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;

/**
 *
 * @author philipp.doering
 */
@Stateless
@LocalBean
@Path("/unterricht")
public class UnterrichtWebservice {
    
    private final Jsonb jsonb = JsonbBuilder.create();
    
    @EJB
    private UnterrichtFacade unterrichtFacade;

    @EJB
    private PermissionFacade permissionFacade;

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUnterricht(
        @HeaderParam("Authorization")
        String token,
        String json
    ){
        if(!permissionFacade.isActive(token)) return Response.ok("Token ist ungültig").build();
        try {
            Unterricht u = jsonb.fromJson(json, Unterricht.class);
            Unterricht unterrichtAusDatenbank = unterrichtFacade.createUnterricht(u);
            if(unterrichtAusDatenbank == null) return Response.ok("Unterricht konnte nicht erstellt werden").build();
            return Response.ok(jsonb.toJson(unterrichtAusDatenbank)).build();
        }
        catch(Exception e) {
            return Response.ok("Json konnte nicht geparst werden").build();
        }
    }
    

    /**
     * TODO: Implement this method
     * @param token
     * @param kursId
     * @return
     */
    @GET
    @Path("/{kursId}")
    public Response getAllForKursById(
        @HeaderParam("Authorization")
        String token,
        @PathParam("kursId")
        int kursId
    ){
        return Response.ok("Not implemented yet").build();
    }
}
