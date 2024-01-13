<script setup>
import Stammdaten from "@/components/Stammdaten.vue";
import CheckInCode from "@/components/CheckInCode.vue";
</script>

<template>
	<main>
		<Stammdaten class="profile" />
		<CheckInCode class="check-in-code" />
	</main>
</template>

<style scoped>
main {
	display: grid;
	height: 100vh;
	width: 100vw;
	align-items: center;
	justify-items: start;
	grid-template-columns: 1fr auto 1fr;
}
</style>
