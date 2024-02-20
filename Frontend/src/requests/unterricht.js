import { useUserStore } from "@/stores/user";

const userData = useUserStore();

export async function createUnterricht(unterricht) {
	try {
		const response = await fetch("http://localhost:8080/Backend/unterricht", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
				Authorization: userData.token,
			},
			body: JSON.stringify(unterricht),
		});
		return response.ok;
	} catch (e) {
		console.log(e);
		return false;
	}
}

export async function getAllUnterrichtByKurs(kurs) {
	try {
		console.log(kurs);
		const response = await fetch(
			"http://localhost:8080/Backend/unterricht/kurs/" + kurs,
			{
				method: "GET",
				headers: {
					Authorization: userData.token,
				},
			},
		);
		if (!response.ok) {
			return null;
		}
		return await response.json();
	} catch (e) {
		console.log(e);
		return null;
	}
}

export async function deleteUnterricht(unterrichtId) {
	try {
		const response = await fetch(
			"http://localhost:8080/Backend/unterricht/" + unterrichtId,
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