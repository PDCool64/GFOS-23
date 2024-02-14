<template>
	<nav class="navbar">
		<ul>
			<li><RouterLink to="/">
				<img src="../assets/pictures/home.png" alt="Home" class="navbar-image" />
			<div class="bold">Home</div>
			</RouterLink></li>
			<li>
				<button @click="goToStundenplan">
					<img src="../assets/pictures/stundenplan.png" alt="Stundenplan" class="navbar-image" />
					<div class="bold">Stundenplan</div>
				</button>
			</li>
			<li v-if="userData.user.isLeiter">
				<RouterLink to="/kurs/choose">Kursverwaltung</RouterLink>
			</li>
			<li v-if="userData.user.isAdmin">
				<RouterLink to="/kurs/create">Kurs erstellen</RouterLink>
			</li>
		</ul>
		<div class="left">
			<img
				src="../assets/pictures/benutzer.png"
				alt="Ende der Navbar"
				class="navbar-image"
				@click="clickImage()"
			/>
			<img alt="Log out" src="../assets/pictures/exit.png" @click="logOut" class="navbar-image">
		</div>
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

const goToStundenplan = () => {
	const now = new Date();
	const day = now.getDay();
	const diff = now.getDate() - day + (day == 0 ? -6 : 1); // adjust when day is Sunday
	const thisWeekMonday = new Date(now.setDate(diff));
	router.push(
		"/stundenplan/" + thisWeekMonday.toISOString().substring(0, 10),
	);
};

const logOut = () => {
	userData.reset();
	router.push("/login");
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
	white-space: nowrap;
}

.navbar ul li a {
	text-decoration: none;
	color: var(--color-text);
	font-weight: bold;
	cursor: pointer;
	align-items: center;
	display: flex;
	flex-direction: row;
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
	margin-left: 15px;
	margin-right: 5px;
	padding: 5px;
	opacity: 0.64;
}

.left {
	display: flex;
	align-items: center;
}

.navbar button {
	background-color: transparent;
	color: var(--color-text);
	border: none;
	border-radius: 5px;
	padding: 3px;
	cursor: pointer;
	font-weight: bold;
	font-size: var(--text-size);
	align-items: center;
	display: flex;
	flex-direction: row;
}

.bold {
	font-weight: bold;
}
</style>
