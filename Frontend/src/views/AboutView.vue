<script setup>
import Anwesenheitsliste from "@/components/Anwesenheitsliste.vue";
import { useUserStore } from "@/stores/user";

const userData = useUserStore();

const fetch_data = async () => {
	const response = await fetch(
		"http://localhost:8080/Backend/stunde/aktuell",
		{
			method: "GET",
			headers: {
				"Authorization": userData.token,
			},
		},
	);
	if (!response.ok) {
		console.log("Error fetching data");
	}
	else {
		const data = await response.json();
		console.log(data);
	}
};

fetch_data();
</script>

<template>
	<div class="about">
		<Anwesenheitsliste />
	</div>
</template>

<style scoped>
@media (min-width: 1024px) {
	.about {
		min-height: 100vh;
		display: flex;
		align-items: center;
	}
}
.about {
	min-height: 100vh;
	display: flex;
	justify-content: center;
}
</style>
