<script setup>
import { useRouter } from 'vue-router';

import Stammdaten from '@/components/Stammdaten.vue';

const router = useRouter();

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
