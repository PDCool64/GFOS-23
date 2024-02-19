<template>
	<div class="wrapper">
		<CustomForm
			@submit="createUnterricht(unterricht); isButtonDisabled = true;"
			class="form"
			button-text="Erstellen"
			header="Unterricht&nbsp;erstellen"
			v-model="isButtonDisabled"
		>
			<label for="startDate">Kursanfang</label>
			<input
				type="date"
				v-model="startDate"
				id="startDate"
				name="startDate"
				required
			/>
			<label for="endDate">Kursende</label>
			<input
				type="date"
				v-model="endDate"
				id="endDate"
				name="endDate"
				required
			/>
			<label for="beginStunde">Stunden</label>
			<input
				v-model="beginStunde"
				type="number"
				id="beginStunde"
				name="beginStunde"
				placeholder="Von"
				required
			/>
			<label for="endStunde">Stunden</label>
			<input
				v-model="endStunde"
				type="number"
				id="endStunde"
				name="endStunde"
				placeholder="Bis"
				required
			/>
			<div class="input-group">
				<label for="tag">Day of the week</label>
				<select v-model="tag" id="tag" name="tag" required>
					<option value="0">Monday</option>
					<option value="1">Tuesday</option>
					<option value="2">Wednesday</option>
					<option value="3">Thursday</option>
					<option value="4">Friday</option>
				</select>
			</div>
		</CustomForm>
	</div>
</template>

<script setup>
import { computed, ref } from "vue";
import { useUserStore } from "@/stores/user";
import { useRoute } from "vue-router";
import { createUnterricht } from "@/requests/unterricht";
import CustomForm from "@/components/CustomForm.vue";

const route = useRoute();
const kursId = route.params.kurs;

const userData = useUserStore();

const startDate = ref("");
const endDate = ref("");
const beginStunde = ref();
const endStunde = ref();
const tag = ref(0);

const isButtonDisabled = ref(false);

const unterricht = computed(() => {
	return {
		startDate: startDate.value,
		endDate: endDate.value,
		unterricht: {
			beginStunde: parseInt(beginStunde.value) - 1,
			endStunde: parseInt(endStunde.value) - 1,
			id: parseInt(kursId),
			tag: parseInt(tag.value),
		},
	};
});
</script>

<style scoped>
@import "../assets/shared_styles/form_inputs.css";
.wrapper {
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	height: 100%;
	width: 100%;
}

.input-group {
	display: flex;
	flex-direction: column;
	margin-bottom: 15px;
}

.input-group label {
	margin-bottom: 5px;
}

.input-group select {
	padding: 5px;
	font-size: var(--text-size);

	border-radius: 4px;
	font-size: 16px;
	color: var(--color-text);

	background-color: var(--fourth-color);
	border: none;
	box-shadow: none;
}

.input-group select:focus {
	outline: 0;
}
</style>
