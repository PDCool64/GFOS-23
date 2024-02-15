<script setup>
import Stammdaten from "@/components/Stammdaten.vue";
import CheckInCode from "@/components/CheckInCode.vue";
import { useUserStore } from "@/stores/user";

const userData = useUserStore();
</script>

<template>
	<main>
		<h1 class="header">Hallo, {{ userData.user.vorname }} {{ userData.user.name }}!</h1>
		<CheckInCode class="check-in-code" />
		<div></div>
	</main>
</template>

<style scoped>
main {
	display: grid;
	height: calc(100vh - var(--navbar-margin) * 2);
	width: 100vw;
	align-items: center;
	justify-items: center;
	grid-template-rows: 1fr 1fr 1fr;
}

</style>
