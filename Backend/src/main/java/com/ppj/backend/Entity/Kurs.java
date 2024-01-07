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
import jakarta.persistence.ManyToMany;
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
@NamedQueries(
	{
		@NamedQuery(name = "Kurs.findAll", query = "SELECT k FROM Kurs k"),
		@NamedQuery(
			name = "Kurs.findByBezeichnung",
			query = "SELECT k FROM Kurs k WHERE k.bezeichnung = :bezeichnung"
		),
		@NamedQuery(
			name = "Kurs.findByCheckincode",
			query = "SELECT k FROM Kurs k WHERE k.checkincode = :checkincode"
		),
		@NamedQuery(
			name = "Kurs.findById",
			query = "SELECT k FROM Kurs k WHERE k.id = :id"
		),
	}
)
public class Kurs implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "BEZEICHNUNG")
	private String bezeichnung;

	@Column(name = "CHECKINCODE")
	private String checkincode;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;

	@ManyToMany(mappedBy = "kursList")
	private List<Account> accountList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kurs")
	private List<Unterricht> unterrichtList;

	@JoinColumn(name = "LEITER", referencedColumnName = "ID")
	@ManyToOne(optional = false)
	private Account leiter;

	public Kurs() {}

	public Kurs(Integer id) {
		this.id = id;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public String getCheckincode() {
		return checkincode;
	}

	public void setCheckincode(String checkincode) {
		this.checkincode = checkincode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
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
		return "com.ppj.backend.Entity.Kurs[ id=" + id + " ]";
	}
}
