import { useUserStore } from "@/stores/user";

let userData;

export const setIsLeiter = async () => {
	try {
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
	} catch (e) {
		return false;
	}
};

export const login = async (email, password) => {
	if (userData === undefined) {
		userData = useUserStore();
	}
	try {
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
		console.log(data);
		userData.setData(data);
		setIsLeiter();
		return response.ok;
	} catch (e) {
		console.log(e);
		return false;
	}
};

export const changePassword = async (oldPassword, newPassword) => {
	try {
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
	} catch (e) {
		return false;
	}
};

export const createAccount = async (account, password) => {
	try {
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
	} catch (e) {
		return null;
	}
};

export const getAccount = async (id) => {
	try {
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
	} catch (e) {
		return null;
	}
};

export const updateAccount = async (account) => {
	try {
		console.log(userData.id);
		const response = await fetch("http://localhost:8080/Backend/account/", {
			method: "PUT",
			headers: {
				"Content-Type": "application/json",
				Authorization: userData.token,
			},
			body: JSON.stringify(account),
		});
		if (!response.ok) {
			return null;
		}
		return await response.json();
	} catch (e) {
		return null;
	}
};
