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
import { useAccountIdStore } from "@/stores/accountId";
import { useTokenStore } from "@/stores/token";
import router from "@/router";
import { useToasted } from "vue-toasted";

const accountIdStore = useAccountIdStore();
const tokenStore = useTokenStore();

let account = ref({});

const cancel = () => {
	router.push("/");
};

onMounted(async () => {
	const response = await fetch(
		"http://localhost:8080/Backend/account/" +
			sessionStorage.getItem("accountId"),
		{
			method: "GET",
			headers: {
				"Content-Type": "application/json",
				Authorization: sessionStorage.getItem("token"),
			},
		},
	);
	if (!response.ok) {
		router.push("/login");
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
		},
		body: JSON.stringify(temp),
	});

	if (!response.ok) {
	} else {
		alert("Account erfolgreich geändert");
	}

	temp = await response.json();
	temp.geburtsdatum = temp.geburtsdatum.substring(0, 10);

	account.value = temp;
}
</script>

<style scoped>
@import '../assets/shared_styles/data_form.css'
</style>
