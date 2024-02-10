<template>
	<div class="wrapper">
		<div class="registration form">
			<h1>erstelle neuen kurs</h1>
			<form @submit.prevent="submitForm">
                <input
                    v-model="leiter"
                    type="email"
                    id="leiter"
                    name="leiter"
                    placeholder="leiter"
                    required
                />
				<input
					v-model="fach"
					type="text"
					id="fach"
					name="fach"
					placeholder="fach"
					required
				/>
				<input
					v-model="art"
					type="text"
					id="art"
					name="art"
					placeholder="art"
					required
				/>
				<input
					v-model="nummer"
					type="number"
					id="nummer"
					name="nummer"
					placeholder="nummer"
					required
				/>
                <input
                    v-model="stufe"
                    type="number"
                    id="stufe"
                    name="stufe"
                    placeholder="stufe"
                    required
                />
				<button type="submit">Erstellen</button>
			</form>
		</div>
	</div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";

const userData = useUserStore();

const leiter =  ref("");
const fach = ref("");
const art = ref("");
const nummer = ref("");
const stufe = ref("");

const submitForm = () => {
    const response = fetch("http://localhost:8080/Backend/kurs", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": userData.token,
        },
        body: JSON.stringify({
            leiter: leiter.value,
            fach: fach.value,
            art: art.value,
            nummer: nummer.value,
            stufe: stufe.value,
       }),
    });
};
</script>

<style scoped>
@import "../assets/shared_styles/form.css";
input {
	padding: auto;
}
.wrapper {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100%;
	width: 100%;
}
</style>
