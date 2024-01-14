/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.ppj.backend.Facades;

import com.ppj.backend.Entity.Account;
import com.ppj.backend.Service.HashingService;
import com.ppj.backend.Service.TokenService;

import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

/**
 *
 *
 * @author phili
 *
 */
@Stateless
@LocalBean
public class PermissionFacade {

	public class TokenID {
		public String token;
		public int id;
		public TokenID(String token, int id) {
			this.token = token;
			this.id = id;
		}
	}

	@PersistenceContext
	private EntityManager em;

	@EJB
	TokenService tokenService;

	@EJB
	HashingService hashingService;

	@EJB 
	AccountFacade accountFacade;

	/**
	 * Tries to login with the given credentials
	 * @param email
	 * @param password
	 * @return token if login was successful, null if not
	 */
	public TokenID login(String email, String password) throws NoResultException {
		try {
			Account a = em
				.createNamedQuery("Account.findByEmail", Account.class)
				.setParameter("email", email)
				.getSingleResult();
			String passwordhash = hashingService.convertStringToHash(password);
			if (!(a.getPassworthash().equals(passwordhash))) return null;
			String token = generateToken(a);
			return new TokenID(token, a.getId()); 
		} 
		catch (NoResultException e){
			return null;
		}
		catch (Exception e) {
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
		return accountFacade.getAccountByEmail(tokenService.verifyToken(token));
	}

	public boolean logout(String token) {
		return true;	
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
