<template>
  <div>
    <Stammdaten class="main"/>
  </div>
</template>

<script>
import Stammdaten from '@/components/Stammdaten.vue';

export default {
  name: 'StammdatenView',
  components: {
    Stammdaten
  }
};
</script>

<style scoped>
.main {
  margin: 0 auto;
  width: 55%;
  max-width: 1024px;
}
</style>
