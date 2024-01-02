CREATE TABLE Account(
    name VARCHAR(50),
    vorname VARCHAR(50),
    personalnummer INT UNIQUE,
    geburtsdatum DATE,
    wochenstunden INT,
    email VARCHAR(255) UNIQUE,
    passwortHash VARCHAR(255) UNIQUE,
    isAdmin BOOLEAN,
    token VARCHAR(64),
    timestampLetzteAktion TIMESTAMP,
    id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)
);
CREATE TABLE Meldung(
    beginTimestamp TIMESTAMP,
    endTimestamp TIMESTAMP,
    grund VARCHAR(255),
    kommentar VARCHAR(255),
    id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    account INT NOT NULL,
    FOREIGN KEY(account) REFERENCES Account(id) ON DELETE CASCADE
);
CREATE TABLE Kurs(
    bezeichnung VARCHAR(20),
    checkinCode VARCHAR(5),
    id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    leiter INT NOT NULL,
    FOREIGN KEY(leiter) REFERENCES Account(id) ON DELETE CASCADE
);
CREATE TABLE Unterricht(
    beginzeit TIME,
    endzeit TIME,
    wochentag VARCHAR(10),
    id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    kurs INT NOT NULL,
    FOREIGN KEY(kurs) REFERENCES Account(id) ON DELETE CASCADE
);
CREATE TABLE Teilnahme(
    account INT NOT NULL,
    kurs INT NOT NULL,
    PRIMARY KEY(account, kurs),
    FOREIGN KEY(account) REFERENCES Account(id) ON DELETE CASCADE,
    FOREIGN KEY(kurs) REFERENCES Kurs(id) ON DELETE CASCADE
);