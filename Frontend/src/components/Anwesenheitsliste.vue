<template>
  <div class ="container">
    <h1 class="überschrift">Ihre Überschrift hier</h1>
  </div>
</template>

<script>

</script>

<style scoped>

.container {
  height: 100vh;
  display: flex;
  justify-content: center; /* Überschrift mittig ausrichten */
  align-items: center; /* Zentrierung vertikal */
}

.überschrift {
  text-align: center;
  background-color: var(--first-color); /* Hintergrundfarbe */
  padding: 10px; /* Abstand um den Text */
  border-radius: 10px; /* Abgerundete Ecken */
}
</style>