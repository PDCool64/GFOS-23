<template>
  <div class="table-container">
    <table>
      <thead>
        <tr>
          <th>Zeit</th>
          <th>Montag</th>
          <th>Dienstag</th>
          <th>Mittwoch</th>
          <th>Donnerstag</th>
          <th>Freitag</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td class="time-cell">08:00 - 09:00</td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
        </tr>
        <tr>
          <td class="time-cell">09:00 - 10:00</td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
        </tr>
        <tr>
          <td class="time-cell">10:00 - 11:00</td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
        </tr>
        <tr>
          <td class="time-cell">12:00 - 13:00</td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
        </tr>
        <tr>
          <td class="time-cell">13:00 - 14:00</td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
        </tr>
        <tr>
          <td class="time-cell">14:00 - 15:00</td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
        </tr>
        <tr>
          <td class="time-cell">15:00 - 16:00</td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
        </tr>
        <tr>
          <td class="time-cell">16:00 - 17:00</td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
          <td class="data-cell"></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>

</script>

<style scoped>


.table-container {
  margin: 3cm;
  overflow-y: auto;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.9), 0 0 10px rgba(0, 0, 0, 0.9); /* Schatten rechts und unten */
  overflow: hidden;
  width: calc(100% - 6cm); /* 100% Breite minus doppelter Rand des äußeren Containers */
}

table {
  width: 100%;
  border-collapse: collapse;
  border-radius: 10px; /* Runde Ecken hinzufügen */
  overflow: hidden; /* Verhindern von Überlauf aus abgerundeten Ecken */
  width: 100%;
}

th, td {
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid #77aca7;
}

th {
  background-color: #77aca7;
  color: #333;
}

tr:nth-child(even) {
  background-color: transparent;
}
</style>
