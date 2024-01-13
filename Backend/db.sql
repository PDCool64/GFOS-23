CREATE TABLE Account (
    id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
    email VARCHAR(255) NOT NULL,
    vorname VARCHAR(64) NOT NULL,
    name VARCHAR(64) NOT NULL,
    geburtsdatum DATE NOT NULL,
    passwortHash VARCHAR(128) NOT NULL, 
    isAdmin BOOLEAN NOT NULL DEFAULT false 
)

CREATE TABLE Kurs (
    id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
    fach VARCHAR(64) NOT NULL,
    name VARCHAR(64) NOT NULL,
    stufe INT NOT NULL, 
    leiterID INT,     
    FOREIGN KEY (leiterID) REFERENCES Account(id)
)

CREATE TABLE KursTeilnahme (
    accountID INT NOT NULL,
    kursID INT NOT NULL,
    PRIMARY KEY (accountID, kursID),
    FOREIGN KEY (accountID) REFERENCES Account(id),
    FOREIGN KEY (kursID) REFERENCES Kurs(id)
)

CREATE TABLE Unterricht (
    id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
    kursID INT NOT NULL,
    beginZeit VARCHAR(5) NOT NULL,
    endZeit VARCHAR(5) NOT NULL,
    FOREIGN KEY (kursID) REFERENCES Kurs(id)
)

CREATE TABLE Stunde (
    id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
    unterrichtID INT NOT NULL,
    beginTimestamp TIMESTAMP NOT NULL,
    endTimestamp TIMESTAMP NOT NULL,
    checkInCode VARCHAR(6) NOT NULL,
    FOREIGN KEY (unterrichtID) REFERENCES Unterricht(id)
)

CREATE TABLE StundeTeilnahme (
    accountID INT NOT NULL,
    stundeID INT NOT NULL,
    anwesend BOOLEAN NOT NULL DEFAULT false,
    note INT,
    PRIMARY KEY (accountID, stundeID),
    FOREIGN KEY (accountID) REFERENCES Account(id),
    FOREIGN KEY (stundeID) REFERENCES Stunde(id)
)

CREATE TABLE StundeLeitung (
    accountID INT NOT NULL,
    stundeID INT NOT NULL,
    leitungsBewertung INT NOT NULL,
    PRIMARY KEY (accountID, stundeID),
    FOREIGN KEY (accountID) REFERENCES Account(id),
    FOREIGN KEY (stundeID) REFERENCES Stunde(id)
)