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
@Table(name = "VORSTUNDE")
@NamedQueries({
	@NamedQuery(name = "Vorstunde.findAll", query = "SELECT v FROM Vorstunde v"),
	@NamedQuery(name = "Vorstunde.findById", query = "SELECT v FROM Vorstunde v WHERE v.id = :id")})
public class Vorstunde implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
	private Integer id;
	@JoinColumn(name = "VORSTUNDE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
	private Stunde vorstunde;
	@JoinColumn(name = "STUNDE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
	private Stunde stunde;

	public Vorstunde() {
	}

	public Vorstunde(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Stunde getVorstunde() {
		return vorstunde;
	}

	public void setVorstunde(Stunde vorstunde) {
		this.vorstunde = vorstunde;
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
		if (!(object instanceof Vorstunde)) {
			return false;
		}
		Vorstunde other = (Vorstunde) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ppj.backend.Entity.Vorstunde[ id=" + id + " ]";
	}
	
}
