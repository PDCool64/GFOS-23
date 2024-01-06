<template>
    <div class="login form">
        <h1>Login</h1>
        <form action="/auth" method="post">
            <input type="text" id="email" name="email" placeholder="Email" required>
            <input type="password" id="password" name="password" placeholder="Password" required>
            <button type="submit">Login</button>
        </form>
        <p>Don't have an account? <RouterLink to="/registration">Register</RouterLink></p>
    </div>
</template>

<style scoped>
    @import '../assets/shared_styles/form.css'
</style>