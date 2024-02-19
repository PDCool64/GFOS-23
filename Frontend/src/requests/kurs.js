import { useUserStore } from "@/stores/user";

const userData = useUserStore();

export async function getLeiterKurse() {
	const response = await fetch("http://localhost:8080/Backend/kurs/leiter/", {
		method: "GET",
		headers: {
			Authorization: userData.token,
		},
	});
	let data;
	if (response.ok) {
		data = await response.json();
	} else {
		console.log("Error fetching data");
	}
	return data !== undefined ? data : [];
}

export const createKurs = (kurs) => {
	const response = fetch("http://localhost:8080/Backend/kurs", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
			Authorization: userData.token,
		},
		body: JSON.stringify(kurs),
	});
};

export const getTeilnahmen = async (kursId) => {
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
		return;
	}
	let data = await response.json();
	return data;
};
export async function createTeilnehmer(kursId, email) {
    let response;
	try {
		response = await fetch(
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
	} catch (e) {
		console.log(e);
        return false;
	}
	if (!response.ok) {
		console.log("Error");
        return false;
	}
}

export async function deleteKurs(kursId) {
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
}