package com.ppj.backend.Facades;

import com.ppj.backend.Entity.Account;
import com.ppj.backend.Entity.Kurs;
import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
@LocalBean
public class KursFacade {
    @PersistenceContext
    private EntityManager em;

    public Kurs createKurs(Kurs k) {
        try {
            em.persist(k);
            Kurs kursMitId = this.getKursById(k.getId());
            return kursMitId;
        } catch(Exception e) {
            return null;
        }
    }

    public Kurs getKursById(int id) {
        try {
            return em.find(Kurs.class, id);
        } catch(Exception e) {
            return null;
        }
    }

    public Kurs getKursByCheckinCode(String checkincode) {
        try {
            return em.createNamedQuery("Kurs.findByCheckincode", Kurs.class)
				.setParameter("checkincode", checkincode)
				.getSingleResult();
        } catch(Exception e) {
            return null;
        }
    }


    public List<Kurs> getAllKurse() {
        try {
            return em.createNamedQuery("Kurs.findAll", Kurs.class).getResultList();
        } catch(Exception e) {
            return null;
        }
    }

    public boolean updateKurs(Kurs k) {
        try {
            Kurs kursInDatenbank = this.getKursById(k.getId());

            // Update all the values
            kursInDatenbank.setBezeichnung(k.getBezeichnung());
            kursInDatenbank.setCheckincode(k.getCheckincode());
            kursInDatenbank.setAccount(k.getAccount());
            // Add more setters if there are more fields to update

            // Merge the updated kursInDatenbank with the EntityManager
            em.merge(kursInDatenbank);

            return true;
        } catch(Exception e) {
            return false;
        }
}

    public boolean deleteKurs(Kurs k) {
        try {
            em.remove(k);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public boolean setLeitung(int kursId, Account a){
        try {
            em.createNamedQuery("Kurs.findById", Kurs.class)
            .setParameter("id", kursId)
            .getSingleResult()
            .setAccount(a);
            return true;
        } catch(Exception e) {
            return false;
        }
    } 

    public boolean addTeilnehmer(int kursId, Account a){
        try {
            em.createNamedQuery("Kurs.findById", Kurs.class)
            .setParameter("id", kursId)
            .getSingleResult()
            .getAccountList()
            .add(a);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}