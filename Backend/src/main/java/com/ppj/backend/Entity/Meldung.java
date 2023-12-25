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
 * @author phili
 */
@Entity
@Table(name = "MELDUNG")
@NamedQueries({
	@NamedQuery(name = "Meldung.findAll", query = "SELECT m FROM Meldung m"),
	@NamedQuery(name = "Meldung.findByBegintimestamp", query = "SELECT m FROM Meldung m WHERE m.begintimestamp = :begintimestamp"),
	@NamedQuery(name = "Meldung.findByEndtimestamp", query = "SELECT m FROM Meldung m WHERE m.endtimestamp = :endtimestamp"),
	@NamedQuery(name = "Meldung.findByGrund", query = "SELECT m FROM Meldung m WHERE m.grund = :grund"),
	@NamedQuery(name = "Meldung.findByKommentar", query = "SELECT m FROM Meldung m WHERE m.kommentar = :kommentar"),
	@NamedQuery(name = "Meldung.findById", query = "SELECT m FROM Meldung m WHERE m.id = :id")})
public class Meldung implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "BEGINTIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
	private Date begintimestamp;
	@Column(name = "ENDTIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
	private Date endtimestamp;
	@Column(name = "GRUND")
	private String grund;
	@Column(name = "KOMMENTAR")
	private String kommentar;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
	private Integer id;
	@JoinColumn(name = "ACCOUNT", referencedColumnName = "ID")
    @ManyToOne(optional = false)
	private Account account;

	public Meldung() {
	}

	public Meldung(Integer id) {
		this.id = id;
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

	public String getGrund() {
		return grund;
	}

	public void setGrund(String grund) {
		this.grund = grund;
	}

	public String getKommentar() {
		return kommentar;
	}

	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
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

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Meldung)) {
			return false;
		}
		Meldung other = (Meldung) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ppj.backend.Entities.Meldung[ id=" + id + " ]";
	}
	
}
