<template>
	<div>
		<form @submit.prevent="updateAccount">
			<div class="input-group">
				<label for="vorname">Vorname:</label>
				<input id="vorname" v-model="account.vorname" />
			</div>

			<div class="input-group">
				<label for="name">Name:</label>
				<input id="name" v-model="account.name" />
			</div>

			<div class="input-group">
				<label for="email">Email:</label>
				<input id="email" v-model="account.email" />
			</div>

			<div class="input-group">
				<label for="geburtsdatum">Geburtsdatum:</label>
				<input
					type="date"
					id="geburtsdatum"
					v-model="account.geburtsdatum"
				/>
			</div>

			<button type="submit">Update</button>
			<RouterLink class="link" to="/password">Passwort ändern</RouterLink>
		</form>
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
.link {
	display: block;
	color: var(--text-dark-2);
	text-decoration: none;
	font-size: 1rem;
	text-align: center;
	border-radius: 10px;
	margin-top: 5px;
	margin: auto;
}

form {
	width: 100%;
	min-width: 280px;
	padding: 20px;
	border-radius: 8px;
}

.input-group {
	display: flex;
	align-items: center;
	margin-bottom: 10px;
	gap: 10px;
}

input {
	border-radius: 5px;
	border: none;
	background-color: var(--black-mute);
	flex-grow: 1;
	width: 100%;
	outline: none;
	padding: 3px;
	text-align: center;
	color: var(--text-dark-2);
}

button {
	cursor: pointer;
	padding: 10px;
	border: none;
	border-radius: 40px;
	color: white;
	background-color: var(--button-blue);
	margin-top: 10px;
	width: 100%;
}

button:hover {
	filter: brightness(0.9);
	transition: all 0.3s ease-in-out;
}

button:active {
	scale: 0.97;
	transition: all 0.15s ease-in-out;
}
</style>
