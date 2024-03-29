/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author philipp.doering
 */
@Stateless
@LocalBean
public class StundeFacade {

	@PersistenceContext
	private EntityManager em;

	@EJB
	private UnterrichtFacade unterrichtFacade;

	private String[] stundenplan = {
		"07:55:00",
		"08:45:00",
		"09:50:00",
		"10:35:00",
		"11:25:00",
		"12:25:00",
		"13:15:00",
		"14:15:00",
		"15:05:00",
		"15:55:00",
		"16:45:00",
		"17:30:00",
		"18:20:00",
	};

	private int currentStundenplanIndex = 0;

	private boolean isValidStundenplanIndex(int currentStundenplanIndex2) {
		Date current = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		try {
			Date stundenplanTime = sdf.parse(
				stundenplan[currentStundenplanIndex2]
			);
			long differenceInMinutes =
				(current.getTime() - stundenplanTime.getTime()) / 1000 / 60;
			if (differenceInMinutes > 0 && differenceInMinutes <= 45) {
				return true;
			}
			return false;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}

	private int updateStundenplanIndex() {
		if (isValidStundenplanIndex(currentStundenplanIndex)) {
			return currentStundenplanIndex;
		}
		Date current = new Date();
		currentStundenplanIndex = -1;
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		try {
			current = sdf.parse(sdf.format(current));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < stundenplan.length; i++) {
			try {
				Date stundenplanTime = sdf.parse(stundenplan[i]);
				long differenceInMinutes =
					(current.getTime() - stundenplanTime.getTime()) / 1000 / 60;
				if (differenceInMinutes > 0 && differenceInMinutes <= 45) {
					currentStundenplanIndex = i;
					break;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return currentStundenplanIndex;
	}

	public Stunde createStunde(Stunde stunde) {
		try {
			em.persist(stunde);
			em.flush();
			Stunde stundeMitId = this.getStundeById(stunde.getId());
			stundeMitId.getUnterricht().getStundeList().add(stundeMitId);
			createStundenteilnahmen(stundeMitId);
			return stundeMitId;
		} catch (Exception e) {
			return null;
		}
	}

	private void createStundenteilnahmen(Stunde stundeMitId) {
		for (Kursteilnahme kt : stundeMitId
			.getUnterricht()
			.getKurs()
			.getKursteilnahmeList()) { 
			Stundeteilnahme st = new Stundeteilnahme();
			em.persist(st);
			st.setStunde(stundeMitId);
			st.setAnwesend(false);
			st.setAccount(kt.getAccount());
			kt.getAccount().getStundeteilnahmeList().add(st);
			stundeMitId.getStundeteilnahmeList().add(st);
			em.flush();
		}
	}

	public Stunde getStundeById(int id) {
		try {
			return em.find(Stunde.class, id);
		} catch (Exception e) {
			return null;
		}
	}

	public Stunde updateStunde(Stunde stunde) {
		try {
			em.merge(stunde);
			em.flush();
			Stunde stundeMitId = this.getStundeById(stunde.getId());
			return stundeMitId;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean deleteStunde(int id) {
		try {
			Stunde stunde = this.getStundeById(id);
			em.remove(stunde);
			em.flush();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Stunde> getAllStunden() {
		try {
			return em
				.createNamedQuery("Stunde.findAll", Stunde.class)
				.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public List<Stunde> getStundenByUnterricht(int unterrichtId) {
		try {
			return em
				.createNamedQuery("Stunde.findByUnterricht", Stunde.class)
				.setParameter("unterrichtId", unterrichtId)
				.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public List<Stunde> getStundenByAccount(Account account) {
		try {
			List<Stunde> stunden = new ArrayList<Stunde>();
			for (Kursteilnahme kt : account.getKursteilnahmeList()) {
				for (Unterricht u : kt.getKurs().getUnterrichtList()) {
					stunden.addAll(u.getStundeList());
				}
			}
			return stunden;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Stundeteilnahme> getStundenByAccountAndDate(
		Account account,
		String startdate,
		String enddate
	) {
		try {
			List<Stundeteilnahme> stunden = new ArrayList<>();
			Date start = new SimpleDateFormat("yyyy-MM-dd").parse(startdate);
			Date end = new SimpleDateFormat("yyyy-MM-dd").parse(enddate);
			for (Kursteilnahme kt : account.getKursteilnahmeList()) {
				for (Unterricht u : kt.getKurs().getUnterrichtList()) {
					for (Stunde s : u.getStundeList()) {
						if (
							start.compareTo(s.getDatum()) <= 0 &&
							end.compareTo(s.getDatum()) >= 0
						) {
							for (Stundeteilnahme st : s.getStundeteilnahmeList()) if (
								st.getAccount().getId() == account.getId()
							) stunden.add(st);
						}
					}
				}
			}
			return stunden;
		} catch (Exception e) {
			return null;
		}
	}

	private String generateCheckincode() {
		String code = "";
		for (int j = 0; j < 5; j++) {
			code += (int) (Math.random() * 10);
		}
		return code;
	}

	public void createStunden(
		Unterricht unterrichtInDatenbank,
		String startDate,
		String endDate
	) {
		LocalDate start = LocalDate.parse(startDate);
		LocalDate end = LocalDate.parse(endDate);
		LocalDate current = start.with(
			TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY)
		);
		current = current.plusDays(unterrichtInDatenbank.getTag());

		while (!current.isAfter(end)) {
			Stunde stunde = new Stunde();
			stunde.setDatum(java.sql.Date.valueOf(current));
			stunde.setUnterricht(unterrichtInDatenbank);
			stunde.setCheckincode(generateCheckincode());
			this.createStunde(stunde);
			current = current.plusDays(7);
		}
		em.flush();
	}

	public List<Stundeteilnahme> getTeilnahmenByStundeId(int id) {
		try {
			Stunde s = getStundeById(id);
			return s.getStundeteilnahmeList();
		} catch (Exception e) {
			return null;
		}
	}

	private boolean isActive(Stunde s) {
		// truncate the time from the date
		// and check whether the date is today
		try {
			SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
			Date currentDateWithoutTime = ymd.parse(ymd.format(new Date()));
			Date sDateWithoutTime = ymd.parse(ymd.format(s.getDatum()));
			if (sDateWithoutTime.compareTo(currentDateWithoutTime) != 0) {
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		// update the index of the current stunde
		updateStundenplanIndex();
		if (currentStundenplanIndex == -1) {
			return false;
		}
		return (
			s.getUnterricht().getBeginstunde() <= currentStundenplanIndex &&
			s.getUnterricht().getEndstunde() >= currentStundenplanIndex
		);
	}

	public Stunde getAktuelleStunde(Account a) {
		try {
			for (Kursteilnahme kt : a.getKursteilnahmeList()) {
				for (Unterricht u : kt.getKurs().getUnterrichtList()) {
					for (Stunde s : u.getStundeList()) {
						if (isActive(s)) {
							return s;
						}
					}
				}
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	public void createStunden(Kurs k, Account a) {
		for (Unterricht u : k.getUnterrichtList()) {
			createStunden(u, a);
		}
	}

	public void createStunden(Unterricht u, Account a) {
		for (Stunde s : u.getStundeList()) {
			Stundeteilnahme st = new Stundeteilnahme();
			st.setAccount(a);
			st.setStunde(s);
			st.setAnwesend(false);
			em.persist(st);
			s.getStundeteilnahmeList().add(st);
			a.getStundeteilnahmeList().add(st);
		}
		em.flush();
	}

	public boolean checkin(Account account, Stunde s) {
		if (!isActive(s)) {
			return false;
		}
		for (Stundeteilnahme st : s.getStundeteilnahmeList()) {
			if (st.getAccount().getId() == account.getId()) {
				st.setAnwesend(true);
				Date current = new Date(new Date().getTime() + 1000 * 60 * 60);
				st.setBegintimestamp(current);
				em.merge(st);
				em.flush();
				return true;
			}
		}
		return false;
	}

	public List<Stundeteilnahme> updateTeilnahmen(
		List<Stundeteilnahme> stundenteilnahmenList
	) {
		for (Stundeteilnahme st : stundenteilnahmenList) {
			Stundeteilnahme stInDatenbank = em.find(
				Stundeteilnahme.class,
				st.getId()
			);
			stInDatenbank.setAnwesend(st.getAnwesend());
			stInDatenbank.setNote(st.getNote());
		}
		em.flush();
		return stundenteilnahmenList;
	}

	public void deleteStundenByUnterricht(Unterricht unterricht) {
		for (Stunde s : unterricht.getStundeList()) {
			em.remove(s);
		}
		unterricht.getStundeList().clear();
	}

	public Stundeteilnahme getStundenteilnahmeByAccountAndStunde(
		Account a,
		Stunde aktuelleStunde
	) {
		return em
			.createNamedQuery(
				"Stundeteilnahme.findByAccountAndStunde",
				Stundeteilnahme.class
			)
			.setParameter("account", a)
			.setParameter("stunde", aktuelleStunde)
			.getSingleResult();
	}

	public boolean checkout(Account account, Stunde s) {
		if (s == null) {
			return false;
		}
		if (!isActive(s)) {
			return false;
		}
		Stundeteilnahme st = getStundenteilnahmeByAccountAndStunde(account, s);
		if (st == null) {
			return false;
		}
		if (!st.getAnwesend()) return false;
		Date current = new Date(new Date().getTime() + 1000 * 60 * 60);
		st.setEndtimestamp(current);
		em.merge(st);
		return true;
	}
}
