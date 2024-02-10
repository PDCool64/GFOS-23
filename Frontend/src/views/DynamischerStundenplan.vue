<template>
	<div class="table-container">
		<table>
			<thead>
				<tr>
					<th>Zeit</th>
					<th v-for="(day, dayIndex) in days" :key="dayIndex">
						{{ day }}
					</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="(time, timeIndex) in times" :key="timeIndex">
					<td class="time-cell">{{ time }}</td>
					<td
						v-bind:class="{
							'data-cell': stunden[dayIndex][timeIndex] != '',
						}"
						v-for="(day, dayIndex) in days"
						:key="dayIndex"
						:id="`cell-${timeIndex}-${dayIndex}`"
						@click="openStunden(timeIndex, dayIndex)"
					>
						{{ stunden[dayIndex][timeIndex] }}
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";
import { useStundenStore } from "@/stores/stunden";
import router from "@/router";
import { useRoute } from "vue-router";

const route = useRoute();
const date = route.params.day;

const startDate = new Date(date);
const endDate = new Date(date);
endDate.setDate(endDate.getDate() + 6);

const userData = useUserStore();
const stundenData = useStundenStore();

const days = ["Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag"];
const times = [
	"07:55 - 08:40",
	"08:45 - 09:30",
	"09:50 - 10:35",
	"10:40 - 11:25",
	"11:40 - 12:25",
	"12:30 - 13:15",
	"13:30 - 14:15",
	"14:15 - 15:00",
	"15:05 - 15:50",
];

const stunden = ref(days.map(() => Array(times.length).fill("")));
async function reload() {
	const response = await fetch(
		"http://localhost:8080/Backend/stunde/" +
			startDate.toISOString().substring(0, 10) +
			"/" +
			endDate.toISOString().substring(0, 10),
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

	const temp = ref(days.map(() => Array(times.length).fill("")));
	for (stunde of data) {
		for (
			var s = stunde.unterricht.beginstunde;
			s <= stunde.unterricht.endstunde;
			s++
		)
			temp.value[stunde.unterricht.tag][s] = stunde;
	}
	stundenData.setStunden(temp);

	for (var stunde of data) {
		for (
			var s = stunde.unterricht.beginstunde;
			s <= stunde.unterricht.endstunde;
			s++
		)
			stunden.value[stunde.unterricht.tag][s] = stunde.unterricht.kurs.fach;
	}

	for (var i = 0; i < stunden.value.length; i++) {
		for (var j = 0; j < stunden.value[i].length; j++) {
			if (stunden.value[i][j] == "") {
			}
		}
	}
}

const openStunden = (timeIndex, dayIndex) => {
	if (stunden.value[dayIndex][timeIndex] == "") return;
	stundenData.setDate(date);
	router.push({
		name: "stunde",
		params: {
			day: dayIndex,
			time: timeIndex,
		},
	});
};

reload();
console.log("Done");
</script>

<style scoped>
.table-container {
	margin: calc(var(--navbar-margin) * 5);
	overflow-y: auto;
	box-shadow:
		0 0 10px rgba(0, 0, 0, 0.9),
		0 0 10px rgba(0, 0, 0, 0.9); /* Schatten rechts und unten */
	width: calc(
		100% - (var(--navbar-margin) * 10)
	); /* 100% Breite minus doppelter Rand des äußeren Containers */
	padding-bottom: 1cm;
	height: auto;
	padding: 10px;
	border-radius: 10px;
}
table {
	width: 100%;
	border-collapse: collapse;
	border-radius: 10px; /* Runde Ecken hinzufügen */
	overflow: hidden;
	table-layout: fixed;
}

th,
td {
	padding: 12px;
	text-align: center;
	border-top: 1px solid #77aca7;
}

th {
	background-color: var(--second-color);
	color: #333;
}

tr:nth-child(even) {
	background-color: transparent;
}

.data-cell {
	cursor: pointer;
}
</style>
