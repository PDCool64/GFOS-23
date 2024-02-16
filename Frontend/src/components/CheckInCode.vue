<template>
	<div class="check-in-code">
		<h1>Gib deinen CheckIn-Code ein</h1>
		<h2 v-if="stunde != undefined">Du hast gerade {{ stunde.unterricht.kurs.fach }}</h2>
		<form @submit.prevent="submitCode">
			<div class="input-container">
				<input
					v-for="(item, index) in Array.from({ length: 5 })"
					:key="index"
					type="digit"
					maxlength="1"
					v-model="code[index]"
					@input="handleInput(index)"
					@keydown.delete="handleDelete"
					ref="inputs"
					:id="`input-${index}`"
				/>
			</div>
		</form>
	</div>
</template>

<script setup>
import { ref, onMounted, nextTick } from "vue";
import { useUserStore } from "@/stores/user";
import { useStundenStore } from "@/stores/stunden";

const userData = useUserStore();
const stundenData = useStundenStore();

const code = ref(Array.from({ length: 8 }, () => ""));
const inputs = ref([]);
const stunde = ref(undefined);

onMounted(() => {
	inputs.value = Array.from({ length: 5 }, (_, i) =>
		document.getElementById(`input-${i}`),
	);
	getCurrentStunde();
});

const handleInput = (index) => {
	if (index < 4 && inputs.value[index + 1]) {
		nextTick(() => {
			inputs.value[index + 1].focus();
		});
	} else {
		submitCode();
		clearInputs();
		inputs.value[0].focus();
	}
};

const clearInputs = () => {
	for (let i = 0; i < 5; i++) {
		code.value[i] = "";
	}
};

const getCurrentStunde = async () => {
	const response = await fetch(
		"http://localhost:8080/Backend/stunde/aktuell",
		{
			method: "GET",
			headers: {
				Authorization: userData.token,
			},
		},
	);
	if (!response.ok) {
		console.log("Error fetching data");
	} else {
		const data = await response.json();
		stundenData.setAktuelleStunde(data);
		console.log(data);
		stunde.value = data;
	}
};

const handleDelete = () => {
	const activeElementId = document.activeElement.id;
	const activeIndex = Number(activeElementId.split("-")[1]);
	if (activeIndex === 4 && code.value[4] !== "") {
		code.value[4] = "";
		return;
	}
	const targetIndex = activeIndex > 0 ? activeIndex - 1 : 0;

	code.value[targetIndex] = "";
	nextTick(() => {
		inputs.value[targetIndex].focus();
	});
};

const submitCode = () => {
	console.log(code.value.join(""));
};
</script>

<style scoped>
h1 {
	color: var(--color-text);
	font-size: calc(var(--text-size) * 2);
	text-align: center;
}

h2 {
	color: var(--color-text);
	font-size: calc(var(--text-size) * 1.5);
	text-align: center;
}

.input-container {
	display: flex;
	justify-content: center;
	padding: 10px;
	border-radius: 10px;
}

input {
	width: 60px;
	height: 80px;
	border: 1px solid #000;
	font-size: 2.2rem;
	margin: 0 5px;
	outline: none;
	background-color: var(--fourth-color);
	border: none;
	color: var(--color-text);
	text-align: center;
	box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.4);
	border-radius: 10px;
}

input:focus {
	border: 2px solid var(--color-border-hover);
}

button {
	width: 100%;
	height: auto;
	font-size: calc(var(--text-size) * 1.5);
	background-color: transparent;
	border: none;
	color: var(--color-text);
}
</style>
