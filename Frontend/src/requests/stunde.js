import { useUserStore } from "@/stores/user";

let userData;

export const updateTeilnahmen = async (toJson, stundenId) => {
	if (userData === undefined) {
		userData = useUserStore();
	}
	console.log({
		method: "PUT",
		headers: {
			"Content-Type": "application/json",
		},
		body: toJson,
	});
	const response = await fetch(
		"http://localhost:8080/Backend/stunde/teilnahme/" + stundenId,
		{
			method: "PUT",
			headers: {
				"Content-Type": "application/json",
			},
			body: toJson,
		},
	);

	let data = await response.json();
	console.log(data);
	return data;
};

export const getTeilnahmen = async (stundenId) => {
	if (userData === undefined) {
		userData = useUserStore();
	}
	const response = await fetch(
		"http://localhost:8080/Backend/stunde/teilnahme/" + stundenId,
	);
	let sampleData = await response.json();
	for (let i = 0; i < sampleData.length; i++) {
		sampleData[i].stunde.datum =
			sampleData[i].stunde.datum.substring(0, 10) + "T00:00:00.000Z";
	}
	return sampleData;
};

export const getStunden = async (startDate, endDate) => {
	if (userData === undefined) {
		userData = useUserStore();
	}
	const response = await fetch(
		"http://localhost:8080/Backend/stunde/" + startDate + "/" + endDate,
		{
			method: "GET",
			headers: {
				"Content-Type": "application/json",
				Authorization: userData.token,
			},
		},
	);
	if (!response.ok) {
		return null;
	}
	return await response.json();
};

export const checkin = async (stundenId, code) => {
	if (userData === undefined) {
		userData = useUserStore();
	}
	const response = await fetch(
		"http://localhost:8080/Backend/stunde/checkin/" + stundenId,
		{
			method: "POST",
			headers: {
				"Content-Type": "application/json",
				Authorization: userData.token,
			},
			body: JSON.stringify({
				code: code,
			}),
		},
	);
	if (!response.ok) {
		return null;
	}
	let data = await response.json();
	return data;
};
export const checkout = async (stundenId) => {
	const response = await fetch(
		"http://localhost:8080/Backend/stunde/checkout/" + stundenId,
		{
			method: "POST",
			headers: {
				"Content-Type": "application/json",
				Authorization: userData.token,
			},
		},
	);
	if (!response.ok) {
		return null;
	}
	let data = await response.json();
	return data;
};
