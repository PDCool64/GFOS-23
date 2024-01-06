<script setup>
import { ref } from 'vue';

const vorname = ref('');
const nachname = ref('');
const email = ref('');
const password = ref('');
const geburtstag = ref('');

const submitForm = async () => {

    console.log({
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            vorname: vorname.value,
            name: nachname.value,
            email: email.value,
            password: password.value,
            geburtsdatum: geburtstag.value
        })
    });

    const response = await fetch('http://localhost:8080/Backend/resources/account', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            vorname: vorname.value,
            name: nachname.value,
            email: email.value,
            password: password.value,
            geburtsdatum: geburtstag.value
        })
    });

    const data = await response.json();
    console.log(data);
};
</script>

<template>
    <div class="registration form">
        <h1>Registrierung</h1>
        <form @submit.prevent="submitForm">
            <input v-model="vorname" type="text" id="vorname" name="vorname" placeholder="Vorname" required>
            <input v-model="nachname" type="text" id="nachname" name="nachname" placeholder="Nachname" required>
            <input v-model="email" type="email" id="email" name="email" placeholder="Email" required>
            <input v-model="password" type="password" id="password" name="password" placeholder="Password" required>
            <input v-model="geburtstag" type="date" id="geburtstag" name="geburtstag" placeholder="Geburtsdatum" required>
            <button type="submit">Register</button>
            Already have an account? <RouterLink to="/login">Login</RouterLink>
        </form>
    </div>
</template>

<style scoped>
    @import '../assets/shared_styles/form.css'
</style>