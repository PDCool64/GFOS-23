/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ppj.backend.Entity;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author philipp.doering
 */
@Entity
@Table(name = "KURS")
@NamedQueries({
	@NamedQuery(name = "Kurs.findAll", query = "SELECT k FROM Kurs k"),
	@NamedQuery(name = "Kurs.findById", query = "SELECT k FROM Kurs k WHERE k.id = :id"),
	@NamedQuery(name = "Kurs.findByFach", query = "SELECT k FROM Kurs k WHERE k.fach = :fach"),
	@NamedQuery(name = "Kurs.findByStufe", query = "SELECT k FROM Kurs k WHERE k.stufe = :stufe"),
	@NamedQuery(name = "Kurs.findByNummer", query = "SELECT k FROM Kurs k WHERE k.nummer = :nummer"),
	@NamedQuery(name = "Kurs.findByArt", query = "SELECT k FROM Kurs k WHERE k.art = :art")})
public class Kurs implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
	private Integer id;
	@Basic(optional = false)
    @Column(name = "FACH")
	private String fach;
	@Column(name = "STUFE")
	private Integer stufe;
	@Column(name = "NUMMER")
	private Integer nummer;
	@Column(name = "ART")
	private String art;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kurs")
	@JsonbTransient
	private List<Unterricht> unterrichtList;
	@JoinColumn(name = "LEITER", referencedColumnName = "ID")
    @ManyToOne
	private Account leiter;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kurs")
	@JsonbTransient
	private List<Kursteilnahme> kursteilnahmeList;

	public Kurs() {
	}

	public Kurs(Integer id) {
		this.id = id;
	}

	public Kurs(Integer id, String fach) {
		this.id = id;
		this.fach = fach;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFach() {
		return fach;
	}

	public void setFach(String fach) {
		this.fach = fach;
	}

	public Integer getStufe() {
		return stufe;
	}

	public void setStufe(Integer stufe) {
		this.stufe = stufe;
	}

	public Integer getNummer() {
		return nummer;
	}

	public void setNummer(Integer nummer) {
		this.nummer = nummer;
	}

	public String getArt() {
		return art;
	}

	public void setArt(String art) {
		this.art = art;
	}

	public List<Unterricht> getUnterrichtList() {
		return unterrichtList;
	}

	public void setUnterrichtList(List<Unterricht> unterrichtList) {
		this.unterrichtList = unterrichtList;
	}

	public Account getLeiter() {
		return leiter;
	}

	public void setLeiter(Account leiter) {
		this.leiter = leiter;
	}

	public List<Kursteilnahme> getKursteilnahmeList() {
		return kursteilnahmeList;
	}

	public void setKursteilnahmeList(List<Kursteilnahme> kursteilnahmeList) {
		this.kursteilnahmeList = kursteilnahmeList;
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
		if (!(object instanceof Kurs)) {
			return false;
		}
		Kurs other = (Kurs) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ppj.backend.Entity.Kurs[ id=" + id + " ]";
	}
	
}
