/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.ppj.backend.Facades;

import com.ppj.backend.Entity.Account;
import com.ppj.backend.Entity.Stundeteilnahme;

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
			em.flush();
			Account accountMitId = this.getAccountById(a.getId());
			return accountMitId;
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
			accountInDatenbank.setVorname(a.getVorname());
			em.merge(accountInDatenbank);
			em.flush();
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

	public Stundeteilnahme createTeilnahme(Stundeteilnahme s) {
		try {
			em.persist(s);
			em.flush();
			return s;
		} catch (Exception e) {
			return null;
		}
	}

	public Stundeteilnahme getTeilnahmeById(int id) {
		try {
			return em.find(Stundeteilnahme.class, id);
		} catch (Exception e) {
			return null;
		}
	}

	public List<Stundeteilnahme> getAllTeilnahmen() {
		try {
			return em
				.createNamedQuery("Stundeteilnahme.findAll", Stundeteilnahme.class)
				.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Updates a Stundeteilnahme record in the database.
	 *
	 * @param s The Stundeteilnahme object that contains the updated data.
	 * @return The updated Stundeteilnahme object if the update was successful, null otherwise.
	 */
	public Stundeteilnahme updateTeilnahme(Stundeteilnahme s) {
		try {
			// Fetch the existing Stundeteilnahme record from the database using the ID from the input object.
			Stundeteilnahme teilnahmeInDatenbank = this.getTeilnahmeById(s.getId());

			// Update the fields of the fetched Stundeteilnahme record with the data from the input object.
			teilnahmeInDatenbank.setAccount(s.getAccount());
			teilnahmeInDatenbank.setBegintimestamp(s.getBegintimestamp());
			teilnahmeInDatenbank.setEndtimestamp(s.getEndtimestamp());
			teilnahmeInDatenbank.setNote (s.getNote());
			teilnahmeInDatenbank.setStunde(s.getStunde());

			// Merge the updated Stundeteilnahme record with the existing one in the database.
			em.merge(teilnahmeInDatenbank);

			// Return the updated Stundeteilnahme record.
			return teilnahmeInDatenbank;
		} catch (Exception e) {
			// If any exception occurs during the update process, return null.
			return null;
		}
	}
	/**
	 * Deletes a Stundeteilnahme record from the database.
	 *
	 * @param s The Stundeteilnahme object to be deleted.
	 * @return true if the deletion was successful, false otherwise.
	 */
	public boolean deleteTeilnahme(Stundeteilnahme s) {
		try {
			// Remove the Stundeteilnahme record from the database.
			em.remove(s);
			// If the removal was successful, return true.
			return true;
		} catch (Exception e) {
			// If any exception occurs during the removal process, return false.
			return false;
		}
	}
}
