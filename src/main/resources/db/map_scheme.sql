CREATE TABLE State_Hood (
    id INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    image VARCHAR(200),
    color VARCHAR(200) NOT NULL
);

CREATE TABLE Super_State_Hood (
    id INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    image VARCHAR(200),
    color VARCHAR(200) NOT NULL
);

CREATE TABLE Star_System (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    statehood_id INTEGER,
    super_statehood_id INTEGER,
    system_importance INTEGER,
    coordinateX DOUBLE NOT NULL,
    coordinateY DOUBLE NOT NULL
);