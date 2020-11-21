-- Creación de base de datos y tablas -- 

CREATE DATABASE bdBanco;

USE bdBanco;


CREATE TABLE Report1
(
	id					INT 			NOT NULL,
    LoanId				INT 			NOT NULL,
    cuil				VARCHAR(20)		NOT NULL,
    amountReqByCustomer DECIMAL(10,2) 	NOT NULL,
    approvedBy 			VARCHAR(20) 	NOT NULL,
    approvalDate 		DATETIME 		NOT NULL,
     
	PRIMARY KEY (id)
);

CREATE TABLE LoanState
(
	id 		INT AUTO_INCREMENT NOT NULL,
	state 	VARCHAR(10) 	   NOT NULL, /*Pendiente, Aceptado, Rechazado*/
	
	PRIMARY KEY (id)
);

CREATE TABLE Loan
(
	id					INT AUTO_INCREMENT NOT NULL,
    dni					VARCHAR(9) 		NOT NULL,
    accountNumber		INT				NOT NULL,
    loanDate 			DATETIME 		NOT NULL,
    amountInt 			DECIMAL(10,2) NOT NULL,
    amountReqByCustomer DECIMAL(10,2) NOT NULL,
    paymentDeadline 	DATETIME 		NOT NULL,
    monthlyFee			DECIMAL(10,2) NOT NULL,
    amountOfFees		INT				NOT NULL,
	loanStateId			INT				NOT NULL,
    status				BIT				NOT NULL,
     
	PRIMARY KEY (id),
    FOREIGN KEY(loanStateId) REFERENCES LoanState(id)
);

CREATE TABLE Fees_x_Loans
(
	idLoan INT NOT NULL,
    feeNumber INT NOT NULL,
	paymentDeadline DATETIME NOT NULL,
    state BIT NOT NULL,
    
    PRIMARY KEY(idLoan, feeNumber),
    FOREIGN KEY (idLoan) REFERENCES Loan(id)
);

CREATE TABLE Provinces
(
	id 			INT 	AUTO_INCREMENT 	NOT NULL,
    description VARCHAR(50)				NOT NULL,
    PRIMARY KEY (id)	
);

CREATE TABLE Users
(
	 dni 		VARCHAR(9) 	NOT NULL,
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
     idProvince	INT			NOT NULL,
     
	 PRIMARY KEY (dni),
     FOREIGN KEY(idProvince) REFERENCES Provinces(id)
);

CREATE TABLE Phones
(
	numberPhone	INT 		NOT NULL,
    description VARCHAR(20) NOT NULL, 	/* teléfono, celular, fax */
    userDni		VARCHAR(9) 	NOT NULL,
     
	PRIMARY KEY (numberPhone, userDni),
    FOREIGN KEY(userDni) REFERENCES Users(dni)
);

CREATE TABLE AccountsType
(
	id	INT 				NOT NULL,
    description VARCHAR(20) NOT NULL,
    status		BIT			NOT NULL,
     
	PRIMARY KEY (id)
);

CREATE TABLE Accounts
(
	accountNumber	INT 		NOT NULL,
    accountDni		VARCHAR(9) 	NOT NULL,
    creationDate 	DATETIME 	NOT NULL,
    accountTypeId 	INT 		NOT NULL,
    cbu 			Varchar(22)	NOT NULL,
    balance 		DECIMAL(10,2) NOT NULL,
    status			BIT			NOT NULL,
     
	PRIMARY KEY (accountNumber),
    FOREIGN KEY(accountDni) REFERENCES Users(dni),
    FOREIGN KEY(accountTypeId) REFERENCES AccountsType(id)
);

CREATE TABLE MovementType
(
	id		 INT AUTO_INCREMENT NOT NULL,
    descriptions 	VARCHAR(20) NOT NULL,
     
	PRIMARY KEY (id)
);

CREATE TABLE Movements
(
	id			 	INT AUTO_INCREMENT NOT NULL,
    accountNumber	INT				NOT NULL,
    movementDate 	DATETIME 		NOT NULL,
    detail 		VARCHAR(60) 	NOT NULL,
    amount 			DECIMAL(10,2) 	NOT NULL,
    MovementTypeId	INT 			NOT NULL,
    status			BIT				NOT NULL,
     
	PRIMARY KEY (id),
    FOREIGN KEY(MovementTypeId) REFERENCES MovementType(id),
    FOREIGN KEY (accountNumber) REFERENCES Accounts(accountNumber)
);

CREATE TABLE Roles
(
	id		INT			AUTO_INCREMENT NOT NULL,
	name 	VARCHAR(20)					NOT NULL, /* Admin o Cliente */
    status	BIT							NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Roles_x_Users
(
	dni		VARCHAR(9)	NOT NULL,
	roleId 	INT			NOT NULL, 
    status	BIT			NOT NULL,
    
    PRIMARY KEY(dni, roleId),
    FOREIGN KEY(dni) REFERENCES Users(dni),
    FOREIGN KEY(roleId) REFERENCES Roles (id)
);

CREATE TABLE Nationalities
(
	id			INT 	AUTO_INCREMENT	NOT NULL,
    country 	VARCHAR(30) 			NOT NULL, 
    gentilic	VARCHAR(40) 			NOT NULL,
    iso			VARCHAR(3)				NULL,
     
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

-- Stored procedures --

USE bdBanco;

-- CUSTOMER --
USE `bdbanco`;

DELIMITER $$
USE `bdbanco`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_InsertCustomer`(
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
IN _numberPhone INT,
IN _description VARCHAR(20),
IN _province	VARCHAR(45)
)
BEGIN
	DECLARE errno INT;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET CURRENT DIAGNOSTICS CONDITION 1 errno = MYSQL_ERRNO;
		ROLLBACK;
    END;
		START TRANSACTION;        		
			INSERT INTO users (dni, firstName, lastName, userName, password, cuil, gender, nationality, birthDate, address, city, email, status, province) 
					VALUES(_dni, _firstName, _lastName, _userName, _password, _cuil, _gender, _nationality, _birthDate, _address, _city, _email, 1, _province );

			INSERT INTO phones (numberPhone, description, userDni) 
					VALUES (_numberPhone, _description, _dni);

			INSERT INTO roles_x_users (dni, roleId, status) 
					VALUES (_dni, 2, 1);
		COMMIT WORK;
	END;$$

DELIMITER ;





DELIMITER $$
CREATE PROCEDURE SP_UpdateCustomer (
-- Variables User --
IN _dni 		VARCHAR(9),
IN _firstName	VARCHAR(45),
IN _lastName 	VARCHAR(45),
IN _userName 	VARCHAR(45),
IN _cuil 		VARCHAR(11),
IN _gender 		VARCHAR(30),
IN _nationality VARCHAR(35),
IN _birthDate	DATETIME,
IN _email		VARCHAR(50),
in _password    VARCHAR(45)
)
BEGIN
	DECLARE errno INT;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET CURRENT DIAGNOSTICS CONDITION 1 errno = MYSQL_ERRNO;
		ROLLBACK;
    END;
		START TRANSACTION;   
			UPDATE users 
				SET 
                firstName = _firstName, 
                lastName = _lastName, 
                userName = _userName, 
                cuil = _cuil, 
                gender= _gender, 
                nationality = _nationality, 
                birthDate = _birthDate, 
                email = _email,
                password = _password
				WHERE dni = _dni;
		COMMIT WORK;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_DeleteCustomer`(
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
			UPDATE users SET status = 0 WHERE dni = _dni;
		COMMIT WORK;
END$$
DELIMITER ;


-- ACCOUNTS --	