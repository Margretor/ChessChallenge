DROP DATABASE boardDB;
CREATE DATABASE boardDB;
USE boardDB;
CREATE TABLE tblpieces (
	id			 INT, 
	pieceType    VARCHAR(10) NOT NULL,
	colour	 	 VARCHAR(10) NOT NULL,
	position	 INT UNIQUE NOT NULL,
	onBoard	 	 TINYINT(1) NOT NULL,
	PRIMARY KEY (id)
);

DESCRIBE tblpieces;

CREATE TABLE tblsquares (
	idSquare			  INT, 
	colourSquare	 	  VARCHAR(10) NOT NULL,
	positionSquare	      INT NOT NULL,
	idPiece			      INT, #!!
	PRIMARY KEY (idSquare)
);

DESCRIBE tblsquares;
#pawn - pion 	rook - tura		knight - cal	bishop - nebunul	queen king

INSERT INTO tblpieces VALUES( 0, "rook", "black", 0, 1), ( 1, "knight", "black", 1, true),
( 2, "bishop", "black", 2, true), ( 3, "queen", "black", 3, true),
( 4, "king", "black", 4, true), ( 5, "bishop", "black", 5, true),
( 6, "knight", "black", 6, true), ( 7, "rook", "black", 7, true),
( 8, "pawn", "black", 8, true), ( 9, "pawn", "black", 9, true),
( 10, "pawn", "black", 10, true), ( 11, "pawn", "black", 11, true),
( 12, "pawn", "black", 12, true), ( 13, "pawn", "black", 13, true),
( 14, "pawn", "black", 14, true), ( 15, "pawn", "black", 15, true),

( 16, "pawn", "white", 48, true), ( 17, "pawn", "white", 49, true), 
( 18, "pawn", "white", 50, true), ( 19, "pawn", "white", 51, true), 
( 20, "pawn", "white", 52, true), ( 21, "pawn", "white", 53, true), 
( 22, "pawn", "white", 54, true), ( 23, "pawn", "white", 55, true), 
( 24, "rook", "white", 56, true), ( 25, "knight", "white", 57, true), 
( 26, "bishop", "white", 58, true), ( 27, "queen", "white", 59, true), 
( 28, "king", "white", 60, true), ( 29, "bishop", "white", 61, true), 
( 30, "knight", "white", 62, true), ( 31, "rook", "white", 63, true);


SELECT * FROM tblpieces;

