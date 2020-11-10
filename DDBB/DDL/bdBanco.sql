CREATE DATABASE bdBanco;

USE bdBanco;

CREATE TABLE Users
(
	 dni 		INT 		NOT NULL,
     firstName 	VARCHAR(45) NOT NULL,
     lastName 	VARCHAR(45) NOT NULL,
     userName	VARCHAR(45) NOT NULL,
     password	VARCHAR(45) NOT NULL,
     cuil		VARCHAR(11) NOT NULL,
     gender		VARCHAR(30) NOT NULL,
     nationality VARCHAR(35) NOT NULL,
     birthDate	DATETIME 	NOT NULL,
     address 	VARCHAR(50) NOT NULL,
     city		VARCHAR(45) 	NULL,
     email		VARCHAR(50) NOT NULL,
     status		BIT			NOT NULL,
     
	 PRIMARY KEY (dni)
);

CREATE TABLE Roles
(
	id		INT			AUTO_INCREMENT NOT NULL,
	name 	VARCHAR(20)					NOT NULL, /* Admin ó Cliente */
    status	BIT							NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Roles_x_Users
(
	dni		INT		NOT NULL,
	roleId 	INT		NOT NULL, 
    status	BIT		NOT NULL,
    
    PRIMARY KEY(dni, roleId),
    FOREIGN KEY(dni) REFERENCES Users(dni),
    FOREIGN KEY(roleId) REFERENCES Roles (id)
);

CREATE TABLE Phones
(
	numberPhone	INT 		NOT NULL,
    description VARCHAR(20) NOT NULL, 	/* teléfono, celular, fax */
    userDni		INT 		    NULL,
    status		BIT			NOT NULL,
     
	PRIMARY KEY (numberPhone),
    FOREIGN KEY(userDni) REFERENCES Users(dni)
);

CREATE TABLE Nationalities
(
	id			INT 	AUTO_INCREMENT	NOT NULL,
    country 	VARCHAR(30) 			NOT NULL, 
    gentilic	VARCHAR(40) 			NOT NULL,
    iso			VARCHAR(3)				NULL,
     
	PRIMARY KEY (id)
);

CREATE TABLE Provinces
(
	id 			INT 	AUTO_INCREMENT 	NOT NULL,
    description VARCHAR(50)				NOT NULL,
    PRIMARY KEY (id)	
);

CREATE TABLE Cities
(
	id 			INT 	AUTO_INCREMENT 	NOT NULL,
    description VARCHAR(45)				NOT NULL,
    postalCode	VARCHAR(4) 				NOT	NULL,
    provinceId	INT						NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (provinceId) REFERENCES Provinces (id)
);
SELECT * FROM Users
Select articulos.idArticulo, articulos.nombre, articulos.precio, articulos.estado, categorias.idCategoria, categorias.nombre , categorias.estado from articulos inner join categorias on articulos.idCategoria = categorias.idCategoria
SELECT u.dni, u.cuil, u.firstname, u.lastname, u.email, u.nationality, u.birthDate, u.gender FROM Users u