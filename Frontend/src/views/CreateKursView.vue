<template>
    <div class="wrapper">
        <div class="registration form">
            <h1>Erstelle neuen Kurs</h1>
            <form @submit.prevent="submitForm">
                <input
                    v-model="kursname"
                    type="text"
                    id="kursname"
                    name="kursname"
                    placeholder="Kursname"
                    required
                />
                <input
                    v-model="kursbeschreibung"
                    type="text"
                    id="kursbeschreibung"
                    name="kursbeschreibung"
                    placeholder="Kursbeschreibung"
                    required
                />
                <input
                    v-model="kursstart"
                    type="time"
                    id="kursstart"
                    name="kursstart"
                    placeholder="Kursstart"
                    required
                />
                <input
                    v-model="kursende"
                    type="time"
                    id="kursende"
                    name="kursende"
                    placeholder="Kursende"
                    required
                />
                <button type="submit">Erstellen</button>
            </form>
        </div>
    </div>
</template>

<script setup>
import { ref } from "vue";

const kursname = ref("");
const kursbeschreibung = ref("");
const kursstart = ref("");
const kursende = ref("");

const submitForm = () => {
    
};

</script>

<style scoped>
@import "../assets/shared_styles/form.css";
input {
    padding: auto;
}
.wrapper {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    width: 100%;
}
</style>
