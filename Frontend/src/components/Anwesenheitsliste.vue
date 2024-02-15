<template>
	<div class="wrapper">
		<table class="custom-table">
			<tr class="table-header">
				<th>Name</th>
				<th>Vorname</th>
				<th>Anwesend</th>
				<th>Note</th>
			</tr>
			<tr v-for="(data, index) in sampleData" :key="index">
				<td>{{ data.account.name }}</td>
				<td>{{ data.account.vorname }}</td>
				<td @click="changeAnwesend(index)" class="center-image">
					<img
						v-if="data.anwesend"
						class="anwesend-image clickable"
						src="@/assets/pictures/haekchen.png"
						alt="Anwesend"
					/>
					<img
						v-else
						class="anwesend-image clickable"
						src="@/assets/pictures/kreuz.png"
						alt="Nicht Anwesend"
					/>
				</td>
				<td>
					<input
						type="text"
						placeholder="Note"
						v-model="data.note"
						:disabled="!data.anwesend"
					/>
				</td>
			</tr>
		</table>
	</div>
</template>

<script setup>
import { ref } from "vue";
import data from "@/assets/test_data/anwesenheit.json";

const sampleData = ref(data);

function changeAnwesend(index) {
	sampleData.value[index].anwesend = !sampleData.value[index].anwesend;
	if (!sampleData.value[index].anwesend) {
		sampleData.value[index].note = "-";
	} else {
		sampleData.value[index].note = "";
	}
}

</script>

<style scoped>
@import "@/assets/shares_styles/account_list.css";
</style>
