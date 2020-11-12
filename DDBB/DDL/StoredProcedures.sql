USE bdBanco;

DELIMITER $$
CREATE PROCEDURE SP_InsertUser (
-- Variables User --
IN _dni 		VARCHAR(9),
IN _firstName	VARCHAR(45),
IN _lastName 	VARCHAR(45),
IN _userName 	VARCHAR(45),
IN _password 	VARCHAR(45),
IN _cuil 		VARCHAR(11),
IN _gender 		VARCHAR(30),
IN _nationality VARCHAR(35),
IN _birthDate	DATETIME,
IN _address 	VARCHAR(50),
IN _city		VARCHAR(45),
IN _email		VARCHAR(50),
-- Variables Phone --
IN _numberPhone INT,
IN _description VARCHAR(20)
)
BEGIN
	DECLARE errno INT;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET CURRENT DIAGNOSTICS CONDITION 1 errno = MYSQL_ERRNO;
		ROLLBACK;
    END;
		START TRANSACTION;        		
			INSERT INTO users (dni, firstName, lastName, userName, password, cuil, gender, nationality, birthDate, address, city, email, status) 
					VALUES(_dni, _firstName, _lastName, _userName, _password, _cuil, _gender, _nationality, _birthDate, _address, _city, _email, 1 );
			INSERT INTO phones (numberPhone, description, userDni, status) 
					VALUES (_numberPhone, _description, dni, 1);
		COMMIT WORK;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE SP_DeleteCustomer (
IN _dni VARCHAR(9)
)
BEGIN
	DECLARE errno INT;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET CURRENT DIAGNOSTICS CONDITION 1 errno = MYSQL_ERRNO;
		ROLLBACK;
    END;
		START TRANSACTION;			
			UPDATE users u INNER JOIN phones p ON u.dni = p.userDni
				SET u.status = 0, p.status = 0 WHERE u.dni = _dni;
		COMMIT WORK;
END$$
DELIMITER ;