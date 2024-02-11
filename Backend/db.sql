CREATE TABLE Account (
    id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
    email VARCHAR(255) NOT NULL,
    vorname VARCHAR(64) NOT NULL,
    name VARCHAR(64) NOT NULL,
    geburtsdatum DATE NOT NULL,
    passwortHash VARCHAR(128),
    isAdmin BOOLEAN
);

CREATE TABLE Kurs (
    id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
    fach VARCHAR(64) NOT NULL,
    stufe INT,
    nummer INT,
    art VARCHAR(64),
    leiter INT,
    FOREIGN KEY (leiter) REFERENCES Account(id)
);

CREATE TABLE KursTeilnahme (
    id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
    account INT NOT NULL,
    kurs INT NOT NULL,
    FOREIGN KEY (account) REFERENCES Account(id),
    FOREIGN KEY (kurs) REFERENCES Kurs(id)
);

CREATE TABLE Unterricht (
    id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
    kurs INT NOT NULL,
    beginStunde INT NOT NULL,
    endStunde INT NOT NULL,
    tag INT NOT NULL,
    FOREIGN KEY (kurs) REFERENCES Kurs(id)
);

CREATE TABLE Stunde (
    id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
    unterricht INT NOT NULL,
    datum DATE NOT NULL,
    checkInCode VARCHAR(6) NOT NULL,
    FOREIGN KEY (unterricht) REFERENCES Unterricht(id)
);

CREATE TABLE StundeBewertung (
    id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
    account INT NOT NULL,
    stunde INT NOT NULL,
    note INT,
    kommentar VARCHAR(255),
    FOREIGN KEY (account) REFERENCES Account(id),
    FOREIGN KEY (stunde) REFERENCES Stunde(id)
);

CREATE TABLE StundeTeilnahme (
    id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
    account INT NOT NULL,
    stunde INT NOT NULL,
    anwesend BOOLEAN,
    beginTimestamp TIMESTAMP NOT NULL,
    endTimestamp TIMESTAMP,
    note INT,
    FOREIGN KEY (account) REFERENCES Account(id),
    FOREIGN KEY (stunde) REFERENCES Stunde(id)
);

INSERT INTO
    APP.ACCOUNT (
        EMAIL,
        VORNAME,
        "NAME",
        GEBURTSDATUM,
        PASSWORTHASH,
        ISADMIN
    )
VALUES
    (
        'philippdoering64@web.de',
        'Philipp',
        'Doering',
        '2006-11-19',
        'fc6dbf222444e02b369a35fa03a845b1e1805425e8f80ece4bf60739e2aeeb15c425632bac4174361bb4a60351851ed539cdc807bdefd12fb5e99cf788d94f75',
        true
    );

INSERT INTO
    APP.KURS (FACH, STUFE, NUMMER, ART, LEITER)
VALUES
    ('Mathe', 11, 1, 'LK', 0);

INSERT INTO
    KursTeilnahme (account, kurs)
VALUES
    (0, 0);

INSERT INTO
    App.Unterricht (
        kurs,
        beginStunde,
        endStunde,
        tag
    )
VALUES
    (
        0,
        1,
        1,
        0
    );

INSERT INTO
    APP.STUNDE (UNTERRICHT, DATUM, CHECKINCODE)
VALUES
    (0, '2022-01-01', '12345');

INSERT INTO
    APP.STUNDETEILNAHME (
        ACCOUNT,
        STUNDE,
        ANWESEND,
        BEGINTIMESTAMP,
        ENDTIMESTAMP,
        NOTE
    )
VALUES
    (
        0,
        0,
        true,
        '2022-01-01 07:57:01.0',
        '2024-02-09 08:40:00.0',
        13
    );

INSERT INTO
    APP.STUNDEBEWERTUNG (ACCOUNT, STUNDE, NOTE, KOMMENTAR)
VALUES
    (0, 0, 13, 'Guter Unterricht')

INSERT INTO 
    APP.UNTERRICHT (
        KURS,
        BEGINSTUNDE,
        ENDSTUNDE,
        TAG
    )
VALUES
    (
        0,
        2,
        2,
        0
    );