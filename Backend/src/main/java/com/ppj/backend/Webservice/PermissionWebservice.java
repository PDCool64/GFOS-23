/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.ppj.backend.Webservice;

import jakarta.ejb.Stateless;
import jakarta.websocket.server.PathParam;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.ppj.backend.Facades.PermissionFacade;
import com.ppj.backend.Service.ResponseService;

import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;

/**
 *
 * @author philipp.doering
 */
@Stateless
@LocalBean
public class PermissionWebservice {
    @EJB
    private PermissionFacade permissionFacade;
    
    @EJB
    private ResponseService responseService;


    @POST
    @Path("/login/{email}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(
        @HeaderParam("password") String password,
        @PathParam("email") String email
    ) {
        String token = permissionFacade.login(email, password);
        if(token == null) {
            return responseService.status(401, "Login fehlgeschlagen");
        } else {
            return responseService.ok(token);
        }
    }


}
