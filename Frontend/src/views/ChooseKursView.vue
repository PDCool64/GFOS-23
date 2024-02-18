<template>
	<div class="wrapper">
		<div class="grid">
			<div
				v-for="kurs in kurse"
				:key="kurs.id"
				@click="createUnterricht(kurs.id)"
				class="kurs-item"
			>
				<h2>{{ kurs.fach }} ({{ kurs.art }}{{ kurs.nummer }})</h2>
				<p>Stufe: {{ kurs.stufe }}</p>
			</div>
		</div>
	</div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useUserStore } from "@/stores/user";
import router from "@/router";
import { getLeiterKurse } from "@/requests/kurs";

const userData = useUserStore();

const kurse = ref([]);

const setup = async () => {
	kurse.value = await getLeiterKurse();
};

onMounted(() => {
	setup();
});

function createUnterricht(kursId) {
	router.push("/kurs/verwalten/" + kursId);
}


</script>

<style scoped>
@import "../assets/shared_styles/chooser.css";
</style>
