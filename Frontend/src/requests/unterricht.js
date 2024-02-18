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