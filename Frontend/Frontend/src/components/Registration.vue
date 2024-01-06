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
    <div class="registration">
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
    .registration {
        width: 300px;
        margin: 0 auto;
        padding: 20px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }

    .registration h1 {
        text-align: center;
        margin-bottom: 20px;
    }

    .registration input {
        width: 100%;
        padding: 10px;
        margin-bottom: 10px;
        box-sizing: border-box;
        border-radius: 5px;
        border-left: 5px solid #ABCCC3;
        border-right: 5px solid #ABCCC3;
    }

    .registration button {
        width: 100%;
        padding: 10px;
        background-color: #6B8C83;
        color: white;
        border: none;
        border-radius: 5px;
    }
    .registration button:active {
        animation: button_pressed 0.5s;
    }

    @keyframes button_pressed {
        0% {
            transform: scale(1);
            background-color: #6B8C83;
        }
        50% {
            transform: scale(0.98);
            background-color: #0068e4;
        }
        100% {
            transform: scale(1);
        } 
    }
</style>