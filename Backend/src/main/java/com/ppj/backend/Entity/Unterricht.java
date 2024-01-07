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
@Table(name = "UNTERRICHT")
@NamedQueries(
	{
		@NamedQuery(
			name = "Unterricht.findAll",
			query = "SELECT u FROM Unterricht u"
		),
		@NamedQuery(
			name = "Unterricht.findByBeginzeit",
			query = "SELECT u FROM Unterricht u WHERE u.beginzeit = :beginzeit"
		),
		@NamedQuery(
			name = "Unterricht.findByEndzeit",
			query = "SELECT u FROM Unterricht u WHERE u.endzeit = :endzeit"
		),
		@NamedQuery(
			name = "Unterricht.findByWochentag",
			query = "SELECT u FROM Unterricht u WHERE u.wochentag = :wochentag"
		),
		@NamedQuery(
			name = "Unterricht.findById",
			query = "SELECT u FROM Unterricht u WHERE u.id = :id"
		),
	}
)
public class Unterricht implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "BEGINZEIT")
	@Temporal(TemporalType.TIME)
	private Date beginzeit;

	@Column(name = "ENDZEIT")
	@Temporal(TemporalType.TIME)
	private Date endzeit;

	@Column(name = "WOCHENTAG")
	private String wochentag;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;

	@JoinColumn(name = "KURS", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Kurs kurs;

	public Unterricht() {}

	public Unterricht(Integer id) {
		this.id = id;
	}

	public Date getBeginzeit() {
		return beginzeit;
	}

	public void setBeginzeit(Date beginzeit) {
		this.beginzeit = beginzeit;
	}

	public Date getEndzeit() {
		return endzeit;
	}

	public void setEndzeit(Date endzeit) {
		this.endzeit = endzeit;
	}

	public String getWochentag() {
		return wochentag;
	}

	public void setWochentag(String wochentag) {
		this.wochentag = wochentag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		if (!(object instanceof Unterricht)) {
			return false;
		}
		Unterricht other = (Unterricht) object;
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
		return "com.ppj.backend.Entity.Unterricht[ id=" + id + " ]";
	}
}
