<template>
	<div class="check-in-code">
		<h1 v-if="keinUnterricht">Du hast gerade frei!</h1>
		<h1 v-if="!eingeloggt && !keinUnterricht">Gib deinen CheckIn-Code ein</h1>
		<h2 v-if="stunde != undefined">
			Du hast gerade {{ stunde.unterricht.kurs.fach }} ({{
				stunde.unterricht.kurs.art
			}}{{ stunde.unterricht.kurs.nummer }})
		</h2>
		<p v-if="stunde != undefined && !keinUnterricht">
			Bei {{ stunde.unterricht.kurs.leiter.vorname }}
			{{ stunde.unterricht.kurs.leiter.name }}
		</p>
		<form @submit.prevent="submitCode" v-if="!keinUnterricht">
			<div v-if="!eingeloggt" class="input-container">
				<input
					v-for="(number, index) in Array.from({ length: 5 })"
					:key="index"
					type="number"
					maxlength="1"
					v-model="code[index]"
					@input="handleInput(index)"
					@keydown.delete="handleDelete"
					ref="inputs"
					:id="`input-${index}`"
				/>
			</div>
			<img
				class="success_image"
				v-if="showSuccessMessage"
				src="../assets/pictures/success.png"
				alt="eingeloggt"
			/>
		</form>
		<button
			class="checkout_button"
			v-if="showCheckOutButton && !showSuccessMessage"
			@click="checkOut_"
		>
			Check Out
		</button>
	</div>
</template>

<script setup>
import { ref, onMounted, nextTick } from "vue";
import { useUserStore } from "@/stores/user";
import { useStundenStore } from "@/stores/stunden";
import { checkin, checkout, getAktuelleStunde } from "@/requests/stunde";

const userData = useUserStore();
const stundenData = useStundenStore();

const code = ref(Array.from({ length: 5 }, () => ""));
const inputs = ref([]);
const stunde = ref(undefined);
const eingeloggt = ref(false);
const showSuccessMessage = ref(false);
const showCheckOutButton = ref(false);
const keinUnterricht = ref(false);

onMounted(() => {
	inputs.value = Array.from({ length: 5 }, (_, i) =>
		document.getElementById(`input-${i}`),
	);
	getCurrentStunde();
});

const handleInput = (index) => {
	console.log(index);
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
	let data = await getAktuelleStunde();
	if (data === null) {
		console.log("Error fetching data");
	} else if(!(data.stunde && data.teilnahme)){
		console.log("No data");
		keinUnterricht.value = true;
	} else {
		stundenData.setAktuelleStunde(data.stunde);
		stundenData.setAktuelleTeilnahme(data.teilnahme);
		console.log(data);
		stunde.value = data.stunde;
		eingeloggt.value = data.teilnahme.anwesend && data.teilnahme.endtimestamp === undefined;
		showCheckOutButton.value = data.teilnahme.anwesend && data.teilnahme.endtimestamp === undefined;
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

const submitCode = async () => {
	let daten = await checkin(
		stundenData.aktuelleStunde.id,
		code.value.join(""),
	);

	if (daten === null) {
		console.log("Error");
		return;
	}
	eingeloggt_();
};

const eingeloggt_ = async () => {
	eingeloggt.value = true;
	showSuccessMessage.value = true;
	setTimeout(() => {
		showSuccessMessage.value = false;
	}, 2000);
	showCheckOutButton.value = true;
};


const checkOut_ = async () => {
	let data = await checkout(stundenData.aktuelleStunde.id);
	if (data !== null) {
		console.log("Success");
		eingeloggt.value = false;
		showCheckOutButton.value = false;
	} else {
		console.log("Error");
	}
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

p {
	color: var(--color-text);
	font-size: calc(var(--text-size) * 1.2);
	text-align: center;
}

.success_image {
	width: 80px;
	height: 80px;
	display: block;
	margin: 0 auto;
	margin-top: 20px;
}

.input-container {
	display: flex;
	justify-content: center;
	padding: 10px;
	border-radius: 10px;
}

.checkout_button {
	width: 100%;
	height: 50px;
	background-color: #cc4321a8;
	color: var(--color-text);
	border: none;
	border-radius: 10px;
	margin-top: 20px;
	cursor: pointer;
}
.checkout_button:hover {
	background-color: #642211;
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
	cursor: pointer;
}

input[type="number"]::-webkit-inner-spin-button,
input[type="number"]::-webkit-outer-spin-button {
	-webkit-appearance: none;
	margin: 0;
}

input[type="number"] {
	-moz-appearance: textfield;
}
</style>
