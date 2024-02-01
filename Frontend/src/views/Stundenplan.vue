<template>
	<div class="table-container">
		<table>
			<thead>
				<tr>
					<th>Zeit</th>
					<th v-for="day in days" :key="day">{{ day }}</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="time in times" :key="time">
					<td class="time-cell">{{ time }}</td>
					<td class="data-cell" v-for="day in days" :key="day"></td>
				</tr>
			</tbody>
		</table>
	</div>
</template>

<script setup>
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
  "15:05 - 15:50"
];
</script>

<style scoped>
.table-container {
	margin: 3cm;
	overflow-y: auto;
	box-shadow:
		0 0 10px rgba(0, 0, 0, 0.9),
		0 0 10px rgba(0, 0, 0, 0.9); /* Schatten rechts und unten */
	width: calc(
		100% - 6cm
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
	overflow: hidden; /* Verhindern von Überlauf aus abgerundeten Ecken */
	width: 100%;
}

th,
td {
	padding: 12px;
	text-align: left;
	border-top: 1px solid #77aca7;
}

th {
	background-color: #77aca7;
	color: #333;
}

tr:nth-child(even) {
	background-color: transparent;
}
</style>
