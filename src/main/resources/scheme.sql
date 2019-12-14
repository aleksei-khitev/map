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
    id INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    statehood_id INTEGER,
    super_statehood_id INTEGER,
    system_importance INTEGER,
    coordinateX DOUBLE NOT NULL,
    coordinateY DOUBLE NOT NULL
);

CREATE TABLE Weapon (
    id INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    damage INTEGER NOT NULL,
    range VARCHAR(200) NOT NULL
);

CREATE TABLE Ship (
    id INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    length DOUBLE NOT NULL,
    crew INTEGER NOT NULL,
    minimal_crew INTEGER,
    troops INTEGER,
    cost DOUBLE,
    image VARCHAR(200) NOT NULL,
    link VARCHAR(200) NOT NULL
);

CREATE TABLE Ship_weapon_mapping (
    id INTEGER NOT NULL PRIMARY KEY,
    ship_id INTEGER NOT NULL,
    weapon_id INTEGER NOT NULL,
    weapon_count INTEGER NOT NULL
);

CREATE TABLE Fleet_node (
    id INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    parent_node_id INTEGER NOT NULL,
    minimum_commander_rank VARCHAR(200) NOT NULL,
    commander_officer  VARCHAR(200)
)

CREATE TABLE Fleet_node_ship_mapping (
   id INTEGER NOT NULL PRIMARY KEY,
   fleet_node_id INTEGER NOT NULL,
   sheep_id INTEGER NOT NULL,
   sheep_count INTEGER NOT NULL
);

CREATE TABLE Fleet_node_node_mapping (
   id INTEGER NOT NULL PRIMARY KEY,
   fleet_node_id INTEGER NOT NULL,
   fleet_node_id INTEGER NOT NULL,
   sheep_count INTEGER NOT NULL
);