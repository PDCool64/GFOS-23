/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.ppj.backend.Webservice;

import jakarta.ejb.Stateless;

import javax.annotation.processing.Generated;

import com.ppj.backend.Entity.Account;
import com.ppj.backend.Facades.UnterrichtFacade;
import com.ppj.backend.Service.ResponseService;

import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
/**
 *
 * @author philipp.doering
 */
@Stateless
@LocalBean
public class UnterrichtWebservice {
	@EJB
	private UnterrichtFacade unterrichtFacade;
	@EJB
	private ResponseService responseService;
	@EJB
	private PermissionFacade permissionFacade;


	@GET 
	public getUnterricht(
		@HeaderParam("Authorization") String token
	) {
		if(!permissionFacade.isActive(token))
			return responseService.unauthorized();
		Account account = permissionFacade.getAccount(token);
		return responseService.ok(unterrichtFacade.getUnterrichtByAccount(account));
	} 

}
