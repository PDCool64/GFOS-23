/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.ppj.backend.Facades;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author philipp.doering
 */
@Stateless
@LocalBean
public class ResponseFacade {

	/*
	 * .header("Access-Control-Allow-Origin", "*")
			.header("Access-Control-Allow-Credentials", "true")
			.header(
				"Access-Control-Allow-Headers",
				"origin, content-type, accept, authorization"
			)
			.header(
				"Access-Control-Allow-Methods",
				"GET, POST, PUT, DELETE, OPTIONS, HEAD"
			)
	 */

	public Response ok(String data) {
		return Response
			.status(200)
			.entity(data)
			.build();
	}

	public Response status(int status, String data) {
		return Response
			.status(status)
			.entity(data)
			.build();
	}
}
