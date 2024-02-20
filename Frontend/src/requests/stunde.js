import { useUserStore } from "../stores/user";

let userData;

export const updateTeilnahmen = async (toJson, stundenId) => {
	try {
		if (userData === undefined) {
			userData = useUserStore();
		}
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
		return data;
	} catch (e) {
		console.log(e);
		return null;
	}
};

export const getTeilnahmen = async (stundenId) => {
	try {
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
	} catch (e) {
		console.log(e);
		return null;
	}
};

export const getStunden = async (startDate, endDate) => {
	try {
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
	} catch (e) {
		console.log(e);
		return null;
	}
};

export const checkin = async (stundenId, code) => {
	try {
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
	} catch (e) {
		console.log(e);
		return null;
	}
};

export const checkout = async (stundenId) => {
	try {
		const response = await fetch(
			"http://localhost:8080/Backend/stunde/checkout/" + stundenId,
			{
				method: "POST",
				headers: {
					Authorization: userData.token,
				},
			},
		);
		if (!response.ok) {
			return null;
		}
		let data = await response.json();
		return data;
	} catch (e) {
		console.log(e);
		return null;
	}
};