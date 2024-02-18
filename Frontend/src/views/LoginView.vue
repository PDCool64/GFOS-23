<template>
	<div class="wrapper">
			<DataForm
				:fields="[
					{ id: 'email', label: 'Email:', model: email, type: 'text' },
					{ id: 'password', label: 'Password:', model: password, type: 'password' }
				]"
				buttonText="Login"
				header="Login"
				:onSubmit="submitForm"
				:isButtonDisabled="isButtonDisabled"
				:error="error"
				:errorMessage="errorMessage"
			/>
		</div>
</template>

<script setup>
import { ref } from "vue";
import router from "@/router";
import { useUserStore } from "@/stores/user";
import { setIsLeiter, login } from "@/requests/account";
import DataForm from "@/components/DataForm.vue";

const email = ref("");
const password = ref("");

const error = ref(false);
const errorMessage = ref("");

const isButtonDisabled = ref(false);

const userData = useUserStore();

const submitForm = async () => {
	console.log("submitForm");
	console.log(email.value);
	isButtonDisabled.value = true;
	
	error.value = login(email.value, password.value);

	if (error.value) {
		errorMessage.value = "Die Email oder das Passwort ist falsch.";
		isButtonDisabled.value = false;
	} else {
		errorMessage.value = "Login erfolgreich.";
		router.push("/");
	}
};
</script>

<style scoped>
@import "../assets/shared_styles/form.css";

button:disabled {
	opacity: 0.5;
	cursor: not-allowed;
}

.wrapper {
	min-height: 100vh;
	display: flex;
	align-items: center;
	justify-content: center;
	flex-direction: column;
}
</style>