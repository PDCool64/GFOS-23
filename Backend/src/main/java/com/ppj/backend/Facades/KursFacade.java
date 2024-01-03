/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.ppj.backend.Facades;

import com.ppj.backend.Entity.Kurs;
import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author phili
 */
@Stateless
@LocalBean
public class KursFacade {
	@PersistenceContext	
	private EntityManager em;	
	
	public Kurs create(Kurs k){
	   try{
		   em.persist(k);
		   Kurs kursMitId = this.getKursById(k.getId());
		   return kursMitId;
	   }
	   catch(Exception e){
		   return null;
	   }
	}

	public Kurs getKursById(Integer id) {
		try {
			return em.find(Kurs.class, id);
		} catch(Exception e) {
			return null;
		}
	}

	public List<Kurs> getAllKurse() {
		try {
		   return em.createNamedQuery("Kurs.findAll").getResultList();
		}
		catch(Exception e) {
			return null;
		}
	}

}
