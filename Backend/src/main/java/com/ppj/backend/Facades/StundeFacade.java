/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package com.ppj.backend.Facades;

import com.ppj.backend.Entity.Account;
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
		current.setTime(0);
		current.setHours(7);
		current.setMinutes(57);
		current.setSeconds(0);
		currentStundenplanIndex = -1;
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		for (int i = 0; i < stundenplan.length; i++) {
			try {
				Date stundenplanTime = sdf.parse(stundenplan[i]);
				long differenceInMinutes =
					(current.getTime() - stundenplanTime.getTime()) / 1000 / 60;
				System.out.println(
					"Difference in minutes: " + differenceInMinutes
				);
				if (differenceInMinutes > 0 && differenceInMinutes <= 45) {
					currentStundenplanIndex = i;
					break;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		System.out.println(currentStundenplanIndex);
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
		for (Kursteilnahme kt : stundeMitId.getUnterricht().getKurs().getKursteilnahmeList()) { // for(int i = 0;...) kt = ...[i]
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

	public List<Stunde> getStundenByAccountAndDate(
		Account account,
		String startdate,
		String enddate
	) {
		try {
			List<Stunde> stunden = new ArrayList<Stunde>();
			Date start = new SimpleDateFormat("yyyy-MM-dd").parse(startdate);
			Date end = new SimpleDateFormat("yyyy-MM-dd").parse(enddate);
			for (Kursteilnahme kt : account.getKursteilnahmeList()) {
				for (Unterricht u : kt.getKurs().getUnterrichtList()) {
					System.out.println(u.getId());
					for (Stunde s : u.getStundeList()) {
						System.out.println(s);
						if (
							start.compareTo(s.getDatum()) <= 0 &&
							end.compareTo(s.getDatum()) >= 0
						) {
							stunden.add(s);
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
}
