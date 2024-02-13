/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.ppj.backend.Facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

import com.ppj.backend.Entity.Account;
import com.ppj.backend.Entity.Kurs;
import com.ppj.backend.Entity.Kursteilnahme;
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
    @PersistenceContext
    EntityManager em;

    @EJB
    private AccountFacade accountFacade;
    @EJB
    private KursFacade kursFacade;

    public List<Unterricht> getUnterrichtByAccount(Account a) {
        try{
            List<Unterricht> unterrichtList = new ArrayList<Unterricht>();   
            for (Kursteilnahme kt : a.getKursteilnahmeList()) {
                unterrichtList.addAll(kt.getKurs().getUnterrichtList());
            }
            return unterrichtList;
        } catch (Exception e) {
            return null;
        }
    }

    public Unterricht createUnterricht(Unterricht unterricht) {
        try {
            em.persist(unterricht);
            em.flush();
            Unterricht unterrichtMitId = this.getUnterrichtById(unterricht.getId());
            unterricht.getKurs().getUnterrichtList().add(unterricht);
            return unterrichtMitId;
        } catch (Exception e) {
            return null;
        }
    }

    public Unterricht getUnterrichtById(int id) {
        try {
            return em.find(Unterricht.class, id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Unterricht> getAllUnterricht() {
        try {
            return em.createNamedQuery("Unterricht.findAll", Unterricht.class).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Unterricht> getUnterrichtByKurs(Kurs kurs) {
        try {
            return kurs.getUnterrichtList();
        } catch (Exception e) {
            return null;
        }
    }
}
