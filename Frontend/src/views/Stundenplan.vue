<template>
	<div class="table-container">
		<div class="options">
			<img
				class="pfeil"
				@click="previousWeek"
				src="../assets/pictures/links.png"
				alt="dkp"
			/>
			<img
				class="pfeil"
				@click="currentWeek"
				src="../assets/pictures/reload.png"
				alt="cdu"
			/>
			<img
				class="pfeil"
				@click="nextWeek"
				src="../assets/pictures/rechts.png"
				alt="afd"
			/>
		</div>
		<table>
			<thead>
				<tr>
					<th>Zeit</th>
					<th v-for="(day, dayIndex) in days" :key="dayIndex">
						<div class="days">
							{{ day[0] }}
							<div class="wider">{{ day[1] }}</div>
						</div>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="(time, timeIndex) in times" :key="timeIndex">
					<td class="time-cell">
						{{ time[0] }}
						<div class="wider">- {{ time[1] }}</div>
					</td>
					<td
						v-bind:class="{
							filled: stunden[dayIndex][timeIndex] != '',
							'data-cell': true,
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
import { ref, useSSRContext, computed, onMounted } from "vue";
import { useUserStore } from "@/stores/user";
import { useStundenStore } from "@/stores/stunden";
import router from "@/router";
import { useRoute } from "vue-router";
import { getStunden } from "@/requests/stunde";
import { getIsLeiter } from "@/requests/kurs";

const route = useRoute();
const date = ref(route.params.day);

const startDate = ref(new Date(date.value));
const endDate = ref(new Date(date.value));
endDate.value.setDate(endDate.value.getDate() + 6);

function reload_dates() {
	date.value = route.params.day;
	startDate.value = new Date(date.value);
	endDate.value = new Date(date.value);
	endDate.value.setDate(endDate.value.getDate() + 6);
}

const userData = useUserStore();
const stundenData = useStundenStore();

stundenData.setDate(date);

const days = [
	["Mo", "ntag"],
	["Di", "enstag"],
	["Mi", "ttwoch"],
	["Do", "nnerstag"],
	["Fr", "eitag"],
];
const times = [
	["07:55", "08:40"],
	["08:45", "09:30"],
	["09:50", "10:35"],
	["10:40", "11:25"],
	["11:40", "12:25"],
	["12:30", "13:15"],
	["13:30", "14:15"],
	["14:15", "15:00"],
	["15:05", "15:50"],
	["15:55", "16:40"],
	["16:45", "17:30"],
	["17:30", "18:15"],
	["18:20", "18:55"],
];

const stunden = ref(days.map(() => Array(times.length).fill("")));
async function reload() {
	stunden.value = days.map(() => Array(times.length).fill(""));

	let data = await getStunden(
		startDate.value.toISOString().substring(0, 10),
		endDate.value.toISOString().substring(0, 10),
	);

	if (data === null) {
		console.log("Error");
		return;
	}

	const temp = ref(days.map(() => Array(times.length).fill("")));
	for (var stunde of data) {
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
			stunden.value[stunde.unterricht.tag][s] =
				stunde.unterricht.kurs.fach;
	}
}

const openStunden = async (timeIndex, dayIndex) => {
	if (stunden.value[dayIndex][timeIndex] == "") return;
	if (
		await getIsLeiter(
			stundenData.stunden[dayIndex][timeIndex].unterricht.kurs.id,
		)
	) {
		router.push(
			"/stunde/anwesenheit/" +
				stundenData.stunden[dayIndex][timeIndex].id,
		);
		return;
	}
	stundenData.setDate(date);
	router.push({
		name: "stunde",
		params: {
			day: dayIndex,
			time: timeIndex,
		},
	});
};

onMounted(() => {
	reload();
});

console.log("Done");

const nextWeek = async () => {
	let date = route.params.day;
	if (date == undefined) date = new Date();
	else date = new Date(date);
	stundenData.setDate(new Date(date.setDate(date.getDate() + 7)));
	await router.push(
		"/stundenplan/" + stundenData.date.toISOString().split("T")[0],
	);
	reload_dates();
	await reload();
};

const previousWeek = async () => {
	let date = route.params.day;
	if (date == undefined) date = new Date();
	else date = new Date(date);
	stundenData.setDate(new Date(date.setDate(date.getDate() - 7)));
	await router.push(
		"/stundenplan/" + stundenData.date.toISOString().split("T")[0],
	);
	reload_dates();
	await reload();
};

const currentWeek = async () => {
	const now = new Date();
	const day = now.getDay();
	const diff = now.getDate() - day + (day == 0 ? -6 : 1);
	const thisWeekMonday = new Date(now.setDate(diff));
	await router.push(
		"/stundenplan/" + thisWeekMonday.toISOString().substring(0, 10),
	);
	reload_dates();
	await reload();
};
</script>

<style scoped>
@import "../assets/shared_styles/stundenplan.css";

.pfeil {
	width: calc(var(--image-size) * 1.4);
	height: calc(var(--image-size) * 1.4);
	opacity: var(--opacity);
	cursor: pointer;
	margin-bottom: 20px;
}
</style>
