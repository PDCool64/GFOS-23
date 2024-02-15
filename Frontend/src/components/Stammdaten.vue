<template>
	<div class="stammdaten">
		<form @submit.prevent="updateAccount">
			<label for="vorname">Vorname:</label>
			<input id="vorname" v-model="account.vorname" />
			<label for="name">Name:</label>
			<input id="name" v-model="account.name" />

			<label for="email">Email:</label>
			<input id="email" v-model="account.email" />

			<label for="geburtsdatum">Geburtsdatum:</label>
			<input
				type="date"
				id="geburtsdatum"
				v-model="account.geburtsdatum"
			/>
			<button type="submit">Update</button>
		</form>
		<RouterLink class="link" to="/password">Passwort ändern</RouterLink>
	</div>
</template>

<script setup>
import { ref, onMounted, VueElement } from "vue";
import { useUserStore } from "@/stores/user";
import router from "@/router";

const userData = useUserStore();

let account = ref({});

const cancel = () => {
	router.push("/");
};

onMounted(async () => {
	const response = await fetch(
		"http://localhost:8080/Backend/account/" + userData.user.id,
		{
			method: "GET",
			headers: {
				"Content-Type": "application/json",
				Authorization: userData.token,
			},
		},
	);
	if (!response.ok) {
		router.push("/login");
		userData.reset();
	}
	account.value = await response.json();
	account.value.geburtsdatum = account.value.geburtsdatum.substring(0, 10);
	let date = new Date(account.value.geburtsdatum);
	account.value.geburtsdatum = date.toISOString().substring(0, 10);
});

async function updateAccount() {
	var temp = { ...account.value };
	temp.geburtsdatum = temp.geburtsdatum + "T00:00:01Z[UTC]";
	const response = await fetch("http://localhost:8080/Backend/account/", {
		method: "PUT",
		headers: {
			"Content-Type": "application/json",
			Authorization: userData.token,
		},
		body: JSON.stringify(temp),
	});
	if (!response.ok) {
		userData.reset();
	} else {
		alert("Account erfolgreich geändert");
	}

	temp = await response.json();
	temp.geburtsdatum = temp.geburtsdatum.substring(0, 10);

	account.value = temp;
}
</script>

<style scoped>
@import "../assets/shared_styles/data_form.css";
</style>
