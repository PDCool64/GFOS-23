<template>
  <div class="container">
    <div class="header">
      <h1>Bsp: Mathe LK 1</h1>
    </div>
    <div class="custom-table-text-container">
      <div class="custom-table-container">
        <table class="custom-table">
          <tr>
            <th style="width: 500px;">Name</th>
            <th style="width: 100px;">Note</th>
            <th style="width: 300px;">Anwesend</th>
          </tr>
          <tr>
            <td>Max Mustermann</td>
            <td>1-</td>
            <td>
              <img
			        src="../assets/pictures/richtig.png"
			        alt="Ende der Navbar"
			        class="anwesend-image"
              @click="changeImageBackground"
		          />
              <img
			        src="../assets/pictures/falsch.png"
			        alt="Ende der Navbar"
			        class="anwesend-image"
              @click="changeImageBackground"
		          />
            </td>
          </tr>
          <tr>
            <td>Ben Neßler</td>
            <td>1+</td>
            <td>Ja</td>
          </tr>
          <tr>
            <td>Phillip Trutz</td>
            <td>6</td>
            <td>ja</td>
          </tr>
          <tr>
            <td>Phillip Trutz</td>
            <td>6</td>
            <td>ja</td>
          </tr>
        </table>
      </div>
      <div class="text-container">
        <p>Thema:</p>
        <p>Zeit:</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      backgroundColor: 'white',
      imageBackgroundColor: 'transparent'
    };
  },
  methods: {
    changeImageBackground() {
      this.imageBackgroundColor = 'lightblue'; // Ändern Sie hier die gewünschte Hintergrundfarbe des Bildcontainers
    }
  }
}
</script>

<style scoped>
.container {
  height: 100vh;
}

.header {
  text-align: center;
  color: var(--black);
  margin-top: 10vh; /* Abstand nach oben von 20% der Bildschirmhöhe */
}

.header h1 {
  margin: 0;
  background-color: var(--first-color);
  border-radius: 10px;
}

.custom-table-container{
  margin-right: 20px;
}

.custom-table {
  margin-top: 2vh; /* Abstand nach oben von 20% der Bildschirmhöhe */
  border-collapse: collapse;
  width: 100%;
  border-radius: 10px;
  background-color: var(--fourth-color);
}

.custom-table th {
  background-color: var(--second-color);
  color: var(--black);
  font-weight: bold;
}

.custom-table th,
.custom-table td {
  padding: 15px;
  text-align: center;
  border: 2.5px solid #000000;
}

.custom-table th:first-child,
.custom-table td:first-child {
  border-left: 0;
}

.custom-table th:last-child,
.custom-table td:last-child {
  border-right: 0;
}

.text-container {
  padding: 10px;
  margin-top: 2vh; /* Abstand nach oben von 20% der Bildschirmhöhe */
  width: 200px; /* Breite des Textcontainers */
  margin-left: 0px; /* Hier können Sie den gewünschten Abstand einstellen */
  background-color: var(--fourth-color);
  border-radius: 10px;
}
.custom-table-text-container{
  display: flex;
}

.anwesend-image{
  height: 30px;
  width: 30px;
  background-color: var(--second-color);
  border-radius: 5px;
  margin-right: 10px;
}

</style>
