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

CREATE TABLE Loan
(
	id					INT 			NOT NULL,
    cuil				VARCHAR(20) 	NOT NULL,
    loandate 			DATETIME 		NOT NULL,
    ammountInt 			DECIMAL(10,2) 	NOT NULL,
    amountReqByCustomer DECIMAL(10,2) 	NOT NULL,
    paymentDeadline 	DATETIME 		NOT NULL,
    status				BIT				NOT NULL,
     
	PRIMARY KEY (id)
);

CREATE TABLE MovementType
(
	id				INT 		NOT NULL,
    descriptions 	VARCHAR(20) NOT NULL,
    status			BIT			NOT NULL,
     
	PRIMARY KEY (id)
);

CREATE TABLE Movement
(
	id				INT 			NOT NULL,
    movementDate 	DATETIME 		NOT NULL,
    details 		VARCHAR(20) 	NOT NULL,
    amount 			DECIMAL(10,2) 	NOT NULL,
    MovementTypeId	INT 			NOT NULL,
    status			BIT				NOT NULL,
     
	PRIMARY KEY (id),
    FOREIGN KEY(MovementTypeId) REFERENCES MovementType(id)
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
     
	 PRIMARY KEY (dni)
);

CREATE TABLE Accounts
(
	accountNumber	INT 		NOT NULL,
    accountDni		VARCHAR(9) 	NOT NULL,
    creationDate 	DATETIME 	NOT NULL,
    accountypeid 	INT 		NOT NULL,
    cbu 			INT 		NOT NULL,
    balance 		DECIMAL(10,2) NOT NULL,
    status			BIT			NOT NULL,
     
	PRIMARY KEY (accountNumber),
    FOREIGN KEY(accountDni) REFERENCES Users(dni)
);

CREATE TABLE AccountsType
(
	id	INT 				NOT NULL,
    description VARCHAR(20) NOT NULL,
    status		BIT			NOT NULL,
     
	PRIMARY KEY (id),
    FOREIGN KEY(id) REFERENCES Accounts(accountNumber)
);

CREATE TABLE Accounts_x_movement
(
	movementId		INT NOT NULL,
    accountnumber	INT NOT NULL,
    status			BIT	NOT NULL,
     
	PRIMARY KEY (movementId,accountnumber),
    FOREIGN KEY(movementId) REFERENCES Movement(id),
    FOREIGN KEY(accountnumber) REFERENCES Accounts(accountNumber)
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

CREATE TABLE Phones
(
	numberPhone	INT 		NOT NULL,
    description VARCHAR(20) NOT NULL, 	/* teléfono, celular, fax */
    userDni		VARCHAR(9) 		NULL,
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




