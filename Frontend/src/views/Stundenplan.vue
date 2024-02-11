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
							filled: stunden[dayIndex][timeIndex] != '',
							'data-cell': true,
						}"
						v-for="(day, dayIndex) in days"
						:key="dayIndex"
						:id="`cell-${timeIndex}-${dayIndex}`"
						@click="openUnterricht(timeIndex, dayIndex)"
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
import { useUnterrichtStore } from "@/stores/unterricht";
import router from "@/router";

const userData = useUserStore();
const unterrichtData = useUnterrichtStore();

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
	const response = await fetch("http://localhost:8080/Backend/unterricht", {
		method: "GET",
		headers: {
			"Content-Type": "application/json",
			Authorization: userData.token,
		},
	});
	if (!response.ok) {
		console.log("Error");
		return;
	}

	let data = await response.json();
	console.log(data);

	const temp = ref(days.map(() => Array(times.length).fill("")));
	for (unterricht of data) {
		for (
			var stunde = unterricht.beginstunde;
			stunde <= unterricht.endstunde;
			stunde++
		)
			temp.value[unterricht.tag][stunde] = unterricht;
	}
	unterrichtData.setUnterricht(temp);

	console.log(temp.value);
	for (var unterricht of data) {
		for (
			var stunde = unterricht.beginstunde;
			stunde <= unterricht.endstunde;
			stunde++
		)
			stunden.value[unterricht.tag][stunde] = unterricht.kurs.fach;
	}

	for (var i = 0; i < stunden.value.length; i++) {
		for (var j = 0; j < stunden.value[i].length; j++) {
			if (stunden.value[i][j] == "") {
			}
		}
	}
}

const openUnterricht = (timeIndex, dayIndex) => {
	if (stunden.value[dayIndex][timeIndex] == "") return;
	router.push({
		name: "unterricht",
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
@import "../assets/shared_styles/stundenplan.css";
</style>
