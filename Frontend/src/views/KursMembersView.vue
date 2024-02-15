<template>
	<div class="outer-wrapper">
		<div class="wrapper">
			<table class="custom-table">
				<thead>
					<tr class="table-header">
						<th>Name</th>
						<th>Vorname</th>
						<th>Mail</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="teilnehmer in teilnahmen" :key="index">
						<td>{{ teilnehmer.account.name }}</td>
						<td>{{ teilnehmer.account.vorname }}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";
import { useRoute } from "vue-router";

const route = useRoute();
const userData = useUserStore();

const kursId = route.params.kurs;

const teilnahmen = ref([]);

const getTeilnahmen = async () => {
	const response = await fetch(
		"http://localhost:8080/Backend/kurs/teilnahmen/" + kursId,
		{
			method: "GET",
			headers: {
				"Content-Type": "application/json",
				Authorization: userData.token,
			},
		},
	);
	if (!response.ok) {
		console.log("Error");
		return;
	}
	let data = await response.json();
	teilnahmen.value = data;
};

getTeilnahmen();
</script>
"

<style scoped>
@import "../assets/shared_styles/account_list.css";
.outer-wrapper {
	min-height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
}
</style>
