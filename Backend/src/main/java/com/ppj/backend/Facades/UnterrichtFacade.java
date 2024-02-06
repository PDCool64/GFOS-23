/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.ppj.backend.Facades;

import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;

import com.ppj.backend.Entity.Account;
import com.ppj.backend.Entity.Kurs;
import com.ppj.backend.Entity.Unterricht;

import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;

/**
 *
 * @author philipp.doering
 */
@Stateless
@LocalBean
public class UnterrichtFacade {
    @EJB
    private AccountFacade accountFacade;
    public List<Unterricht> getUnterrichtByAccount(Account a) {
        try{   
            List<Unterricht> unterrichtList = new ArrayList<Unterricht>();
            for (Kurs k : a.getKursList()) {
                unterrichtList.addAll(k.getUnterrichtList());
            }
            return unterrichtList;
        } catch (Exception e) {
            return null;
        }
    }
}
