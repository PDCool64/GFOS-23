<script setup>
import { useTokenStore } from '@/stores/token';
import { useRouter } from 'vue-router';

import Stammdaten from '@/components/Stammdaten.vue';

const router = useRouter();
const tokenStore = useTokenStore();
if (sessionStorage.getItem('token') === undefined) {
  router.push('/login');
  console.log("Token undefined");
}
</script>

<template>
  <div class="wrapper">
    <Stammdaten />
  </div>
</template>

<style scoped>
.wrapper {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
