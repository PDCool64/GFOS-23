/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.ppj.backend.Facades;

import com.ppj.backend.Entity.Account;
import com.ppj.backend.Entity.Kursteilnahme;
import com.ppj.backend.Entity.Stunde;
import com.ppj.backend.Entity.Unterricht;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author philipp.doering
 */
@Stateless
@LocalBean
public class StundeFacade {

	@PersistenceContext
	private EntityManager em;

	@EJB
	private UnterrichtFacade unterrichtFacade;

	public Stunde createStunde(Stunde stunde) {
		try {
			em.persist(stunde);
			em.flush();
			Stunde stundeMitId = this.getStundeById(stunde.getId());
			return stundeMitId;
		} catch (Exception e) {
			return null;
		}
	}

	public Stunde getStundeById(int id) {
		try {
			return em.find(Stunde.class, id);
		} catch (Exception e) {
			return null;
		}
	}

	public Stunde updateStunde(Stunde stunde) {
		try {
			em.merge(stunde);
			em.flush();
			Stunde stundeMitId = this.getStundeById(stunde.getId());
			return stundeMitId;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean deleteStunde(int id) {
		try {
			Stunde stunde = this.getStundeById(id);
			em.remove(stunde);
			em.flush();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Stunde> getAllStunden() {
		try {
			return em
				.createNamedQuery("Stunde.findAll", Stunde.class)
				.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public List<Stunde> getStundenByUnterricht(int unterrichtId) {
		try {
			return em
				.createNamedQuery("Stunde.findByUnterricht", Stunde.class)
				.setParameter("unterrichtId", unterrichtId)
				.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public List<Stunde> getStundenByAccount(Account account) {
		try {
			List<Stunde> stunden = new ArrayList<Stunde>();
			for (Kursteilnahme kt : account.getKursteilnahmeList()) {
				for (Unterricht u : kt.getKurs().getUnterrichtList()) {
					stunden.addAll(u.getStundeList());
				}
			}
			return stunden;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Stunde> getStundenByAccountAndDate(
		Account account,
		String startdate,
		String enddate
	) {
		try {
			List<Stunde> stunden = new ArrayList<Stunde>();
			Date start = new SimpleDateFormat("yyyy-MM-dd").parse(startdate);
			Date end = new SimpleDateFormat("yyyy-MM-dd").parse(enddate);
			for (Kursteilnahme kt : account.getKursteilnahmeList()) {
				for (Unterricht u : kt.getKurs().getUnterrichtList()) {
					for (Stunde s : u.getStundeList()) {
						if (
							start.compareTo(s.getDatum()) <= 0 &&
							end.compareTo(s.getDatum()) >= 0
						) {
							stunden.add(s);
						}
					}
				}
			}
			return stunden;
		} catch (Exception e) {
			return null;
		}
	}
}
