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
const sampleData = ref([
	{
		id: 1,
		stunde: {
			id: 1,
			date: "2022-01-01",
			checkincode: "12345",
			unterricht: {
				id: 1,
				kurs: {
					id: 1,
					fach: "Mathe",
					art: "GK",
					nummer: 1,
					stufe: 12,
					leiter: {
						id: 1,
						name: "Doering",
						vorname: "Max",
						email: "maxdoering@web.de",
						isAdmin: false,
					},
				},
				tag: 1,
				beginStunde: 1,
				endStunde: 2,
			},
		},
		account: {
			name: "Doering",
			vorname: "Max",
			id: 1,
			isAdmin: false,
			email: "maxdoering@web.de",
		},
		note: "-",
		anwesend: false,
		beginTimestamp: "2022-01-01T12:00:00Z",
		endTimeStamp: "2022-01-01T13:00:00Z",
	},
	{
		id: 2,
		stunde: {
			id: 2,
			date: "2022-01-01",
			checkincode: "12345",
			unterricht: {
				id: 1,
				kurs: {
					id: 1,
					fach: "Mathe",
					art: "GK",
					nummer: 1,
					stufe: 12,
					leiter: {
						id: 1,
						name: "Doering",
						vorname: "Max",
						email: "maxdoering@web.de",
						isAdmin: false,
					},
				},
				tag: 1,
				beginStunde: 1,
				endStunde: 2,
			},
		},
		account: {
			name: "Trutz",
			vorname: "Max",
			id: 1,
			isAdmin: false,
			email: "maxtrutz@web.de",
		},
		note: 12,
		anwesend: true,
		beginTimestamp: "2022-01-01T12:00:00Z",
		endTimeStamp: "2022-01-01T13:00:00Z",
	},
	{
		id: 3,
		stunde: {
			id: 2,
			date: "2022-01-01",
			checkincode: "12345",
			unterricht: {
				id: 1,
				kurs: {
					id: 1,
					fach: "Mathe",
					art: "GK",
					nummer: 1,
					stufe: 12,
					leiter: {
						id: 1,
						name: "Doering",
						vorname: "Max",
						email: "maxdoering@web.de",
						isAdmin: false,
					},
				},
				tag: 1,
				beginStunde: 1,
				endStunde: 2,
			},
		},
		account: {
			name: "Goehlert",
			vorname: "Maximilian",
			id: 1,
			isAdmin: false,
			email: "maxgoehlert@web.de",
		},
		note: 5,
		anwesend: true,
		beginTimestamp: "2022-01-01T12:00:00Z",
		endTimeStamp: "2022-01-01T13:00:00Z",
	},
]);

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
.wrapper {
	display: flex;
	justify-content: flex-start;
	align-items: flex-start;
	border-radius: 10px;
	box-shadow: rgba(0, 0, 0, 0.5) 0px 0px 10px;
}

.clickable {
	cursor: pointer;
}

.center-image {
	display: flex;
	justify-content: center;
	align-items: center;
}

.table-header {
	background-color: var(--third-color);
	border-radius: 10px;
	overflow: hidden;
}

.custom-table {
	border-collapse: collapse;
	width: 100%;
}

.custom-table th {
	color: var(--color-text);
	font-weight: bold;
}

.custom-table th,
.custom-table td {
	padding: 15px;
	text-align: center;
	width: 100%;
	justify-content: center;
	align-items: center;
}

input {
	height: 100%;
	width: 100%;
	padding: 5px;
	outline: none;
	color: var(--color-text);
	background-color: var(--fourth-color);
	text-align: center;
	border: none;
	border-radius: 5px;
}

.anwesend-image {
	height: 30px;
	width: 30px;
	padding: 5px;
	background-color: var(--second-color);
	border-radius: 5px;
}
</style>
