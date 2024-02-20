import { useUserStore } from "../stores/user";
import address from "../address";

let userData;

export const updateTeilnahmen = async (toJson, stundenId) => {
	try {
		if (userData === undefined) {
			userData = useUserStore();
		}
		const response = await fetch(
			address + "/stunde/teilnahme/" + stundenId,
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
			address + "/stunde/teilnahme/" + stundenId,
			{
				method: "GET",
				headers: {
					Authorization: userData.token,
				},
			},
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
			address + "/stunde/" + startDate + "/" + endDate,
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
			address + "/stunde/checkin/" + stundenId,
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
			address + "/stunde/checkout/" + stundenId,
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

export const getAktuelleStunde = async () => {
	if (userData === undefined) {
		userData = useUserStore();
	}
	const response = await fetch(
		address + "/stunde/aktuell",
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
	let data = await response.json();
	if ("stunde" in data || "teilnahme" in data) {
		data.stunde = JSON.parse(data.stunde);
		data.teilnahme = JSON.parse(data.teilnahme);
		console.log(data);
		return data;
	}
	return {};
};
