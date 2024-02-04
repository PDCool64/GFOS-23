<template>
  <div>
    <nav class="navbar">
      <!-- Hier kommen die Inhalte deiner Navbar -->
      <ul>
        <li>
          <a href="/">Home</a></li>
        <li><a href="/profile">Profile</a></li>
        <li><a href="/about">About</a></li>
        <li><a href="/contact">Contact</a></li>
      </ul>
      <img src="../assets/pictures/unnamed.png" alt="Ende der Navbar" class="navbar-image" @click="toggleTable" />
    </nav>
    <ol v-show="showList" class="info-list">
        <li><a href="/">Home</a></li>
        <li><a href="/profile">Profile</a></li>
        <li><a href="/about">About</a></li>
        <li><a href="/contact">Contact</a></li>
      </ol>
      </div>
</template>

<script>
export default {
  data() {
    return {
      showList: false // Variable, um den Listenstatus zu verfolgen
    };
  },
  methods: {
    toggleTable() {
      this.showList = !this.showList; // Umkehrung des Listenstatus
    }
  }
};

</script>

<style scoped>
/* Hier kommt das Styling für deine Navbar */
.navbar {
  background-color: var(--first-color); /* Hintergrundfarbe deiner Navbar */
  height: 6.25vp; /* 1/8 der gesamten vertikalen Höhe */
  width: 100%; /* Volle Breite des Bildschirms */
  position: fixed; /* Fixierte Position am oberen Bildschirmrand */
  top: 0; /* Oben ausrichten */
  left: 0; /* Links ausrichten */
  display: flex;
  align-items: center;
  justify-content: space-between; /* Gleichmäßige Verteilung der Elemente */
  padding: 12px 20px; /* Innenabstand für die Inhalte der Navbar */
  text-align: center; /* Zentrierung des Inhalts innerhalb der Navbar */
}

/* Styling für die Navbar-Links */
.navbar ul {
  list-style: none;
  display: flex;
  margin: 0; /* Kein zusätzlicher Abstand für die Liste */
  padding: 0; /* Kein zusätzlicher Abstand für die Liste */
}

.navbar ul li {
  flex: 1; /* Gleichmäßige Verteilung der li-Elemente */
  margin: 0 50px; /* Abstand zwischen den Elementen */
  display: flex; 
  align-items: center;
}

.navbar ul li a {
  text-decoration: none;
  color: var(--black); /* Textfarbe der Navbar-Links */
  font-weight: bold;
}
/* Hier kannst du weiteres Styling für deine Seite hinzufügen */
.main-content {
  /* Hier kommt das Styling für den Hauptinhalt deiner Seite */
  margin-top: 12.5vh; /* Platz unterhalb der Navbar */
  display: flex;
  align-items: center;
  justify-content: center;
  height: 87.5vh; /* Der Rest der vertikalen Höhe */
}
.navbar-image {
  max-height: 100%; /* Maximale Höhe des Bildes */
  width: 40px;
  height: 40px;
  border: 2px solid black; /* Schwarze Umrandung */
  border-radius: 50%; /* Runde Ecken für das runde Bild */
  cursor: pointer;
}

.info-list {
  position: fixed; /* Fixierte Position am rechten Bildschirmrand */
  top: calc(-0.1vw + 64.4px); /* 10% der Bildschirmhöhe */
  right: 0; /* Rechts ausrichten */
  left: auto; /* Automatische Positionierung von links entfernen */
  bottom: 0; /* Bis zum unteren Rand ausrichten */
  width: 10%; /* 20% der Bildschirmbreite */
  min-height: 100; /* Volle Bildschirmhöhe */
  background-color: var(--second-color); /* Hintergrundfarbe der Liste */
  padding: 10px; /* Innenabstand für die Inhalte der Liste */
  display: block; /* Jetzt standardmäßig sichtbar (zum Testen) */
  list-style: none; /* Entferne die Standardpunktliste */
}

.info-list li a {
  color: var(--black);
  font-weight: bold;
}
.info-list li{
  margin-bottom: 50px; /* Abstand zwischen den Listeneinträgen */

}
</style>
