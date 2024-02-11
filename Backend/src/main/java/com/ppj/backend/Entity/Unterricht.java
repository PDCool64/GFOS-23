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
@Table(name = "UNTERRICHT")
@NamedQueries({
	@NamedQuery(name = "Unterricht.findAll", query = "SELECT u FROM Unterricht u"),
	@NamedQuery(name = "Unterricht.findById", query = "SELECT u FROM Unterricht u WHERE u.id = :id"),
	@NamedQuery(name = "Unterricht.findByBeginstunde", query = "SELECT u FROM Unterricht u WHERE u.beginstunde = :beginstunde"),
	@NamedQuery(name = "Unterricht.findByEndstunde", query = "SELECT u FROM Unterricht u WHERE u.endstunde = :endstunde"),
	@NamedQuery(name = "Unterricht.findByTag", query = "SELECT u FROM Unterricht u WHERE u.tag = :tag")})
public class Unterricht implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
	private Integer id;
	@Basic(optional = false)
    @Column(name = "BEGINSTUNDE")
	private int beginstunde;
	@Basic(optional = false)
    @Column(name = "ENDSTUNDE")
	private int endstunde;
	@Basic(optional = false)
    @Column(name = "TAG")
	private int tag;
	@JoinColumn(name = "KURS", referencedColumnName = "ID")
    @ManyToOne(optional = false)
	private Kurs kurs;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "unterricht")
	@JsonbTransient
	private List<Stunde> stundeList;

	public Unterricht() {
	}

	public Unterricht(Integer id) {
		this.id = id;
	}

	public Unterricht(Integer id, int beginstunde, int endstunde, int tag) {
		this.id = id;
		this.beginstunde = beginstunde;
		this.endstunde = endstunde;
		this.tag = tag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getBeginstunde() {
		return beginstunde;
	}

	public void setBeginstunde(int beginstunde) {
		this.beginstunde = beginstunde;
	}

	public int getEndstunde() {
		return endstunde;
	}

	public void setEndstunde(int endstunde) {
		this.endstunde = endstunde;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public Kurs getKurs() {
		return kurs;
	}

	public void setKurs(Kurs kurs) {
		this.kurs = kurs;
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
