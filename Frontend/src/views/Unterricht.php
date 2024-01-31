<?php
// Verbindung zur Datenbank herstellen
$servername = "localhost";
$username = "deinBenutzername";
$password = "deinPasswort";
$dbname = "deineDatenbank";

// Verbindung herstellen
$conn = new mysqli($servername, $username, $password, $dbname);

// Überprüfen der Verbindung
if ($conn->connect_error) {
    die("Verbindung fehlgeschlagen: " . $conn->connect_error);
}

// SQL-Abfrage, um Unterricht um 8:00 Uhr abzurufen
$sql = "SELECT * FROM Unterricht WHERE startzeit = '2024-01-27 08:00:00'";

$result = $conn->query($sql);

// Überprüfen, ob Datensätze vorhanden sind
if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        // Hier kannst du mit den Daten arbeiten
        echo "Unterrichtsname: " . $row["unterrichtsname"] . "<br>";
        echo "Lehrer: " . $row["lehrer"] . "<br>";
        // ... weitere Datenfelder
    }
} else {
    echo "Keine Ergebnisse gefunden.";
}

// Verbindung schließen
$conn->close();
?>