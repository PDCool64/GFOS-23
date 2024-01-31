/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.ppj.backend.Facades;

import com.ppj.backend.Entity.Unterricht;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author philipp.doering
 */
@Stateless
@LocalBean
public class UnterrichtFacade {

	@PersistenceContext
	EntityManager em;

	public Unterricht createUnterricht(Unterricht u) {
		try {
			em.persist(u);
			Unterricht unterrichtMitId = this.getUnterrichtByKursId(u.getId());
			return unterrichtMitId;
		} catch (Exception e) {
			return null;
		}
	}

	public Unterricht getUnterrichtByKursId(int id) {
		try {
			return em.find(Unterricht.class, id);
		} catch (Exception e) {
			return null;
		}
	}

	public boolean updateUnterricht(Unterricht u) {
		try {
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteUnterricht(Unterricht u) {
		try {
			em.remove(u);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
