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
  width: 100%;
  height: 100%;
  overflow: hidden;
}

.navbar{
  position: fixed;
  top: 0;
  left: 0;
  padding: auto;
  height: var(--navbar-height);
  width: calc(100% - 2*var(--navbar-margin));
  margin:  var(--navbar-margin);
  background-color: #333;
  color: #fff;
  border-radius: 10px;
}

.view{
  width: 100vw;
  height: calc(100vh - var(--navbar-height));
  overflow: hidden;
}

</style>
