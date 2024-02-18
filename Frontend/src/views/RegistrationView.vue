<script setup>
import { computed, ref } from "vue";
import { useUserStore } from "@/stores/user";
import CustomForm from "@/components/CustomForm.vue";
import { createAccount } from "@/requests/account";

const userData = useUserStore();

const vorname = ref("");
const nachname = ref("");
const email = ref("");
const password = ref("");
const geburtstag = ref("");
const isadmin = ref(false);

const error = ref(false);

const errorMessage = ref("");
const isButtonDisabled = ref(false);

const account = computed(() => {
	return {
		vorname: vorname.value,
		nachname: nachname.value,
		email: email.value,
		password: password.value,
		geburtstag: geburtstag.value,
		isadmin: isadmin.value,
	};
});

const submitForm = async (account, password) => {
	isButtonDisabled.value = true;
	let statuscode = createAccount(account, password);
	if (statuscode === 400) {
		errorMessage.value = "Ein Account mit dieser Email existiert bereits.";
		error.value = true;
		isButtonDisabled.value = false;
	}
	if (!(statuscode >= 200 && statuscode < 300)) {
		errorMessage.value = "Etwas anderes ist schief gelaufen";
		isButtonDisabled.value = false;
	} else {
		const data = await response.json();
		errorMessage.value = "Registrierung erfolgreich.";
		error.value = false;
		isButtonDisabled.value = true;
	}
};
</script>

<template>
	<div class="wrapper">
		<div class="registration form">
			<h1>Registrierung</h1>
			<CustomForm
				@submit.prevent="submitForm"
				button-text="Registrieren"
				header=""
			>
				<input
					v-model="vorname"
					type="text"
					id="vorname"
					name="vorname"
					placeholder="Vorname"
					required
				/>
				<input
					v-model="nachname"
					type="text"
					id="nachname"
					name="nachname"
					placeholder="Nachname"
					required
				/>
				<input
					v-model="email"
					type="email"
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
				<input
					v-model="geburtstag"
					type="date"
					id="geburtstag"
					name="geburtstag"
					placeholder="Geburtsdatum"
					required
				/>
				<div class="checkbox-wrapper">
					<p>Admin</p>
					<input
						type="checkbox"
						class="checkbox"
						id="isadmin"
						name="isadmin"
						v-model="isadmin"
					/>
				</div>
			</CustomForm>
			<p
				:class="{
					'error-message': error,
					'success-message': !error,
				}"
			>
				{{ errorMessage }}
			</p>
		</div>
	</div>
</template>

<style scoped>
@import "../assets/shared_styles/form_inputs.css";
.wrapper {
	min-height: 100vh;
	display: flex;
	align-items: center;
	justify-content: center;
}
.checkbox-wrapper {
	display: flex;
	align-items: center;
	justify-content: space-between;
	margin-bottom: 10px;

	input {
		margin: 0;
		width: auto;
	}
}

button:disabled {
	opacity: 0.5;
	cursor: not-allowed;
}
</style>
