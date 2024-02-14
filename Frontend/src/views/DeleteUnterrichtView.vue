<template>

    <div class="container">
        <h1>Unterricht löschen</h1>
        <form @submit.prevent="deleteUnterricht">
            <button type="submit">Löschen</button>
        </form>
    </div>

</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";
import { useRoute } from "vue-router";

const route = useRoute();
const kursId = route.params.kurs;

const userData = useUserStore();

</script>

<style scoped>


</style>