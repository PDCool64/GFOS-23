import { useUserStore } from "@/stores/user";

const userData = useUserStore();

export async function getLeiterKurse() {
	try {
		const response = await fetch(
			"http://localhost:8080/Backend/kurs/leiter/",
			{
				method: "GET",
				headers: {
					Authorization: userData.token,
				},
			},
		);
		let data;
		if (response.ok) {
			data = await response.json();
		} else {
			console.log("Error fetching data");
		}
		return data !== undefined ? data : [];
	} catch (e) {
		console.log(e);
		return [];
	}
}

export const createKurs = async (kurs) => {
	try {
		const response = await fetch("http://localhost:8080/Backend/kurs", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
				Authorization: userData.token,
			},
			body: JSON.stringify(kurs),
		});
		return response.ok;
	} catch (e) {
		console.log(e);
		return false;
	}
};

export const getTeilnahmen = async (kursId) => {
	try {
		const response = await fetch(
			"http://localhost:8080/Backend/kurs/teilnahmen/" + kursId,
			{
				method: "GET",
				headers: {
					"Content-Type": "application/json",
					Authorization: userData.token,
				},
			},
		);
		if (!response.ok) {
			console.log("Error");
			return null;
		}
		let data = await response.json();
		return data;
	} catch (e) {
		console.log(e);
		return null;
	}
};

export async function createTeilnehmer(kursId, email) {
	try {
		const response = await fetch(
			"http://localhost:8080/Backend/kurs/teilnehmer/" +
				kursId +
				"/" +
				email,
			{
				method: "POST",
				headers: {
					"Content-Type": "application/json",
					Authorization: userData.token,
				},
			},
		);
		if (!response.ok) {
			console.log("Error");
			return false;
		}
		return true;
	} catch (e) {
		console.log(e);
		return false;
	}
}

export async function deleteKurs(kursId) {
	try {
		const response = await fetch(
			"http://localhost:8080/Backend/kurs/" + kursId,
			{
				method: "DELETE",
				headers: {
					Authorization: userData.token,
				},
			},
		);
		return response.ok;
	} catch (e) {
		console.log(e);
		return false;
	}
}

export async function deleteTeilnehmer(kursId, accountId) {
	try {
		const response = await fetch(
			"http://localhost:8080/Backend/kurs/teilnehmer/" +
				kursId +
				"/" +
				accountId,
			{
				method: "DELETE",
				headers: {
					Authorization: userData.token,
				},
			},
		);
		console.log(response);
		return response.ok;
	} catch (e) {
		console.log(e);
		return false;
	}
}
