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
@Table(name = "STUNDEBEWERTUNG")
@NamedQueries({
	@NamedQuery(name = "Stundebewertung.findAll", query = "SELECT s FROM Stundebewertung s"),
	@NamedQuery(name = "Stundebewertung.findById", query = "SELECT s FROM Stundebewertung s WHERE s.id = :id"),
	@NamedQuery(name = "Stundebewertung.findByNote", query = "SELECT s FROM Stundebewertung s WHERE s.note = :note"),
	@NamedQuery(name = "Stundebewertung.findByKommentar", query = "SELECT s FROM Stundebewertung s WHERE s.kommentar = :kommentar")})
public class Stundebewertung implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
	private Integer id;
	@Column(name = "NOTE")
	private Integer note;
	@Column(name = "KOMMENTAR")
	private String kommentar;
	@JoinColumn(name = "ACCOUNT", referencedColumnName = "ID")
    @ManyToOne(optional = false)
	private Account account;
	@JoinColumn(name = "STUNDE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
	private Stunde stunde;

	public Stundebewertung() {
	}

	public Stundebewertung(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Stundebewertung)) {
			return false;
		}
		Stundebewertung other = (Stundebewertung) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ppj.backend.Entity.Stundebewertung[ id=" + id + " ]";
	}
	
}
