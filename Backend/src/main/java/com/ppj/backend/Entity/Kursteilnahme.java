/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ppj.backend.Entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author philipp.doering
 */
@Entity
@Table(name = "KURSTEILNAHME")
@NamedQueries({
	@NamedQuery(name = "Kursteilnahme.findAll", query = "SELECT k FROM Kursteilnahme k"),
	@NamedQuery(name = "Kursteilnahme.findById", query = "SELECT k FROM Kursteilnahme k WHERE k.id = :id")})
public class Kursteilnahme implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
	private Integer id;
	@JoinColumn(name = "ACCOUNT", referencedColumnName = "ID")
    @ManyToOne(optional = false)
	private Account account;
	@JoinColumn(name = "KURS", referencedColumnName = "ID")
    @ManyToOne(optional = false)
	private Kurs kurs;

	public Kursteilnahme() {
	}

	public Kursteilnahme(Account account, Kurs kurs) {
		this.account = account;
		this.kurs = kurs;
	}

	public Kursteilnahme(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Kurs getKurs() {
		return kurs;
	}

	public void setKurs(Kurs kurs) {
		this.kurs = kurs;
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
		if (!(object instanceof Kursteilnahme)) {
			return false;
		}
		Kursteilnahme other = (Kursteilnahme) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ppj.backend.Entity.Kursteilnahme[ id=" + id + " ]";
	}
	
}
