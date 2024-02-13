<script setup>
import { ref } from "vue";
import router from "@/router";
import { useUserStore } from "@/stores/user";
const email = ref("");
const password = ref("");

const error = ref(false);
const errorMessage = ref("");


const userData = useUserStore();

const setIsLeiter = async () => {
	const response = await fetch("http://localhost:8080/Backend/account/isleiter", {
		method: "GET",
		headers: {
			"Authorization": userData.token,
		},
	});
	const data = await response.json();
	userData.setIsLeiter(data.isLeiter);
};

const submitForm = async () => {
	const response = await fetch("http://localhost:8080/Backend/login", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify({
			email: email.value,
			password: password.value,
		}),
	});

	if (!response.ok) {
		errorMessage.value = "Die Email oder das Passwort ist falsch.";
		error.value = true;
	} else {
		const data = await response.json();
		errorMessage.value = "Login erfolgreich.";
		error.value = false;
		const account = JSON.parse(data.account);
		data.account = account;
		userData.setData(data);
		setIsLeiter();
		router.push("/");
	}
};
</script>

<template>
	<div class="wrapper">
		<div class="login form">
			<h1>Login</h1>
			<form method="post" @submit.prevent="submitForm">
				<input
					v-model="email"
					type="text"
					id="email"
					name="email"
					placeholder="Email"
					required
				/>
				<input
					v-model="password"
					type="password"
					id="password"
					name="password"
					placeholder="Password"
					required
				/>
				<button type="submit">Login</button>
				<p
					:class="{
						'error-message': error,
						'success-message': !error,
					}"
				>
					{{ errorMessage }}
				</p>
			</form>
		</div>
	</div>
</template>

<style scoped>
@import "../assets/shared_styles/form.css";

.wrapper {
	min-height: 100vh;
	display: flex;
	align-items: center;
	justify-content: center;
}
</style>
