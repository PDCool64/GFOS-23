package com.ppj.backend.Facades;

import com.ppj.backend.Entity.Account;
import com.ppj.backend.Entity.Kurs;
import com.ppj.backend.Entity.Kursteilnahme;
import com.ppj.backend.Entity.Stunde;
import com.ppj.backend.Entity.Stundeteilnahme;
import com.ppj.backend.Entity.Unterricht;
import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Stateless
@LocalBean
public class KursFacade {

	@PersistenceContext
	private EntityManager em;

	@EJB
	private AccountFacade accountFacade;

	@EJB
	private StundeFacade stundenFacade;

	@EJB
	private UnterrichtFacade unterrichtFacade;

	public Kurs createKurs(Kurs k) {
		try {
			em.persist(k);
			em.flush();
			Kurs kursMitId = this.getKursById(k.getId());
			k.getLeiter().getKursList().add(kursMitId);
			addTeilnehmer(k.getId(), k.getLeiter());
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
			for (Kursteilnahme kt : k.getKursteilnahmeList()) {
				kt.getAccount().getKursteilnahmeList().remove(kt);
				em.remove(kt);
			}
			for (Unterricht u : k.getUnterrichtList()) {
				unterrichtFacade.deleteUnterricht(u);
			}
			k.getLeiter().getKursList().remove(k);
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
			Kursteilnahme kt = new Kursteilnahme(a, k);
			k.getKursteilnahmeList().add(kt);
			a.getKursteilnahmeList().add(kt);
			stundenFacade.createStunden(k, a);
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

	public boolean setTeilnehmerListe(
		List<Kursteilnahme> teilnehmerListe,
		int kursId
	) {
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

	public List<Kurs> getKurseByAccountId(int accountId) {
		List<Kurs> kurse = new LinkedList<Kurs>();
		List<Kursteilnahme> kursteilnahmen = accountFacade
			.getAccountById(accountId)
			.getKursteilnahmeList();
		for (Kursteilnahme k : kursteilnahmen) {
			kurse.add(k.getKurs());
		}
		return kurse;
	}

	public List<Kurs> getKurseByLeiter(Account accountById) {
		Account a = accountFacade.getAccountById(accountById.getId());
		return a.getKursList();
	}

	public List<Kursteilnahme> getTeilnahmenByKurs(int kursId) {
		try {
			Kurs k = getKursById(kursId);
			return k.getKursteilnahmeList();
		} catch (Exception e) {
			return null;
		}
	}

	public void deleteTeilnehmer(Kurs k, int teilnehmerId) {
		Account teilnehmer = accountFacade.getAccountById(teilnehmerId);
		for (Unterricht u : k.getUnterrichtList()) {
			for (Stunde s : u.getStundeList()) {
				for (Stundeteilnahme st : List.copyOf(s.getStundeteilnahmeList())) {
					if (st.getAccount().getId() == teilnehmer.getId()) {
						em.remove(st);
						teilnehmer.getStundeteilnahmeList().remove(st);
						s.getStundeteilnahmeList().remove(st);
					}
				}
			}
		}
		for (Kursteilnahme kt : List.copyOf(k.getKursteilnahmeList())) {
			if (kt.getAccount().getId() == teilnehmer.getId()) {
				k.getKursteilnahmeList().remove(kt);
				teilnehmer.getKursteilnahmeList().remove(kt);

				em.remove(kt);
			}
		}
		em.flush();
	}

    public HashMap<String, List<Stundeteilnahme>> getStats(Kurs k) {
		HashMap<String, List<Stundeteilnahme>> stats = new HashMap<String, List<Stundeteilnahme>>();
		for (Unterricht u : k.getUnterrichtList()) {
			for (Stunde s : u.getStundeList()) {
				for (Stundeteilnahme st : s.getStundeteilnahmeList()) {
					if (stats.containsKey(st.getAccount().getEmail())) {
						stats.get(st.getAccount().getEmail()).add(st);
					} else {
						List<Stundeteilnahme> stList = new LinkedList<Stundeteilnahme>();
						stList.add(st);
						stats.put(st.getAccount().getEmail(), stList);
					}
				}
			}
		}
		return stats;
    }
}
