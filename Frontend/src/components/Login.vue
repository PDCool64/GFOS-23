<script setup>

import { ref } from 'vue';
    const email = ref('');
    const password = ref('');

    const submitForm = async () => {
        const response = await fetch('http://localhost:8080/Backend/login/' + email.value, {
            method: 'POST',
            headers: {
                'password': password.value,
            },
        });

        if (response.status === 401) {
            errorMessage.value = "Die Email oder das Passwort ist falsch.";
            error.value = true;
        } else {
            const data = await response.json();
            console.log(data);
            errorMessage.value = "Registrierung erfolgreich.";
            error.value = false;
        }
    };
</script>

<template>
    <div class="login form">
        <h1>Login</h1>
        <form action="localhost:8080/Backend/login" method="post" @submit.prevent="submitForm">
            <input v-model="email" type="text" id="email" name="email" placeholder="Email" required>
            <input v-model="password" type="password" id="password" name="password" placeholder="Password" required>
            <button type="submit">Login</button>
        </form>
        <p>Don't have an account? <RouterLink to="/registration">Register</RouterLink></p>
    </div>
</template>

<style scoped>
    @import '../assets/shared_styles/form.css';
</style>