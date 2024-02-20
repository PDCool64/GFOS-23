<template>
	<div class="outer-wrapper">
		<div class="grid">
			<div class="neues-mitglied">
				<form @submit.prevent="submitForm" v-if="isAdding">
					<input
						type="text"
						class="input"
						placeholder="Email"
						v-model="email"
					/>
					<button class="submit_button" type="submit">OK</button>
				</form>
				<img
					class="image"
					src="../assets/pictures/add_user.png"
					alt="Neues Mitglied"
					@click="isAdding = !isAdding"
					v-if="!isAdding"
				/>
			</div>
			<div class="wrapper">
				<table class="custom-table">
					<thead>
						<tr class="table-header">
							<th>Name</th>
							<th>Vorname</th>
							<th>Geburtsdatum</th>
							<th>Email</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="teilnehmer in teilnahmen" :key="teilnehmer">
							<td>{{ teilnehmer.account.name }}</td>
							<td>{{ teilnehmer.account.vorname }}</td>
							<td>
								{{
									formatDate(
										teilnehmer.account.geburtsdatum.substring(
											0,
											10,
										),
									)
								}}
							</td>
							<td>{{ teilnehmer.account.email }}</td>
							<td>
								<img
									class="image"
									src="../assets/pictures/mull.png"
									alt="Löschen"
									@click="delete_(teilnehmer.account.id)"
								/>
								</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { useUserStore } from "@/stores/user";
import { useRoute } from "vue-router";
import { getTeilnahmen, createTeilnehmer, deleteTeilnehmer } from "@/requests/kurs";

const route = useRoute();
const userData = useUserStore();

const kursId = route.params.kurs;

const teilnahmen = ref([]);
const isAdding = ref(false);
const email = ref("");

const formatDate = (date) => {
	let temp = new Date(date);
	return temp.toLocaleDateString();
};

async function submitForm() {
	isAdding.value = false;
	let created = await createTeilnehmer(kursId, email.value);
	email.value = "";
	teilnahmen.value = await getTeilnahmen(kursId);
}

async function delete_(id) {
	await deleteTeilnehmer(kursId, id);
	teilnahmen.value = await getTeilnahmen(kursId);
}

onMounted(() => {
	getTeilnahmen(kursId).then((data) => {
		teilnahmen.value = data;
	});
});
</script>

<style scoped>
@import "../assets/shared_styles/account_list.css";
.outer-wrapper {
	min-height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
}
.neues-mitglied {
	width: 100%;
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 20px;
}
.image {
	width: 40px;
	height: 40px;
	opacity: 0.64;
	cursor: pointer;
}

.grid {
	display: grid;
}
.input {
	margin-right: 15px;
	height: auto;
	font-size: var(--text-size);
	color: var(--text-color);
	width: 100%;
}

.submit_button {
	width: 15%;
	margin-right: 30px;
	background-color: var(--third-color);
	border: none;
	border-radius: 5px;
	color: var(--text-color);
	cursor: pointer;
}

form {
	display: flex;
	justify-content: flex-end;
	width: 70%;
}
</style>
