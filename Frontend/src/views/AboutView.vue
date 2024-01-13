<script setup>
  import Login from '@/components/Login.vue'
</script>

<template>
  <div class="about">
    <Login />
  </div>
</template>

<style>
@media (min-width: 1024px) {
  .about {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }
}
</style>
