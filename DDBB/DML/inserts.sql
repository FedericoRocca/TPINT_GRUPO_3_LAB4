USE bdbanco;

-- ROLES --
INSERT INTO roles (name, status) VALUES ("Administrador", 1);
INSERT INTO roles (name, status) VALUES ("Cliente", 1);

-- ROLES_X_USERS  --
INSERT INTO roles_x_users (dni, roleId, status) VALUES (99999999, 2, 1);
INSERT INTO roles_x_users (dni, roleId, status) VALUES (99999988, 2, 1);
INSERT INTO roles_x_users (dni, roleId, status) VALUES (36249161, 2, 1);

-- Usuarios de prueba -- 
INSERT INTO users (dni, firstName, lastName, userName, password, cuil, gender, nationality, birthDate, address, city, email, status) 
	VALUES (99999999,"André","Villalta", "andre007", "007","23999999991","Masculino","Peruvian","1992-11-05", "calle 123", "Don Torcuato", "andre@gmail.com", 1 );

INSERT INTO users (dni, firstName, lastName, userName, password, cuil, gender, nationality, birthDate, address, city, email, status) 
	VALUES ("99999988","Alan","Benitez", "alan008", "007","23999999881","Masculino","Argentina","1991-01-01", "calle 123", "Benavidez", "alan@gmail.com", 1 );

INSERT INTO users (dni, firstName, lastName, userName, password, cuil, gender, nationality, birthDate, address, city, email, status) 
	VALUES ("36249161","Federico","Rocca", "usernameLoco", "Password","20362491615","Masculino","Argentina","1991-11-22", "Una calle random", "San Fernando", "frocca17@gmail.com", 1 );

select * from accounts;
select * from users;
select * from roles;

SELECT u.dni, u.cuil, u.firstname, u.lastname, u.email, nats.country, u.birthdate FROM Users u INNER JOIN Roles_x_Users rxu ON rxu.dni = u.dni INNER JOIN Nationalities nats on u.nationality = nats.id WHERE rxu.roleId = 2;
SELECT u.dni, u.cuil, u.firstname, u.lastname, u.email, u.birthdate FROM Users u