package com.ppj.backend.Facades;

import com.ppj.backend.Entity.Account;
import com.ppj.backend.Entity.Kurs;
import com.ppj.backend.Entity.Kursteilnahme;
import com.ppj.backend.Entity.Unterricht;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
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
			em.flush();
			Kurs kursMitId = this.getKursById(k.getId());
			
			return kursMitId;
		} catch (Exception e) {
			return null;
		}
	}

	public Kurs getKursById(int id) {
		try {
			return em.find(Kurs.class, id);
		} catch (Exception e) {
			return null;
		}
	}

	public Kurs getKursByCheckinCode(String checkincode) {
		try {
			return em
				.createNamedQuery("Kurs.findByCheckincode", Kurs.class)
				.setParameter("checkincode", checkincode)
				.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public List<Kurs> getAllKurse() {
		try {
			return em
				.createNamedQuery("Kurs.findAll", Kurs.class)
				.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public boolean updateKurs(Kurs k) {
		try {
			Kurs kursInDatenbank = this.getKursById(k.getId());

			kursInDatenbank.setFach(k.getFach());
			kursInDatenbank.setLeiter(k.getLeiter());
			kursInDatenbank.setArt(k.getArt());
			kursInDatenbank.setNummer(k.getNummer());
			kursInDatenbank.setStufe(k.getStufe());

			em.merge(kursInDatenbank);

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteKurs(Kurs k) {
		try {
			em.remove(k);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	public boolean addTeilnehmer(int kursId, Account a) {
		try {
			Kurs k = em
				.createNamedQuery("Kurs.findById", Kurs.class)
				.setParameter("id", kursId)
				.getSingleResult();
			k.getKursteilnahmeList().add(new Kursteilnahme(a, k));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean removeTeilnehmer(int kursId, Account a) {
		try {
			Kurs k = em
				.createNamedQuery("Kurs.findById", Kurs.class)
				.setParameter("id", kursId)
				.getSingleResult();
			k.getKursteilnahmeList().remove(new Kursteilnahme(a, k));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean setTeilnehmerListe(List<Kursteilnahme> teilnehmerListe, int kursId) {
		try {
			Kurs k = em
				.createNamedQuery("Kurs.findById", Kurs.class)
				.setParameter("id", kursId)
				.getSingleResult();
			k.setKursteilnahmeList(teilnehmerListe);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Unterricht createUnterricht(Unterricht u) {
		try {
			em.persist(u);
			em.flush();
			Unterricht unterrichtMitId = getUnterrichtById(u.getId());
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
			return em
				.createNamedQuery("Unterricht.findAll", Unterricht.class)
				.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public boolean updateUnterricht(Unterricht u) {
		try {
			Unterricht unterrichtInDatenbank = getUnterrichtById(u.getId());

			unterrichtInDatenbank.setBeginstunde(u.getBeginstunde());
			unterrichtInDatenbank.setEndstunde(u.getEndstunde());
			unterrichtInDatenbank.setKurs(u.getKurs());
			unterrichtInDatenbank.setStundeList(u.getStundeList());

			em.merge(unterrichtInDatenbank);

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteUnterricht(Unterricht u) {
		try {
			em.remove(u);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
