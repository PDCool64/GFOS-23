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

if(!isLoggedIn.value){
  router.push("/login");
  console.log(userData.token);
}

</script>

<template>
	<div class="app">
		<NavBar v-if="isLoggedIn"/>
		<RouterView />
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
  color: #fff;
  border-radius: 10px;
}

.view{
  width: 100vw;
  height: calc(100vh - var(--navbar-height));
  overflow: hidden;
}

</style>
