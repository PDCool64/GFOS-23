/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ppj.backend.Entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
	@NamedQuery(name = "Stundeteilnahme.findByAccountid", query = "SELECT s FROM Stundeteilnahme s WHERE s.stundeteilnahmePK.accountid = :accountid"),
	@NamedQuery(name = "Stundeteilnahme.findByStundeid", query = "SELECT s FROM Stundeteilnahme s WHERE s.stundeteilnahmePK.stundeid = :stundeid"),
	@NamedQuery(name = "Stundeteilnahme.findByAnwesend", query = "SELECT s FROM Stundeteilnahme s WHERE s.anwesend = :anwesend"),
	@NamedQuery(name = "Stundeteilnahme.findByNote", query = "SELECT s FROM Stundeteilnahme s WHERE s.note = :note")})
public class Stundeteilnahme implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected StundeteilnahmePK stundeteilnahmePK;
	@Basic(optional = false)
    @Column(name = "ANWESEND")
	private Boolean anwesend;
	@Column(name = "NOTE")
	private Integer note;
	@JoinColumn(name = "ACCOUNTID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
	private Account account;
	@JoinColumn(name = "STUNDEID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
	private Stunde stunde;

	public Stundeteilnahme() {
	}

	public Stundeteilnahme(StundeteilnahmePK stundeteilnahmePK) {
		this.stundeteilnahmePK = stundeteilnahmePK;
	}

	public Stundeteilnahme(StundeteilnahmePK stundeteilnahmePK, Boolean anwesend) {
		this.stundeteilnahmePK = stundeteilnahmePK;
		this.anwesend = anwesend;
	}

	public Stundeteilnahme(int accountid, int stundeid) {
		this.stundeteilnahmePK = new StundeteilnahmePK(accountid, stundeid);
	}

	public StundeteilnahmePK getStundeteilnahmePK() {
		return stundeteilnahmePK;
	}

	public void setStundeteilnahmePK(StundeteilnahmePK stundeteilnahmePK) {
		this.stundeteilnahmePK = stundeteilnahmePK;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Stunde getStunde() {
		return stunde;
	}

	public void setStunde(Stunde stunde) {
		this.stunde = stunde;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (stundeteilnahmePK != null ? stundeteilnahmePK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Stundeteilnahme)) {
			return false;
		}
		Stundeteilnahme other = (Stundeteilnahme) object;
		if ((this.stundeteilnahmePK == null && other.stundeteilnahmePK != null) || (this.stundeteilnahmePK != null && !this.stundeteilnahmePK.equals(other.stundeteilnahmePK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ppj.backend.Entity.Stundeteilnahme[ stundeteilnahmePK=" + stundeteilnahmePK + " ]";
	}
	
}
