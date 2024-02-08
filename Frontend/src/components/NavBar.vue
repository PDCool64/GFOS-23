<template>
	<nav class="navbar">
		<ul>
			<li><RouterLink to="/">Home</RouterLink></li>
			<li><RouterLink to="/profile">Profile</RouterLink></li>
			<li><RouterLink to="/stundenplan">Stundenplan</RouterLink></li>
		</ul>
		<img
			src="../assets/pictures/unnamed.png"
			alt="Ende der Navbar"
			class="navbar-image"
			@click="toggleTable"
		/>
	</nav>
	<ul v-show="showList" class="info-list">
		<li><a href="/">Home</a></li>
		<li><a href="/profile">Profile</a></li>
		<li><button @click="logOut">Log out</button></li>
	</ul>
</template>

<script setup>
import router from "@/router";
import { RouterLink } from "vue-router";
import { ref } from "vue";
import { useUserStore } from "@/stores/user";

const userData = useUserStore();

const showList = ref(false);

const toggleTable = () => {
	showList.value = !showList.value;
};

const logOut = () => {
	userData.reset();
	router.push("/login");
	showList.value = false;
};
</script>

<style scoped>
.navbar {
	background-color: var(--fivth-color); 
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

.info-list {
	position: fixed; 
	top: calc(-0.1vw + 64.4px); 
	right: 0; 
	left: auto; 
	bottom: 0; 
	width: 10%; 
	min-height: 100; 
	background-color: var(--fivth-color); 
	padding: 10px; 
	display: block; 
	list-style: none; 
}

.info-list li a {
	color: var(--color-text);
	font-weight: bold;
}

.info-list li button {
	color: var(--color-text);
	font-weight: bold;
	background-color: transparent;
	font-size: var(--text-size);
	border: none;
	padding: 10px;
	cursor: pointer;
}

.info-list li {
	margin-bottom: 50px; 
	text-align: center; 
}
</style>
