<template>
	<div class="wrapper">
		<h1>Kursverwaltung</h1>
		<div class="grid">
			<div @click="createUnterricht" class="kurs-item">
				<div class="one-line">
					<img src="../assets/pictures/zeit-add.png" alt="" />
					<h2>Unterricht erstellen</h2>
				</div>
			</div>
			<div @click="deleteUnterricht" class="kurs-item">
				<div class="one-line">
					<img src="../assets/pictures/mull.png" alt="" />
					<h2>Unterricht löschen</h2>
				</div>
			</div>
			<div class="kurs-item">
				<div class="one-line">
					<img src="../assets/pictures/users.png" alt="" />
					<h2>Mitglieder</h2>
				</div>
			</div>
			<div class="kurs-item">
				<div class="one-line">
					<img src="../assets/pictures/stats.png" alt="" />
					<h2>Statistiken</h2>
				</div>
			</div>
		</div>
	</div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";
import router from "@/router";
import { useRoute } from "vue-router";

const route = useRoute();
const kursId = route.params.kurs;

const userData = useUserStore();

const kurse = ref([]);

function deleteUnterricht() {
	router.push("/unterricht/delete/" + kursId);
}

function createUnterricht() {
	router.push("/unterricht/create/" + kursId);
}
</script>

<style scoped>
@import "../assets/shared_styles/chooser.css";
</style>
