<template>
    <div class="wrapper">
  <div class="unterricht">
     <button class="unterricht__close" @click="goToStundenplan">X</button>
    <h2 class="unterricht__title">{{ stundenData.date }}</h2>
    <div class="unterricht__card">
      <h3 class="unterricht__card-title">{{ content.kurs.fach }} ({{ content.kurs.art }}{{ content.kurs.nummer }})</h3>
      <p class="unterricht__card-text">Leiter: {{ content.kurs.leiter.name }}, {{ content.kurs.leiter.vorname }}</p>
      <p class="unterricht__card-text">Stufe: {{ content.kurs.stufe }}</p>
    </div>
  </div></div>
</template>
<script setup>

import { ref, onMounted, VueElement } from "vue";
import { useUserStore } from "@/stores/user";
import { useStundenStore } from "@/stores/stunden";
import { useRouter } from 'vue-router';

const userData = useUserStore();
const stundenData = useStundenStore();
const route = useRouter();
const day = ref(0);
const time = ref(0);

day.value = route.currentRoute.value.params.day;
time.value = route.currentRoute.value.params.time;

const content = ref('');
content.value = stundenData.stunden[day.value][time.value].unterricht;

const goToStundenplan = () => {
  route.push('/stundenplan/' + stundenData.date);
}

</script>

<style scoped>

.wrapper {
    min-height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
}

.unterricht {
  width: 100%;
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  position: relative;
}

.unterricht__title {
  margin-bottom: 20px;
  text-align: center;
}

.unterricht__card {
  padding: 20px;
  background-color: var(--fourth-color);
  border-radius: 10px;
}

.unterricht__card-title {
  margin-bottom: 10px;
  font-size: 1.5em;
  font-weight: bold;
}

.unterricht__card-text {
  margin-bottom: 10px;
}
.unterricht__close {
    position: absolute;
    top: 10px;
    right: 10px;
  background: none;
  border: none;
  font-size: 1.5em;
  cursor: pointer;

}
</style>