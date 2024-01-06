/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.ppj.backend.Facades;

import com.ppj.backend.Entity.Account;
import com.ppj.backend.Service.TokenService;

import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * <h2>TODO: Implement a sophisticated hashing and token generation algorithm!! </h2>
 *
 * @author phili
 *
 */
@Stateless
@LocalBean
public class PermissionFacade {

	@PersistenceContext
	private EntityManager em;

	@EJB
	TokenService tokenService;
	/**
	 * Tries to login with the given credentials
	 * @param email
	 * @param password
	 * @return token if login was successful, null if not
	 */
	public String login(String email, String password) {
		String passwordhash = password;
		try {
			Account a = em
				.createNamedQuery("Account.findByEmail", Account.class)
				.setParameter("email", email)
				.getSingleResult();
			if (a.getPassworthash() == passwordhash) {
				String token = generateToken(a);
				a.setToken(token);
				return token;
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Generates a random token
	 * @return token
	 */
	private String generateToken(Account a) {
		return tokenService.createNewToken(a.getEmail());
	}

	public Account getAccountByToken(String token) {
		try {
			return em
				.createNamedQuery("Account.findByToken", Account.class)
				.setParameter("token", token)
				.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public boolean logout(String token) {
		try {
			Account a = getAccountByToken(token);
			a.setToken(null);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isActive(String token) {
		if (token == null) return true;
		try {
			return tokenService.verifyToken(token) != "";
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isAdmin(String token) {
		try {
			Account a = getAccountByToken(token);
			return a.getIsadmin();
		} catch (Exception e) {
			return false;
		}
	}
}
