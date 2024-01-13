<script setup>
import { RouterLink, RouterView } from "vue-router";
import NavBar from "./components/NavBar.vue";

let token = sessionStorage.getItem("token");

</script>

<template>
	<div class="app">
		<NavBar v-if="token != ''" class="navbar" />
		<RouterView class="view"/>
	</div>
</template>

<style scoped>
.app {
  margin-top: var(--navbar-height);
  width: 100vw;
  height: 100vh;
  overflow: hidden;
}

.navbar{
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: var(--navbar-height);
  background-color: #333;
  color: #fff;
}

.view{
  width: 100vw;
  height: calc(100vh - var(--navbar-height));
  overflow: hidden;
}

</style>
