USE bdbanco;

SELECT *FROM Users;

INSERT INTO Users (dni, firstName, lastName, userName, password, cuil, gender, nationality, birthDate, address, city, email, status) 
VALUES (99999999,"André","Villalta", "andre007", "007","23999999991","Masculino","Peruvian","1992-11-05", "calle 123", "Don Torcuato", "andre@gmail.com", 1 );

INSERT INTO Users (dni, firstName, lastName, userName, password, cuil, gender, nationality, birthDate, address, city, email, status) 
VALUES ("99999988","Alan","Benitez", "alan008", "007","23999999881","Masculino","Argentina","1991-01-01", "calle 123", "Benavidez", "alan@gmail.com", 1 );