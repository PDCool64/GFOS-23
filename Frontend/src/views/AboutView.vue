<script setup>
import Anwesenheitsliste from "@/components/Anwesenheitsliste.vue";
import { useUserStore } from "@/stores/user";

const userData = useUserStore();

const fetch_data = async () => {
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
