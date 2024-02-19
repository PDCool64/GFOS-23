import { useUserStore } from "../stores/user";

const userData = useUserStore();

export const setIsLeiter = async () => {
	const response = await fetch(
		"http://localhost:8080/Backend/account/isleiter",
		{
			method: "GET",
			headers: {
				Authorization: userData.token,
			},
		},
	);
	const data = await response.json();
	userData.setIsLeiter(data.isLeiter);
};

export const login = async (email, password) => {
	const response = await fetch("http://localhost:8080/Backend/login", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify({
			email: email,
			password: password,
		}),
	});
	const data = await response.json();
	const account = JSON.parse(data.account);
	data.account = account;
	userData.setData(data);
	setIsLeiter();
	return response.ok;
};

export const changePassword = async (oldPassword, newPassword) => {
	const response = await fetch(
		"http://localhost:8080/Backend/account/password",
		{
			method: "PUT",
			headers: {
				"Content-Type": "application/json",
				Authorization: userData.token,
			},
			body: JSON.stringify({
				oldPassword: oldPassword,
				newPassword: newPassword,
			}),
		},
	);
	return response.ok;
};

export const createAccount = async (account, password) => {
	account.geburtsdatum = account.geburtsdatum + "T10:00:00.000Z";
	const response = await fetch("http://localhost:8080/Backend/account", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
			Authorization: userData.token,
			password: password,
		},
		body: JSON.stringify(account),
	});
	return response.status;
};

export const getAccount = async (id) => {
	const response = await fetch(
		`http://localhost:8080/Backend/account/${id}`,
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
	const data = await response.json();
	return data;
};
export const updateAccount = async (account) => {
	console.log(userData.id);
	let response;
	try {
		response = await fetch("http://localhost:8080/Backend/account/", {
			method: "PUT",
			headers: {
				"Content-Type": "application/json",
				Authorization: userData.token,
			},
			body: JSON.stringify(account),
		});
	} catch (e) {
	}
	if (!response.ok) {
		return null;
	}
	return await response.json();
};
