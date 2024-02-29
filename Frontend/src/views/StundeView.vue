<template>
	<div class="wrapper">
		<div class="unterricht">
			<img
				class="unterricht__close"
				@click="goToStundenplan"
				src="../assets/pictures/kreuz.png"
				alt="X"
			/>
			<h2 class="unterricht__title">Details</h2>
			<div class="unterricht__card">
				<h3 class="unterricht__card-title">
					{{ content.kurs.fach }} ({{ content.kurs.art
					}}{{ content.kurs.nummer }})
				</h3>
				<p class="unterricht__card-text">
					Leiter: {{ content.kurs.leiter.name }},
					{{ content.kurs.leiter.vorname }}
				</p>
				<p class="unterricht__card-text">
					Stufe: {{ content.kurs.stufe }}
				</p>
				<p class="unterricht__card-text">
					Datum: {{ formatDate(stundenData.date, content.tag) }}
				</p>
				<p class="unterricht__card-text">
					Note: {{ note }}
				</p>
			</div>
		</div>
	</div>
</template>
<script setup>
import { ref, onMounted, VueElement } from "vue";
import { useUserStore } from "@/stores/user";
import { useStundenStore } from "@/stores/stunden";
import { useRouter } from "vue-router";
import { getIsLeiter } from "@/requests/kurs";

const userData = useUserStore();
const stundenData = useStundenStore();
const route = useRouter();
const day = ref(0);
const time = ref(0);

day.value = route.currentRoute.value.params.day;
time.value = route.currentRoute.value.params.time;

const content = ref("");
content.value = stundenData.stunden[day.value][time.value].stunde.unterricht;
const note = stundenData.stunden[day.value][time.value].note;

const goToStundenplan = () => {
	route.push("/stundenplan/" + stundenData.date);
};

const formatDate = (date, tag = 0) => {
	let temp = new Date(date);
	temp = new Date(temp.setDate(temp.getDate() + tag));
	return temp.toLocaleDateString();
};

const setup = async () => {
	if (userData.user.id != undefined) {
		let isLeiter = await getIsLeiter(
			content.value.kurs.id,
		);
	}	
};

onMounted(() => {
	setup();
});
</script>

<style scoped>
.wrapper {
	min-height: 100vh;
	display: flex;
	align-items: center;
	justify-content: center;
}

.unterricht {
	width: 100%;
	max-width: 600px;
	margin: 0 auto;
	padding: 20px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	position: relative;
}

.unterricht__title {
	margin-bottom: 20px;
	text-align: center;
}

.unterricht__card {
	padding: 20px;
	background-color: var(--fourth-color);
	border-radius: 10px;
}

.unterricht__card-title {
	margin-bottom: 10px;
	font-size: 1.5em;
	font-weight: bold;
}

.unterricht__card-text {
	margin-bottom: 10px;
}
.unterricht__close {
	position: absolute;
	top: 10px;
	right: 10px;
	background: none;
	border: none;
	font-size: 1.5em;
	cursor: pointer;
	filter: invert(1);
	width: 25px;
	height: 25px;
	opacity: 0.64;
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
	cursor: pointer;
}
</style>
