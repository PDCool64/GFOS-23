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
@Table(name = "STUNDELEITUNG")
@NamedQueries({
	@NamedQuery(name = "Stundeleitung.findAll", query = "SELECT s FROM Stundeleitung s"),
	@NamedQuery(name = "Stundeleitung.findById", query = "SELECT s FROM Stundeleitung s WHERE s.id = :id"),
	@NamedQuery(name = "Stundeleitung.findByLeitungsbewertung", query = "SELECT s FROM Stundeleitung s WHERE s.leitungsbewertung = :leitungsbewertung"),
	@NamedQuery(name = "Stundeleitung.findByKommentar", query = "SELECT s FROM Stundeleitung s WHERE s.kommentar = :kommentar")})
public class Stundeleitung implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
	private Integer id;
	@Basic(optional = false)
    @Column(name = "LEITUNGSBEWERTUNG")
	private int leitungsbewertung;
	@Column(name = "KOMMENTAR")
	private String kommentar;
	@JoinColumn(name = "ACCOUNTID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
	private Account accountid;
	@JoinColumn(name = "STUNDEID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
	private Stunde stundeid;

	public Stundeleitung() {
	}

	public Stundeleitung(Integer id) {
		this.id = id;
	}

	public Stundeleitung(Integer id, int leitungsbewertung) {
		this.id = id;
		this.leitungsbewertung = leitungsbewertung;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getLeitungsbewertung() {
		return leitungsbewertung;
	}

	public void setLeitungsbewertung(int leitungsbewertung) {
		this.leitungsbewertung = leitungsbewertung;
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
		if (!(object instanceof Stundeleitung)) {
			return false;
		}
		Stundeleitung other = (Stundeleitung) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ppj.backend.Entity.Stundeleitung[ id=" + id + " ]";
	}
	
}
