<template>
    <div class="login">
        <h1>Login</h1>
        <form action="/auth" method="post">
            <input type="text" id="email" name="email" placeholder="Email" required>
            <input type="password" id="password" name="password" placeholder="Password" required>
            <button type="submit">Login</button>
        </form>
        <p>Don't have an account? <RouterLink to="/register">Register</RouterLink></p>
    </div>
</template>

<style scoped>
    .login {
        width: 300px;
        margin: 0 auto;
        padding: 20px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }

    .login h1 {
        text-align: center;
        margin-bottom: 20px;
    }

    .login input {
        width: 100%;
        padding: 10px;
        margin-bottom: 10px;
        box-sizing: border-box;
    }

    .login button {
        width: 100%;
        padding: 10px;
        background-color: #007BFF;
        color: white;
        border: none;
    }

    .login p {
        text-align: center;
    }
</style>