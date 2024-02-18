<template>
	<div class="wrapper">
		<div class="registration form">
			<CustomForm @submit.prevent="createKurs(kurs)" header="Kurs&nbsp;erstellen" button-text="Erstellen">
				<input
					v-model="leiter"
					type="email"
					id="leiter"
					name="leiter"
					placeholder="Leiter"
					required
				/>
				<input
					v-model="fach"
					type="text"
					id="fach"
					name="fach"
					placeholder="Fach"
					required
				/>
				<input
					v-model="art"
					type="text"
					id="art"
					name="art"
					placeholder="Art"
					required
				/>
				<input
					v-model="nummer"
					type="number"
					id="nummer"
					name="nummer"
					placeholder="Nummer"
					required
				/>
				<input
					v-model="stufe"
					type="number"
					id="stufe"
					name="stufe"
					placeholder="Stufe"
					required
				/>
			</CustomForm>
		</div>
	</div>
</template>

<script setup>
import { computed, ref } from "vue";
import { useUserStore } from "@/stores/user";
import { createKurs } from "@/requests/kurs";
import CustomForm from "@/components/CustomForm.vue";

const userData = useUserStore();

const leiter = ref("");
const fach = ref("");
const art = ref("");
const nummer = ref("");
const stufe = ref("");
const kurs = computed(() => {
	return {
		leiter: leiter.value,
		fach: fach.value,
		art: art.value,
		nummer: nummer.value,
		stufe: stufe.value,
	};
});

</script>

<style scoped>
@import "../assets/shared_styles/form_inputs.css";
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
