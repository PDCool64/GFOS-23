<script setup>
import { useAccountIdStore } from "@/stores/accountId";
import { useTokenStore } from "@/stores/token";
import { ref } from "vue";
import router from "@/router";
const email = ref("");
const password = ref("");

const error = ref(false);
const errorMessage = ref("");

const accountIdStore = useAccountIdStore();
const tokenStore = useTokenStore();

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
		console.log(data);
		errorMessage.value = "Login erfolgreich.";
		error.value = false;
		sessionStorage.setItem("token", data.token);
		sessionStorage.setItem("accountId", data.id);
		router.push("/profile");
	}
};
</script>

<template>
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
			<p :class="{ 'error-message': error, 'success-message': !error }">
				{{ errorMessage }}
			</p>
		</form>
		<p>
			Don't have an account?
			<RouterLink to="/registration">Register</RouterLink>
		</p>
	</div>
</template>

<style scoped>
@import "../assets/shared_styles/form.css";
</style>
