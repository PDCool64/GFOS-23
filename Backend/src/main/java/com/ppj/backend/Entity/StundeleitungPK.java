/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ppj.backend.Entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

/**
 *
 * @author philipp.doering
 */
@Embeddable
public class StundeleitungPK implements Serializable {

	@Basic(optional = false)
    @Column(name = "ACCOUNTID")
	private int accountid;
	@Basic(optional = false)
    @Column(name = "STUNDEID")
	private int stundeid;

	public StundeleitungPK() {
	}

	public StundeleitungPK(int accountid, int stundeid) {
		this.accountid = accountid;
		this.stundeid = stundeid;
	}

	public int getAccountid() {
		return accountid;
	}

	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}

	public int getStundeid() {
		return stundeid;
	}

	public void setStundeid(int stundeid) {
		this.stundeid = stundeid;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) accountid;
		hash += (int) stundeid;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof StundeleitungPK)) {
			return false;
		}
		StundeleitungPK other = (StundeleitungPK) object;
		if (this.accountid != other.accountid) {
			return false;
		}
		if (this.stundeid != other.stundeid) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.ppj.backend.Entity.StundeleitungPK[ accountid=" + accountid + ", stundeid=" + stundeid + " ]";
	}
	
}
