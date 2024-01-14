/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ppj.backend.Entity;

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
	@NamedQuery(name = "Kurs.findByName", query = "SELECT k FROM Kurs k WHERE k.name = :name"),
	@NamedQuery(name = "Kurs.findByStufe", query = "SELECT k FROM Kurs k WHERE k.stufe = :stufe")})
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
	@Basic(optional = false)
    @Column(name = "NAME")
	private String name;
	@Column(name = "STUFE")
	private Integer stufe;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kursid")
	private List<Unterricht> unterrichtList;
	@JoinColumn(name = "LEITERID", referencedColumnName = "ID")
    @ManyToOne
	private Account leiterid;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kursid")
	private List<Kursteilnahme> kursteilnahmeList;

	public Kurs() {
	}

	public Kurs(Integer id) {
		this.id = id;
	}

	public Kurs(Integer id, String fach, String name) {
		this.id = id;
		this.fach = fach;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStufe() {
		return stufe;
	}

	public void setStufe(Integer stufe) {
		this.stufe = stufe;
	}

	public List<Unterricht> getUnterrichtList() {
		return unterrichtList;
	}

	public void setUnterrichtList(List<Unterricht> unterrichtList) {
		this.unterrichtList = unterrichtList;
	}

	public Account getLeiterid() {
		return leiterid;
	}

	public void setLeiterid(Account leiterid) {
		this.leiterid = leiterid;
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
