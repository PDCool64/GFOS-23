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
@NamedQueries({
	@NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
	@NamedQuery(name = "Account.findById", query = "SELECT a FROM Account a WHERE a.id = :id"),
	@NamedQuery(name = "Account.findByEmail", query = "SELECT a FROM Account a WHERE a.email = :email"),
	@NamedQuery(name = "Account.findByVorname", query = "SELECT a FROM Account a WHERE a.vorname = :vorname"),
	@NamedQuery(name = "Account.findByName", query = "SELECT a FROM Account a WHERE a.name = :name"),
	@NamedQuery(name = "Account.findByGeburtsdatum", query = "SELECT a FROM Account a WHERE a.geburtsdatum = :geburtsdatum"),
	@NamedQuery(name = "Account.findByPassworthash", query = "SELECT a FROM Account a WHERE a.passworthash = :passworthash"),
	@NamedQuery(name = "Account.findByIsadmin", query = "SELECT a FROM Account a WHERE a.isadmin = :isadmin")})
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
	private Integer id;
	@Basic(optional = false)
    @Column(name = "EMAIL")
	private String email;
	@Basic(optional = false)
    @Column(name = "VORNAME")
	private String vorname;
	@Basic(optional = false)
    @Column(name = "NAME")
	private String name;
	@Basic(optional = false)
    @Column(name = "GEBURTSDATUM")
    @Temporal(TemporalType.DATE)
	private Date geburtsdatum;
	@Basic(optional = false)
    @Column(name = "PASSWORTHASH")
	private String passworthash;
	@Basic(optional = false)
    @Column(name = "ISADMIN")
	private Boolean isadmin;
	@JoinTable(name = "KURSTEILNAHME", joinColumns = {
    	@JoinColumn(name = "ACCOUNTID", referencedColumnName = "ID")}, inverseJoinColumns = {
    	@JoinColumn(name = "KURSID", referencedColumnName = "ID")})
    @ManyToMany
	private List<Kurs> kursList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
	private List<Stundeteilnahme> stundeteilnahmeList;
	@OneToMany(mappedBy = "leiterid")
	private List<Kurs> kursList1;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
	private List<Stundeleitung> stundeleitungList;

	public Account() {
	}

	public Account(Integer id) {
		this.id = id;
	}

	public Account(Integer id, String email, String vorname, String name, Date geburtsdatum, String passworthash, Boolean isadmin) {
		this.id = id;
		this.email = email;
		this.vorname = vorname;
		this.name = name;
		this.geburtsdatum = geburtsdatum;
		this.passworthash = passworthash;
		this.isadmin = isadmin;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getGeburtsdatum() {
		return geburtsdatum;
	}

	public void setGeburtsdatum(Date geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
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

	public List<Kurs> getKursList() {
		return kursList;
	}

	public void setKursList(List<Kurs> kursList) {
		this.kursList = kursList;
	}

	public List<Stundeteilnahme> getStundeteilnahmeList() {
		return stundeteilnahmeList;
	}

	public void setStundeteilnahmeList(List<Stundeteilnahme> stundeteilnahmeList) {
		this.stundeteilnahmeList = stundeteilnahmeList;
	}

	public List<Kurs> getKursList1() {
		return kursList1;
	}

	public void setKursList1(List<Kurs> kursList1) {
		this.kursList1 = kursList1;
	}

	public List<Stundeleitung> getStundeleitungList() {
		return stundeleitungList;
	}

	public void setStundeleitungList(List<Stundeleitung> stundeleitungList) {
		this.stundeleitungList = stundeleitungList;
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
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ppj.backend.Entity.Account[ id=" + id + " ]";
	}
	
}
