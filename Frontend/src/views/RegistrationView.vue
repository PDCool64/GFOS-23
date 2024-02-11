<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";

const userData = useUserStore();

const vorname = ref("");
const nachname = ref("");
const email = ref("");
const password = ref("");
const geburtstag = ref("");
const isadmin = ref(false);

const error = ref(false);

const errorMessage = ref("");


const submitForm = async () => {
	const response = await fetch("http://localhost:8080/Backend/account", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
			"Authorization": userData.token,
			password: password.value,
		},
		body: JSON.stringify({
			vorname: vorname.value,
			name: nachname.value,
			email: email.value,
			geburtsdatum: geburtstag.value + "T00:00:00Z[UTC]",
			isadmin: isadmin.value,
		}),
	});

	if (response.status === 400) {
		errorMessage.value = "Ein Account mit dieser Email existiert bereits.";
		error.value = true;
	} else {
		const data = await response.json();
		console.log(data);
		errorMessage.value = "Registrierung erfolgreich.";
		error.value = false;
	}
};
</script>

<template>
	<div class="wrapper">
		<div class="registration form">
			<h1>Registrierung</h1>
			<form @submit.prevent="submitForm">
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
				<button type="submit">Register</button>
                <p :class="{ 'error-message': error, 'success-message': !error }">
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
.checkbox-wrapper {
    display: flex;
    align-items: center;
    justify-content: space-between; 
    margin-bottom: 10px;

    input{
        margin: 0;
        width: auto;
    }
}

</style>