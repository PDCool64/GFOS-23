/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ppj.backend.Entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author phili
 */
@Entity
@Table(name = "ACCOUNTS")
@NamedQueries({
	@NamedQuery(name = "Accounts.findAll", query = "SELECT a FROM Accounts a"),
	@NamedQuery(name = "Accounts.findById", query = "SELECT a FROM Accounts a WHERE a.id = :id"),
	@NamedQuery(name = "Accounts.findByVorname", query = "SELECT a FROM Accounts a WHERE a.vorname = :vorname"),
	@NamedQuery(name = "Accounts.findByNachname", query = "SELECT a FROM Accounts a WHERE a.nachname = :nachname"),
	@NamedQuery(name = "Accounts.findByGeburtstag", query = "SELECT a FROM Accounts a WHERE a.geburtstag = :geburtstag"),
	@NamedQuery(name = "Accounts.findByWochenstunden", query = "SELECT a FROM Accounts a WHERE a.wochenstunden = :wochenstunden"),
	@NamedQuery(name = "Accounts.findByPersonalnummer", query = "SELECT a FROM Accounts a WHERE a.personalnummer = :personalnummer"),
	@NamedQuery(name = "Accounts.findByEmail", query = "SELECT a FROM Accounts a WHERE a.email = :email"),
	@NamedQuery(name = "Accounts.findByPassworthash", query = "SELECT a FROM Accounts a WHERE a.passworthash = :passworthash"),
	@NamedQuery(name = "Accounts.findByIsadmin", query = "SELECT a FROM Accounts a WHERE a.isadmin = :isadmin"),
	@NamedQuery(name = "Accounts.findByToken", query = "SELECT a FROM Accounts a WHERE a.token = :token"),
	@NamedQuery(name = "Accounts.findByLetzteaktion", query = "SELECT a FROM Accounts a WHERE a.letzteaktion = :letzteaktion")})
public class Accounts implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
	private Integer id;
	@Basic(optional = false)
    @Column(name = "VORNAME")
	private String vorname;
	@Basic(optional = false)
    @Column(name = "NACHNAME")
	private String nachname;
	@Basic(optional = false)
    @Column(name = "GEBURTSTAG")
    @Temporal(TemporalType.TIMESTAMP)
	private Date geburtstag;
	@Basic(optional = false)
    @Column(name = "WOCHENSTUNDEN")
	private int wochenstunden;
	@Basic(optional = false)
    @Column(name = "PERSONALNUMMER")
	private int personalnummer;
	@Basic(optional = false)
    @Column(name = "EMAIL")
	private String email;
	@Basic(optional = false)
    @Column(name = "PASSWORTHASH")
	private String passworthash;
	@Basic(optional = false)
    @Column(name = "ISADMIN")
	private Boolean isadmin;
	@Basic(optional = false)
    @Column(name = "TOKEN")
	private String token;
	@Basic(optional = false)
    @Column(name = "LETZTEAKTION")
    @Temporal(TemporalType.TIMESTAMP)
	private Date letzteaktion;

	public Accounts() {
	}

	public Accounts(Integer id) {
		this.id = id;
	}

	public Accounts(Integer id, String vorname, String nachname, Date geburtstag, int wochenstunden, int personalnummer, String email, String passworthash, Boolean isadmin, String token, Date letzteaktion) {
		this.id = id;
		this.vorname = vorname;
		this.nachname = nachname;
		this.geburtstag = geburtstag;
		this.wochenstunden = wochenstunden;
		this.personalnummer = personalnummer;
		this.email = email;
		this.passworthash = passworthash;
		this.isadmin = isadmin;
		this.token = token;
		this.letzteaktion = letzteaktion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public Date getGeburtstag() {
		return geburtstag;
	}

	public void setGeburtstag(Date geburtstag) {
		this.geburtstag = geburtstag;
	}

	public int getWochenstunden() {
		return wochenstunden;
	}

	public void setWochenstunden(int wochenstunden) {
		this.wochenstunden = wochenstunden;
	}

	public int getPersonalnummer() {
		return personalnummer;
	}

	public void setPersonalnummer(int personalnummer) {
		this.personalnummer = personalnummer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassworthash() {
		return passworthash;
	}

	public void setPassworthash(String passworthash) {
		this.passworthash = passworthash;
	}

	public Boolean getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(Boolean isadmin) {
		this.isadmin = isadmin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLetzteaktion() {
		return letzteaktion;
	}

	public void setLetzteaktion(Date letzteaktion) {
		this.letzteaktion = letzteaktion;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Accounts)) {
			return false;
		}
		Accounts other = (Accounts) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ppj.backend.Entities.Accounts[ id=" + id + " ]";
	}
	
}
