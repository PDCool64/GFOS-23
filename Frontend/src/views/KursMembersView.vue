<template>
	{{ kursId }}
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";
import { useRoute } from "vue-router";

const route = useRoute();
const userData = useUserStore();

const kursId = route.params.kurs;

const getTeilnahmen = async () => {
	const response = await fetch("http://localhost:8080/Backend/teilnahmen", {
		method: "GET",
		headers: {
			"Content-Type": "application/json",
			Authorization: userData.token,
		},
	});
	if (!response.ok) {
		console.log("Error");
		return;
	}
	const data = await response.json();
	console.log(data);
};

getTeilnahmen();
</script>

<style scoped></style>
