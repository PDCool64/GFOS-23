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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author philipp.doering
 */
@Entity
@Table(name = "STUNDE")
@NamedQueries(
	{
		@NamedQuery(name = "Stunde.findAll", query = "SELECT s FROM Stunde s"),
		@NamedQuery(
			name = "Stunde.findById",
			query = "SELECT s FROM Stunde s WHERE s.id = :id"
		),
		@NamedQuery(
			name = "Stunde.findByDatum",
			query = "SELECT s FROM Stunde s WHERE s.datum = :datum"
		),
		@NamedQuery(
			name = "Stunde.findByCheckincode",
			query = "SELECT s FROM Stunde s WHERE s.checkincode = :checkincode"
		),
	}
)
public class Stunde implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;

	@Basic(optional = false)
	@Column(name = "DATUM")
	@Temporal(TemporalType.DATE)
	private Date datum;

	@Basic(optional = false)
	@Column(name = "CHECKINCODE")
	@JsonbTransient
	private String checkincode;

	@JoinColumn(name = "UNTERRICHT", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Unterricht unterricht;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "stunde")
	@JsonbTransient
	private List<Stundeteilnahme> stundeteilnahmeList;


	public Stunde() {}

	public Stunde(Integer id) {
		this.id = id;
	}

	public Stunde(Integer id, Date datum, String checkincode) {
		this.id = id;
		this.datum = datum;
		this.checkincode = checkincode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getCheckincode() {
		return checkincode;
	}

	public void setCheckincode(String checkincode) {
		this.checkincode = checkincode;
	}

	public Unterricht getUnterricht() {
		return unterricht;
	}

	public void setUnterricht(Unterricht unterricht) {
		this.unterricht = unterricht;
	}

	public List<Stundeteilnahme> getStundeteilnahmeList() {
		return stundeteilnahmeList;
	}

	public void setStundeteilnahmeList(
		List<Stundeteilnahme> stundeteilnahmeList
	) {
		this.stundeteilnahmeList = stundeteilnahmeList;
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
		if (!(object instanceof Stunde)) {
			return false;
		}
		Stunde other = (Stunde) object;
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
		return "com.ppj.backend.Entity.Stunde[ id=" + id + " ]";
	}
}
