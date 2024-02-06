<template>
	<nav class="navbar">
		<ul>
			<li><RouterLink to="/">Home</RouterLink></li>
			<li><RouterLink to="/profile">Profile</RouterLink></li>
			<li><RouterLink to="/stundenplan">Stundenplan</RouterLink></li>
			<li><button @click="logOut">Log out</button></li>
		</ul>
		<img
			src="../assets/pictures/unnamed.png"
			alt="Ende der Navbar"
			class="navbar-image"
			@click="clickImage()"
		/>
	</nav>
	<p></p>
</template>

<script setup>
import router from "@/router";
import { RouterLink } from "vue-router";
import { ref } from "vue";
import { useUserStore } from "@/stores/user";

const userData = useUserStore();

function clickImage() {
	router.push("/profile");
}

const logOut = () => {
	userData.reset();
	router.push("/login");
	showList.value = false;
};
</script>

<style scoped>
.navbar {
	background-image: linear-gradient(
		to right,
		var(--fivth-color),
		var(--third-color)
	);
	height: 6.25vp; 
	width: 100%; 
	position: fixed; 
	top: 0; 
	left: 0; 
	display: flex;
	align-items: center;
	justify-content: space-between; 
	padding: 12px 20px; 
	text-align: center; 
}

.navbar ul {
	list-style: none;
	display: flex;
	margin: 0; 
	padding: 0; 
}

.navbar ul li {
	flex: 1; 
	margin: 0 50px; 
	display: flex;
	align-items: center;
}

.navbar ul li a {
	text-decoration: none;
	color: var(--color-text); 
	font-weight: bold;
	cursor: pointer;
}

.main-content {
	margin-top: 12.5vh; 
	display: flex;
	align-items: center;
	justify-content: center;
	height: 87.5vh; 
}
.navbar-image {
	max-height: 100%; 
	width: 40px;
	height: 40px;
	cursor: pointer;
}

.navbar button {
	background-color: transparent;
	color: var(--color-text);
	border: none;
	border-radius: 5px;
	padding: 10px 20px;
	cursor: pointer;
	text-wrap: nowrap;
	font-weight: bold;
	font-size: var(--text-size);
}
</style>
