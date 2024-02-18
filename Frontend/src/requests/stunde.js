export const updateTeilnahmen = async (toJson, stundenId) => {
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
	const response = await fetch(
		"http://localhost:8080/Backend/stunde/teilnahme/" + stundenId,
	);
	let sampleData = await response.json();
	for (let i = 0; i < sampleData.length; i++) {
		sampleData[i].stunde.datum = sampleData[i].stunde.datum.substring(
			0,
			10,
		) + "T00:00:00.000Z";
	}
	return sampleData;
};
