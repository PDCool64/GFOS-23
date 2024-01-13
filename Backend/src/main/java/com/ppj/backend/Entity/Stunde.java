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
@NamedQueries({
	@NamedQuery(name = "Stunde.findAll", query = "SELECT s FROM Stunde s"),
	@NamedQuery(name = "Stunde.findById", query = "SELECT s FROM Stunde s WHERE s.id = :id"),
	@NamedQuery(name = "Stunde.findByBegintimestamp", query = "SELECT s FROM Stunde s WHERE s.begintimestamp = :begintimestamp"),
	@NamedQuery(name = "Stunde.findByEndtimestamp", query = "SELECT s FROM Stunde s WHERE s.endtimestamp = :endtimestamp"),
	@NamedQuery(name = "Stunde.findByCheckincode", query = "SELECT s FROM Stunde s WHERE s.checkincode = :checkincode")})
public class Stunde implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
	private Integer id;
	@Basic(optional = false)
    @Column(name = "BEGINTIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
	private Date begintimestamp;
	@Basic(optional = false)
    @Column(name = "ENDTIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
	private Date endtimestamp;
	@Basic(optional = false)
    @Column(name = "CHECKINCODE")
	private String checkincode;
	@JoinColumn(name = "UNTERRICHTID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
	private Unterricht unterrichtid;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "stunde")
	private List<Stundeteilnahme> stundeteilnahmeList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "stunde")
	private List<Stundeleitung> stundeleitungList;

	public Stunde() {
	}

	public Stunde(Integer id) {
		this.id = id;
	}

	public Stunde(Integer id, Date begintimestamp, Date endtimestamp, String checkincode) {
		this.id = id;
		this.begintimestamp = begintimestamp;
		this.endtimestamp = endtimestamp;
		this.checkincode = checkincode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getCheckincode() {
		return checkincode;
	}

	public void setCheckincode(String checkincode) {
		this.checkincode = checkincode;
	}

	public Unterricht getUnterrichtid() {
		return unterrichtid;
	}

	public void setUnterrichtid(Unterricht unterrichtid) {
		this.unterrichtid = unterrichtid;
	}

	public List<Stundeteilnahme> getStundeteilnahmeList() {
		return stundeteilnahmeList;
	}

	public void setStundeteilnahmeList(List<Stundeteilnahme> stundeteilnahmeList) {
		this.stundeteilnahmeList = stundeteilnahmeList;
	}

	public List<Stundeleitung> getStundeleitungList() {
		return stundeleitungList;
	}

	public void setStundeleitungList(List<Stundeleitung> stundeleitungList) {
		this.stundeleitungList = stundeleitungList;
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
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ppj.backend.Entity.Stunde[ id=" + id + " ]";
	}
	
}
