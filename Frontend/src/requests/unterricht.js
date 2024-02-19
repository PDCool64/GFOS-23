import { useUserStore } from "@/stores/user";


const userData = useUserStore();

export async function createUnterricht(unterricht) {
	const response = await fetch("http://localhost:8080/Backend/unterricht", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
			Authorization: userData.token,
		},
		body: JSON.stringify(unterricht),
	});
}

export async function getAllUnterrichtByKurs(kurs) {
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
	return await response.json();
}

export async function deleteUnterricht(unterrichtId) {
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
}