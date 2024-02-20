<script setup>
import { useRouter } from 'vue-router';
import { setIsLeiter } from '@/requests/account';

import Stammdaten from '@/components/Stammdaten.vue';

const router = useRouter();
setIsLeiter();

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
