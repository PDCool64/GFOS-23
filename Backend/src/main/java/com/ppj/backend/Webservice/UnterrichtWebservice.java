/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.ppj.backend.Webservice;

import jakarta.ejb.Stateless;

import com.ppj.backend.Facades.UnterrichtFacade;
import com.ppj.backend.Service.ResponseService;

import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;

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
	
}
