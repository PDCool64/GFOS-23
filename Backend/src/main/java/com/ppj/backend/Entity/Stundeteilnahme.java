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
@Table(name = "STUNDETEILNAHME")
@NamedQueries({
	@NamedQuery(name = "Stundeteilnahme.findAll", query = "SELECT s FROM Stundeteilnahme s"),
	@NamedQuery(name = "Stundeteilnahme.findById", query = "SELECT s FROM Stundeteilnahme s WHERE s.id = :id"),
	@NamedQuery(name = "Stundeteilnahme.findByAnwesend", query = "SELECT s FROM Stundeteilnahme s WHERE s.anwesend = :anwesend"),
	@NamedQuery(name = "Stundeteilnahme.findByNote", query = "SELECT s FROM Stundeteilnahme s WHERE s.note = :note"),
	@NamedQuery(name = "Stundeteilnahme.findByKommentar", query = "SELECT s FROM Stundeteilnahme s WHERE s.kommentar = :kommentar")})
public class Stundeteilnahme implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
	private Integer id;
	@Basic(optional = false)
    @Column(name = "ANWESEND")
	private Boolean anwesend;
	@Column(name = "NOTE")
	private Integer note;
	@Column(name = "KOMMENTAR")
	private String kommentar;
	@JoinColumn(name = "ACCOUNTID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
	private Account accountid;
	@JoinColumn(name = "STUNDEID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
	private Stunde stundeid;

	public Stundeteilnahme() {
	}

	public Stundeteilnahme(Integer id) {
		this.id = id;
	}

	public Stundeteilnahme(Integer id, Boolean anwesend) {
		this.id = id;
		this.anwesend = anwesend;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getAnwesend() {
		return anwesend;
	}

	public void setAnwesend(Boolean anwesend) {
		this.anwesend = anwesend;
	}

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

	public String getKommentar() {
		return kommentar;
	}

	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	public Account getAccountid() {
		return accountid;
	}

	public void setAccountid(Account accountid) {
		this.accountid = accountid;
	}

	public Stunde getStundeid() {
		return stundeid;
	}

	public void setStundeid(Stunde stundeid) {
		this.stundeid = stundeid;
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
		if (!(object instanceof Stundeteilnahme)) {
			return false;
		}
		Stundeteilnahme other = (Stundeteilnahme) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ppj.backend.Entity.Stundeteilnahme[ id=" + id + " ]";
	}
	
}
