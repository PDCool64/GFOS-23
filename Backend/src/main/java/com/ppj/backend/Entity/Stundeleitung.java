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
@Table(name = "STUNDELEITUNG")
@NamedQueries({
	@NamedQuery(name = "Stundeleitung.findAll", query = "SELECT s FROM Stundeleitung s"),
	@NamedQuery(name = "Stundeleitung.findByAccountid", query = "SELECT s FROM Stundeleitung s WHERE s.stundeleitungPK.accountid = :accountid"),
	@NamedQuery(name = "Stundeleitung.findByStundeid", query = "SELECT s FROM Stundeleitung s WHERE s.stundeleitungPK.stundeid = :stundeid"),
	@NamedQuery(name = "Stundeleitung.findByLeitungsbewertung", query = "SELECT s FROM Stundeleitung s WHERE s.leitungsbewertung = :leitungsbewertung")})
public class Stundeleitung implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected StundeleitungPK stundeleitungPK;
	@Basic(optional = false)
    @Column(name = "LEITUNGSBEWERTUNG")
	private int leitungsbewertung;
	@JoinColumn(name = "ACCOUNTID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
	private Account account;
	@JoinColumn(name = "STUNDEID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
	private Stunde stunde;

	public Stundeleitung() {
	}

	public Stundeleitung(StundeleitungPK stundeleitungPK) {
		this.stundeleitungPK = stundeleitungPK;
	}

	public Stundeleitung(StundeleitungPK stundeleitungPK, int leitungsbewertung) {
		this.stundeleitungPK = stundeleitungPK;
		this.leitungsbewertung = leitungsbewertung;
	}

	public Stundeleitung(int accountid, int stundeid) {
		this.stundeleitungPK = new StundeleitungPK(accountid, stundeid);
	}

	public StundeleitungPK getStundeleitungPK() {
		return stundeleitungPK;
	}

	public void setStundeleitungPK(StundeleitungPK stundeleitungPK) {
		this.stundeleitungPK = stundeleitungPK;
	}

	public int getLeitungsbewertung() {
		return leitungsbewertung;
	}

	public void setLeitungsbewertung(int leitungsbewertung) {
		this.leitungsbewertung = leitungsbewertung;
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
		hash += (stundeleitungPK != null ? stundeleitungPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Stundeleitung)) {
			return false;
		}
		Stundeleitung other = (Stundeleitung) object;
		if ((this.stundeleitungPK == null && other.stundeleitungPK != null) || (this.stundeleitungPK != null && !this.stundeleitungPK.equals(other.stundeleitungPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ppj.backend.Entity.Stundeleitung[ stundeleitungPK=" + stundeleitungPK + " ]";
	}
	
}
