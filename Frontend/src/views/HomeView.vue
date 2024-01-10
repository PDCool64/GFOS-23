<script setup>
import Stammdaten from '@/components/Stammdaten.vue';
</script>

<template>
	<main>
    <Stammdaten class="profile"/>
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