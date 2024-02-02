<template>
	<nav>
		<ul class="große-liste">
			<li class="große-liste"><router-link to="/">Home</router-link></li>
			<li class="große-liste">
				<router-link to="/stundenplan">Calendar</router-link>
			</li>
		</ul>
		<div
			class="klickbarer-bereich"
			@click="listeSichtbar = !listeSichtbar"
		>
			<img
				src="../assets/pictures/unnamed.png"
				alt="Bild kann nicht geladen werden"
				style="width: 40px; height: 40px"
			/>
			<ul v-if="listeSichtbar" class="kleine-liste">
				<li><router-link to="/"> Home</router-link></li>
				<li><router-link to="/profile"> Profile</router-link></li>
			</ul>
		</div>
	</nav>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue';

const listeSichtbar = ref(false);

const closeListe = (event) => {
  if (event.target.closest('.klickbarer-bereich')) return;
  listeSichtbar.value = false;
};

onMounted(() => {
  window.addEventListener('click', closeListe);
});

onUnmounted(() => {
  window.removeEventListener('click', closeListe);
});
</script>

<style scoped>
nav {
	background-image: url("../assets/pictures/blauer Hintergrund.jpg");
	background-size: 100% 100%;
	padding: 10px;
	color: #6b8c83;
	display: flex;
	justify-content: center;
	box-shadow: 0px 10px 10px var(--shadow);
	margin: 10px;
	width: 100%;
	align-items: center;
}
.große-liste {
	list-style-type: none;
	margin: 0;
	padding: 0;
	display: flex;
	width: 100%;
	height: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
}

.große-liste li {
	margin: 0 5px;
	flex-grow: 1;
	text-align: center;
	display: inline-block; /* Listeneinträge nebeneinander */
	margin-right: 20px; /* Abstand zwischen den Listeneinträgen */
	color: white;
	position: relative;
}
.große-liste li::after {
	content: "";
	position: absolute;
	right: 0;
	top: 50%; /* Move the top edge of the line to the vertical center of the list item */
	width: 1px; /* Width of the line */
	height: 80%; /* Height of the line */
	background-color: rgba(0, 0, 0, 0.5); /* Color of the line with 50% opacity */
	transform: translateY(-50%); /* Shift the line up by half its height */
}
a {
	color: #000000;
	text-decoration: none;
	display: block;
	width: 100%;
	height: 100%;
	border-radius: 5px;
}
.klickbarer-bereich {
	display: flex;
	justify-content: center;
	align-items: center;
	width: 50px;
	height: 38px;
	position: relative;
}

.kleine-liste {
	background-color: var(--drop-down-color); /* Hintergrundfarbe in Grauton */
	border: 1px solid #ccc;
	border-radius: 5px;
	position: absolute;
	top: 100%;
	box-shadow: 0 2px 5px var(--shadow); 
	box-sizing: border-box;
	padding: 10px;
}

.kleine-liste li:last-child {
	border-bottom: none; /* Entfernt die untere Grenze des letzten Elements */
}

.kleine-liste li:hover {
	background-color: #ddd; /* Hintergrundfarbe beim Hovern für bessere Sichtbarkeit */
}
.kleine-liste li {
	display: block;
	padding: 5px;
	color: #cfcfcf;
	text-align: center;
}

.klickbarer-bereich {
}
</style>
