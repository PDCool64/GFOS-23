/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/EjbWebService.java to edit this template
 */
package com.ppj.backend.Webservice;

import com.ppj.backend.Entity.Account;
import com.ppj.backend.Facades.AccountFacade;
import javax.jws.WebService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
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
@WebService(serviceName = "AccountWebservice")
@Stateless()
public class AccountWebservice {
    private final Jsonb jsonb = JsonbBuilder.create();
    @EJB
    private AccountFacade accountFacade;
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(String json)
    {
        try
        {
            Account a = jsonb.fromJson(json, Account.class);
            Account accountAusDatenbank = accountFacade.createAccount(a);
            if(accountAusDatenbank == null)
            {
                return Response
                    .ok("Account konnte nicht erstellt werden.")
                    .build();
            }
            else
            {
                return Response
                    .ok(jsonb.toJson(accountAusDatenbank))
                    .build();
            }
        }
        catch(Exception e)
        {
            return Response
                .ok("JSON-String konnte nicht geparsed werden.")
                .build();
        }
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id)
    {
        Account a = accountFacade.getAccountById(id);
        if(a == null)
        {
            return Response
                    .ok("Account konnte nicht gefunden werden.")
                    .build();
        }
        else
        {
            return Response
                    .ok(jsonb.toJson(a))
                    .build();
        }
    }
}
