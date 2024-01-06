import socket

# Erstelle einen Socket
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Definiere den Host und den Port
host = 'localhost'
port = 6609

# Binde den Socket an den Host und den Port
s.bind((host, port))

# Höre auf eingehende Verbindungen
s.listen(1)
print(f"Server is listening on {host}:{port}")

while True:
    # Akzeptiere eine Verbindung
    client, addr = s.accept()
    print(f"Connected by {addr}")

    while True:
        # Empfange Daten vom Client
        data = client.recv(1024)

        if not data:
            break

        # Gib die empfangenen Daten aus
        print(f"Received data: {data.decode('utf-8')}")

    # Schließe die Verbindung
    client.close()