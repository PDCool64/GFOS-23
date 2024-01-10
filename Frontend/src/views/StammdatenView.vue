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
  <div>
    <Stammdaten class="main"/>
  </div>
</template>

<style scoped>
.main {
}
</style>
