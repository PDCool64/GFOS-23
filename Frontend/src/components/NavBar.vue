<template>
    <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<nav>
		<ul class="große-liste">
			<li class="große-liste"><router-link to="/">Home</router-link></li>
      <li class="große-liste"><router-link to="/profile">Stammdaten</router-link></li>
			<li class="große-liste"><router-link to="/about">About</router-link></li>
			<li class="große-liste"><router-link to="/contact">Contact</router-link></li>	
      </ul>
			<div class="klickbarer-bereich" @mouseenter="zeigeListe" @mouseleave="versteckeListe">
				<img src="./unnamed.png" alt="Bild kann nicht geladen werden" style="width: 40px; height: 40px;">
          	<ul v-if="listeSichtbar" class="kleine-liste">
            	<li><router-link to="/"> Home</router-link></li>
              <li><router-link to="/profile"> Profile</router-link></li>
              <li><router-link to="/contact"> Contact</router-link></li>
          	</ul>
          </div>
        </nav>
</template>

<script>

export default {
  data() {
    return {
      listeSichtbar: false,
    };
  },
  methods: {
    zeigeListe() {
      this.listeSichtbar = true;
    },
    versteckeListe() {
      this.listeSichtbar = false;
    },
  },
};
</script>

<style scoped>
nav {
	background-image: url('blauer Hintergrund.jpg');
  background-size: 100% 100%;
	padding: 10px;
	color: #6B8C83;
	display: flex;
	justify-content: center;
  box-shadow: 0px 10px 10px rgba(0, 0, 0, 0.8);
}
.große-liste
 {
	list-style-type: none;
	margin: 0;
	padding: 0;
	display: flex;
  width: 100%;
	height: 4vh;
	display: flex;
  justify-content: center;
	align-items: center;
}

 .große-liste li {
	margin-right: 10px;
  flex-grow: 1;
  text-align: center;
  display: inline-block; /* Listeneinträge nebeneinander */
  margin-right: 20px; /* Abstand zwischen den Listeneinträgen */
  color: white;
  position: relative;
}
.große-liste li::after {
      content: '';
      position: absolute;
      right: 0;
      top: 50%; /* Zentriere den Strich vertikal */
      width: 1px; /* Breite des Strichs */
      height: 30px; /* Höhe des Strichs */
      background-color: rgb(0, 0, 0); /* Farbe des Strichs */
      transform: translateY(-50%); /* Korrigiere die Positionierung */
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
  padding: 0;
  margin: 0;
}

.kleine-liste {
  background-color: #f2f2f2; /* Hintergrundfarbe in Grauton */
  border: 1px solid #ccc;
  border-radius: 5px;
  position: absolute;
  top: 10%;
  left: 97%; 
  transform: translateX(-50%);
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
  box-sizing: border-box;
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

</style>

