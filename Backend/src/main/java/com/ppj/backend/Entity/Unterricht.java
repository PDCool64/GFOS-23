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
@Table(name = "UNTERRICHT")
@NamedQueries({
	@NamedQuery(name = "Unterricht.findAll", query = "SELECT u FROM Unterricht u"),
	@NamedQuery(name = "Unterricht.findById", query = "SELECT u FROM Unterricht u WHERE u.id = :id"),
	@NamedQuery(name = "Unterricht.findByBeginzeit", query = "SELECT u FROM Unterricht u WHERE u.beginzeit = :beginzeit"),
	@NamedQuery(name = "Unterricht.findByEndzeit", query = "SELECT u FROM Unterricht u WHERE u.endzeit = :endzeit")})
public class Unterricht implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
	private Integer id;
	@Basic(optional = false)
    @Column(name = "BEGINZEIT")
	private String beginzeit;
	@Basic(optional = false)
    @Column(name = "ENDZEIT")
	private String endzeit;
	@JoinColumn(name = "KURSID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
	private Kurs kursid;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "unterrichtid")
	private List<Stunde> stundeList;

	public Unterricht() {
	}

	public Unterricht(Integer id) {
		this.id = id;
	}

	public Unterricht(Integer id, String beginzeit, String endzeit) {
		this.id = id;
		this.beginzeit = beginzeit;
		this.endzeit = endzeit;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBeginzeit() {
		return beginzeit;
	}

	public void setBeginzeit(String beginzeit) {
		this.beginzeit = beginzeit;
	}

	public String getEndzeit() {
		return endzeit;
	}

	public void setEndzeit(String endzeit) {
		this.endzeit = endzeit;
	}

	public Kurs getKursid() {
		return kursid;
	}

	public void setKursid(Kurs kursid) {
		this.kursid = kursid;
	}

	public List<Stunde> getStundeList() {
		return stundeList;
	}

	public void setStundeList(List<Stunde> stundeList) {
		this.stundeList = stundeList;
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
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ppj.backend.Entity.Unterricht[ id=" + id + " ]";
	}
	
}
