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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

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
	@NamedQuery(name = "Stundeteilnahme.findByBegintimestamp", query = "SELECT s FROM Stundeteilnahme s WHERE s.begintimestamp = :begintimestamp"),
	@NamedQuery(name = "Stundeteilnahme.findByEndtimestamp", query = "SELECT s FROM Stundeteilnahme s WHERE s.endtimestamp = :endtimestamp"),
	@NamedQuery(name = "Stundeteilnahme.findByNote", query = "SELECT s FROM Stundeteilnahme s WHERE s.note = :note")})
public class Stundeteilnahme implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
	private Integer id;
	@Column(name = "ANWESEND")
	private Boolean anwesend;
	@Basic(optional = false)
    @Column(name = "BEGINTIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
	private Date begintimestamp;
	@Column(name = "ENDTIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
	private Date endtimestamp;
	@Column(name = "NOTE")
	private Integer note;
	@JoinColumn(name = "ACCOUNT", referencedColumnName = "ID")
    @ManyToOne(optional = false)
	private Account account;
	@JoinColumn(name = "STUNDE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
	private Stunde stunde;

	public Stundeteilnahme() {
	}

	public Stundeteilnahme(Integer id) {
		this.id = id;
	}

	public Stundeteilnahme(Integer id, Date begintimestamp) {
		this.id = id;
		this.begintimestamp = begintimestamp;
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

	public Date getBegintimestamp() {
		return begintimestamp;
	}

	public void setBegintimestamp(Date begintimestamp) {
		this.begintimestamp = begintimestamp;
	}

	public Date getEndtimestamp() {
		return endtimestamp;
	}

	public void setEndtimestamp(Date endtimestamp) {
		this.endtimestamp = endtimestamp;
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
