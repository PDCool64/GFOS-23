<template>
	<div class="outer-wrapper">
		<h1 v-if="sampleData[0] !== undefined">
			{{ sampleData[0].stunde.unterricht.kurs.fach }} ({{
				sampleData[0].stunde.unterricht.kurs.art
			}}{{ sampleData[0].stunde.unterricht.kurs.nummer }})
		</h1>
		<h2 v-if="sampleData[0] !== undefined">
			{{ sampleData[0].stunde.datum.substring(0, 10) }}
		</h2>
		<h2 v-if="sampleData[0] !== undefined">
			Von {{ times[sampleData[0].stunde.unterricht.beginstunde][0] }} bis
			{{ times[sampleData[0].stunde.unterricht.endstunde][1] }}
			{{ sampleData[0].stunde.unterricht.beginstunde }}
		</h2>
		<div class="button-wrapper">
			<div class="wrapper">
				<table class="custom-table">
					<tr class="table-header">
						<th>Name</th>
						<th>Vorname</th>
						<th>Anwesend</th>
						<th>Note</th>
						<th>Gekommen</th>
						<th>Gegangen</th>
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
						<td>
							<div v-if="data.begintimestamp">
								{{ data.begintimestamp.substring(11, 19) }}
							</div>
						</td>
						<td v-if="data.endtimestamp">
							{{ data.endtimestamp.substring(11, 19) }}
						</td>
					</tr>
				</table>
			</div>
			<button @click="updateTeilnahmen(toJson, stundenId)">Save</button>
		</div>
	</div>
</template>

<script setup>
import { computed, onMounted, ref } from "vue";
import { useRoute } from "vue-router";
import { updateTeilnahmen, getTeilnahmen } from "@/requests/stunde";

const route = useRoute();

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

const stundenId = route.params.id;
console.log(stundenId);

const sampleData = ref([]);
const toJson = computed(() => {
	let res = sampleData.value;
	for (let i = 0; i < res.length; i++) {
		if (res[i].note === "" || res[i].note === "-") {
			res[i].note = null;
		}
	}
	return JSON.stringify(res);
});

const setup = async () => {
	sampleData.value = getTeilnahmen(stundenId).then((res) => {
		sampleData.value = res;
	});
};

onMounted(() => {
	setup();
});

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
@import "@/assets/shared_styles/account_list.css";
.outer-wrapper {
	height: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
}

.button-wrapper {
	display: flex;
	flex-direction: column;
}

button {
	padding: 10px;
	background-color: var(--button-blue);
	color: var(--color-text);
	border: none;
	border-radius: 5px;
	margin-top: 10px;
	margin-left: auto;
}
</style>
