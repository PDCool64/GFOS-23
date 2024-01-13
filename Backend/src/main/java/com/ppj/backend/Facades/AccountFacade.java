/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.ppj.backend.Facades;

import com.ppj.backend.Entity.Account;
import com.ppj.backend.Entity.Meldung;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author phili
 */
@Stateless
@LocalBean
public class AccountFacade {

	@PersistenceContext
	private EntityManager em;

	public Account createAccount(Account a) {
		try {
			em.persist(a);
			// Account accountMitId = this.getAccountById(a.getId());
			return a;
		} catch (Exception e) {
			return null;
		}
	}

	public Account getAccountByEmail(String email) {
		try {
			return em
				.createNamedQuery("Account.findByEmail", Account.class)
				.setParameter("email", email)
				.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public Account getAccountById(int id) {
		try {
			return em.find(Account.class, id);
		} catch (Exception e) {
			return null;
		}
	}

	public List<Account> getAllAccounts() {
		try {
			return em
				.createNamedQuery("Account.findAll", Account.class)
				.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	 public Account updateAccount(Account a) {
		try {
			Account accountInDatenbank = this.getAccountById(a.getId());
			accountInDatenbank.setEmail(a.getEmail());
			accountInDatenbank.setGeburtsdatum(a.getGeburtsdatum());
			accountInDatenbank.setIsadmin(a.getIsadmin());
			accountInDatenbank.setName(a.getName());
			accountInDatenbank.setPassworthash(a.getPassworthash());
			accountInDatenbank.setVorname(a.getVorname());
			em.merge(accountInDatenbank);
			return accountInDatenbank;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean deleteAccount(Account a) {
		try {
			em.remove(a);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean createMeldung(Account a, Meldung m) {
		try {
			Account accountInDatenbank = this.getAccountById(a.getId());
			m.setAccount(accountInDatenbank);
			em.persist(m);
			accountInDatenbank.getMeldungList().add(m);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
