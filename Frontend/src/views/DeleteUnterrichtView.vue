<template>
	<div class="wrapper">
		<h1>Unterricht löschen</h1>
		<form @submit.prevent="deleteUnterricht">
			<button type="submit">Löschen</button>
		</form>
	</div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";
import { useRoute } from "vue-router";

const route = useRoute();
const kursId = route.params.kurs;

const userData = useUserStore();
</script>

<style scoped>
@import "../assets/shared_styles/chooser.css";

.wrapper {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	height: 100%;
	width: 100%;
}


button {
    margin-top: 20px;
}
</style>
