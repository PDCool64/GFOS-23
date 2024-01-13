<template>
	<nav>
		<ul>
			<li><router-link to="/">Home</router-link></li>
            <li><router-link to="/profile">Stammdaten</router-link></li>
			<li><router-link to="/about">About</router-link></li>
			<li><router-link to="/contact">Contact</router-link></li>
		</ul>
	</nav>
</template>

<script setup></script>

<style scoped>
nav {
	background-color: #333;
	color: #fff;
	padding: 10px;
	display: flex;
	justify-content: center;
}

ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	display: flex;
    width: 100%;
}

li {
	margin-right: 10px;
    flex-grow: 1;
    text-align: center;
}

a {
	color: #4caf50;
    text-decoration: none;
    display: block;
    width: 100%;
    height: 100%;
    border-radius: 5px;
}

</style>

