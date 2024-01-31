<script setup>
</script>

<template>
    <div class="registration">
        <h1>Registration</h1>
        <form action="/register" method="post">
            <input type="text" id="vorname" name="vorname" placeholder="Vorname" required>
            <input type="text" id="nachname" name="nachname" placeholder="Nachname" required>
            <input type="email" id="email" name="email" placeholder="Email" required>
            <input type="password" id="password" name="password" placeholder="Password" required>
            <input type="date" id="geburtstag" name="geburtstag" placeholder="Geburtsdatum" required>
            <button type="submit">Register</button>
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
    }

    .registration button {
        width: 100%;
        padding: 10px;
        background-color: #007BFF;
        color: white;
        border: none;
    }
</style>