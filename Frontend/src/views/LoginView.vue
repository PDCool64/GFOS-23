<script setup>
  import Login from '@/components/Login.vue'
</script>

<template>
  <div class="wrapper">
    <Login />
  </div>
</template>

<style>
@media (min-width: 1024px) {
  .wrapper {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }
}
</style>
