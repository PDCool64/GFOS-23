/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.ppj.backend.Facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

import com.ppj.backend.Entity.Stundebewertung;

import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;

/**
 *
 * @author philipp.doering
 */
@Stateless
@LocalBean
public class BewertungsFacade {
	@PersistenceContext
	EntityManager em;

	@EJB
	KursFacade kursFacade;

	@EJB
	AccountFacade accountFacade;

	public Stundebewertung createBewertung(Stundebewertung b) {
		try {
			em.persist(b);
			em.flush();
			Stundebewertung bewertungMitId = this.getBewertungById(b.getId());
			return bewertungMitId;
		} catch (Exception e) {
			return null;
		}
	}

	public Stundebewertung getBewertungById(int id) {
		try {
			return em.find(Stundebewertung.class, id);
		} catch (Exception e) {
			return null;
		}
	}

	public List<Stundebewertung> getAllBewertungen() {
		try {
			return em
				.createNamedQuery("Stundebewertung.findAll", Stundebewertung.class)
				.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public Stundebewertung updateBewertung(Stundebewertung b) {
		try {
			em.merge(b);
			return b;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean deleteBewertung(Stundebewertung b) {
		try {
			em.remove(b);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
