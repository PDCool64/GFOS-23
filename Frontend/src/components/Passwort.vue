<template>
	<div class="password">
		<form @submit.prevent="changePassword">
			<label for="old-password">Old Password:</label>
			<input
				id="old-password"
				v-model="oldPassword"
				type="password"
				required
			/>
			<label for="new-password">New Password:</label>
			<input
				id="new-password"
				v-model="newPassword"
				type="password"
				required
			/>
			<button type="submit">Change Password</button>
		</form>
		<RouterLink class="link" to="/profile">Cancel</RouterLink>
	</div>
</template>

<script setup>
import { ref } from "vue";
import { useTokenStore } from "@/stores/token";
import router from "@/router";

const tokenStore = useTokenStore();
const oldPassword = ref("");
const newPassword = ref("");

const changePassword = async () => {
	const response = await fetch(
		"http://localhost:8080/Backend/account/password",
		{
			method: "PUT",
			headers: {
				"Content-Type": "application/json",
				Authorization: sessionStorage.getItem("token"),
			},
			body: JSON.stringify({
				oldPassword: oldPassword.value,
				newPassword: newPassword.value,
			}),
		},
	);

	if (!response.ok) {
		const message = `An error has occured: ${response.status}`;
		throw new Error(message);
	} else {
		alert("Password changed successfully.");
		router.push("/profile");
	}

	const data = await response.json();
	console.log(data);
};
</script>

<style scoped>
@import "../assets/shared_styles/data_form.css";
</style>
