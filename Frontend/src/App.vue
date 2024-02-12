<script setup>
import { RouterLink, RouterView } from "vue-router";
import NavBar from "./components/NavBar.vue";
import { useUserStore } from "./stores/user";
import router from "./router";
import { computed } from "vue";

const userData = useUserStore();

const isLoggedIn = computed(() => {
	return userData.token != "";
});

if (!isLoggedIn.value) {
	router.push("/login");
	console.log(userData.token);
}
</script>

<template>
	<div class="app">
		<NavBar v-if="isLoggedIn" />
		<RouterView />
	</div>
</template>

<style scoped>
.app {
	width: 100%;
	height: 100vh;
	overflow: hidden;
	display: flex;
	flex-direction: column;
}
</style>
