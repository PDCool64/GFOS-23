<template>
	<div class="stammdaten">
		<form @submit.prevent="update">
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
import { getAccount, updateAccount } from "@/requests/account";

const userData = useUserStore();

let account = ref({});

onMounted(async () => {
	let data = await getAccount(userData.user.id);
	if (data === null) {
		router.push("/login");
		userData.reset();
	}
	data.geburtsdatum = data.geburtsdatum.substring(0, 10);
	data.geburtsdatum = new Date(data.geburtsdatum);
	data.geburtsdatum = data.geburtsdatum.toISOString().substring(0, 10);
	account.value = data;
});

async function update() {
	var temp = { ...account.value };
	if (!"geburtsdatum" in temp) {
		temp.geburtsdatum = new Date();
	}
	temp.geburtsdatum = temp.geburtsdatum + "T00:00:01Z[UTC]";
	let response = updateAccount(temp);
	if (response === null) {
		userData.reset();
		router.push("/login");
	} else {
		alert("Account erfolgreich geändert");
	}
	temp.geburtsdatum = temp.geburtsdatum.substring(0, 10);
	account.value = temp;
}
</script>

<style scoped>
@import "../assets/shared_styles/data_form.css";
</style>
