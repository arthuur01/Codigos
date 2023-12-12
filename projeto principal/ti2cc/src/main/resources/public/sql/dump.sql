/* Lógico 1.2: */


CREATE TABLE Pessoa (
    Id SERIAL PRIMARY KEY,
    Login VARCHAR(50) UNIQUE,
    Email VARCHAR(50),
    Password VARCHAR(50),
    Status BOOLEAN
);
