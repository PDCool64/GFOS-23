<template>
	<div class="wrapper">
		<CustomForm
			header="Login"
			buttonText="Submit"
			@submit="submitForm"
			v-model="isButtonDisabled"
		>
			<input
				id="email"
				v-model="email"
				type="text"
				placeholder="Email"
				required
			/>
			<input
				id="password"
				v-model="password"
				type="password"
				placeholder="Password"
				required
			/>
		</CustomForm>
	</div>
</template>

<script setup>
import { ref } from "vue";
import router from "@/router";
import { useUserStore } from "@/stores/user";
import { login } from "@/requests/account";
import CustomForm from "@/components/CustomForm.vue";

const email = ref("");
const password = ref("");

const error = ref(false);
const errorMessage = ref("");

const isButtonDisabled = ref(false);

const userData = useUserStore();

const submitForm = async () => {
	console.log("submitForm");
	isButtonDisabled.value = true;

	error.value = await login(email.value, password.value);

	console.log(error.value);

	if (!error.value) {
		errorMessage.value = "Die Email oder das Passwort ist falsch.";
		isButtonDisabled.value = false;
		console.log("Login failed");
	} else {
		errorMessage.value = "Login erfolgreich.";
		router.push("/");
	}
};
</script>

<style scoped>
@import "../assets/shared_styles/form_inputs.css";


.wrapper {
	min-height: 100vh;
	display: flex;
	align-items: center;
	justify-content: center;
	flex-direction: column;
}
</style>
