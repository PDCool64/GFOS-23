/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ppj.backend.Entity;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author philipp.doering
 */
@Entity
@Table(name = "ACCOUNT")
@NamedQueries(
	{
		@NamedQuery(
			name = "Account.findAll",
			query = "SELECT a FROM Account a"
		),
		@NamedQuery(
			name = "Account.findByName",
			query = "SELECT a FROM Account a WHERE a.name = :name"
		),
		@NamedQuery(
			name = "Account.findByVorname",
			query = "SELECT a FROM Account a WHERE a.vorname = :vorname"
		),
		@NamedQuery(
			name = "Account.findByPersonalnummer",
			query = "SELECT a FROM Account a WHERE a.personalnummer = :personalnummer"
		),
		@NamedQuery(
			name = "Account.findByGeburtsdatum",
			query = "SELECT a FROM Account a WHERE a.geburtsdatum = :geburtsdatum"
		),
		@NamedQuery(
			name = "Account.findByWochenstunden",
			query = "SELECT a FROM Account a WHERE a.wochenstunden = :wochenstunden"
		),
		@NamedQuery(
			name = "Account.findByEmail",
			query = "SELECT a FROM Account a WHERE a.email = :email"
		),
		@NamedQuery(
			name = "Account.findByPassworthash",
			query = "SELECT a FROM Account a WHERE a.passworthash = :passworthash"
		),
		@NamedQuery(
			name = "Account.findByIsadmin",
			query = "SELECT a FROM Account a WHERE a.isadmin = :isadmin"
		),
		@NamedQuery(
			name = "Account.findByToken",
			query = "SELECT a FROM Account a WHERE a.token = :token"
		),
		@NamedQuery(
			name = "Account.findByTimestampletzteaktion",
			query = "SELECT a FROM Account a WHERE a.timestampletzteaktion = :timestampletzteaktion"
		),
		@NamedQuery(
			name = "Account.findById",
			query = "SELECT a FROM Account a WHERE a.id = :id"
		),
	}
)
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "NAME")
	private String name;

	@Column(name = "VORNAME")
	private String vorname;

	@Column(name = "PERSONALNUMMER")
	private Integer personalnummer;

	@Column(name = "GEBURTSDATUM")
	@Temporal(TemporalType.DATE)
	private Date geburtsdatum;

	@Column(name = "WOCHENSTUNDEN")
	private Integer wochenstunden;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PASSWORTHASH")
	private String passworthash;

	@Column(name = "ISADMIN")
	private Boolean isadmin;

	@Column(name = "TOKEN")
	private String token;

	@Column(name = "TIMESTAMPLETZTEAKTION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestampletzteaktion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;

	@JoinTable(
		name = "TEILNAHME",
		joinColumns = {
			@JoinColumn(name = "ACCOUNT", referencedColumnName = "ID"),
		},
		inverseJoinColumns = {
			@JoinColumn(name = "KURS", referencedColumnName = "ID"),
		}
	)
	@ManyToMany
	private List<Kurs> kursList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
	private List<Meldung> meldungList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "leiter")
	private List<Kurs> kursList1;

	public Account() {}

	public Account(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public Integer getPersonalnummer() {
		return personalnummer;
	}

	public void setPersonalnummer(Integer personalnummer) {
		this.personalnummer = personalnummer;
	}

	public Date getGeburtsdatum() {
		return geburtsdatum;
	}

	public void setGeburtsdatum(Date geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	public Integer getWochenstunden() {
		return wochenstunden;
	}

	public void setWochenstunden(Integer wochenstunden) {
		this.wochenstunden = wochenstunden;
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

	public Date getTimestampletzteaktion() {
		return timestampletzteaktion;
	}

	public void setTimestampletzteaktion(Date timestampletzteaktion) {
		this.timestampletzteaktion = timestampletzteaktion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Kurs> getKursList() {
		return kursList;
	}

	public void setKursList(List<Kurs> kursList) {
		this.kursList = kursList;
	}

	public List<Meldung> getMeldungList() {
		return meldungList;
	}

	public void setMeldungList(List<Meldung> meldungList) {
		this.meldungList = meldungList;
	}

	public List<Kurs> getKursList1() {
		return kursList1;
	}

	public void setKursList1(List<Kurs> kursList1) {
		this.kursList1 = kursList1;
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
		if (!(object instanceof Account)) {
			return false;
		}
		Account other = (Account) object;
		if (
			(this.id == null && other.id != null) ||
			(this.id != null && !this.id.equals(other.id))
		) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ppj.backend.Entity.Account[ id=" + id + " ]";
	}
}
