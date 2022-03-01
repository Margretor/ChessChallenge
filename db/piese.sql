DROP DATABASE chessDB;
CREATE DATABASE chessDB;
USE chessDB;
CREATE TABLE tblPieces (
	id			 INT PRIMARY KEY, 
	pieceType    VARCHAR(10) NOT NULL,
	colour	 	 VARCHAR(10) NOT NULL,
	position	 INT UNIQUE NOT NULL,
	onBoard	 	 TINYINT(1) NOT NULL
	
);
# 0 increment
DESCRIBE tblPieces;

CREATE TABLE tblSquares (
	idSquare			  INT PRIMARY KEY, 
	colourSquare	 	  VARCHAR(10) NOT NULL,
	positionSquare	      INT UNIQUE NOT NULL,
	idPiece			      INT,
	FOREIGN KEY (idPiece) REFERENCES tblPieces(id) ON DELETE SET NULL ON UPDATE CASCADE
);

DESCRIBE tblSquares;

CREATE TABLE tblBoard (
	scoreW INT,
	scoreB INT,
	turn   TINYINT(1),
	in_sah TINYINT(1)
);
DESCRIBE tblBoard;

#pawn - pion 	rook - tura		knight - cal	bishop - nebunul	queen king

INSERT INTO tblPieces VALUES( 0, "rook", "black", 0, 1), ( 1, "knight", "black", 1, true),
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

SELECT * FROM tblPieces;


INSERT INTO tblSquares  VALUES ( 0, "white", 0, 0),(1, "black", 1, 1), (2, "white", 2, 2), 
(3,"black", 3, 3), (4, "white", 4, 4), (5,"black", 5, 5), (6, "white", 6, 6),
(7, "black", 7, 7), ( 8,"black", 8, 8), (9,"white", 9, 9), (10, "black", 10, 10), 
(11,"white", 11, 11), (12, "black", 12, 12), (13,"white", 13, 13), (14, "black", 14, 14), 
(15, "white", 15, 15), (16, "white", 16, NULL), (17, "black", 17, NULL), (18, "white", 18, NULL),
(19, "black", 19, NULL), (20, "white", 20, NULL), (21,"black", 21, NULL), (22, "white", 22, NULL),
(23, "black", 23, NULL), (24, "black", 24, NULL), (25, "white", 25, NULL), (26, "black", 26, NULL), 
(27, "white", 27, NULL), (28, "black", 28, NULL), (29, "white", 29, NULL), (30, "black", 30, NULL),
(31, "white", 31, NULL), (32, "white", 32, NULL), (33, "black", 33, NULL), (34, "white", 34, NULL), 
(35, "black", 35, NULL), (36, "white", 36, NULL), (37, "black", 37, NULL), (38, "white", 38, NULL), 
(39, "black", 39, NULL), (40,  "black", 40, NULL), (41, "white", 41, NULL), (42, "black", 42, NULL), 
(43,"white", 43, NULL), (44, "black", 44, NULL), (45, "white", 45, NULL), (46, "black", 46, NULL), 
(47, "white", 47, NULL), ( 48, "white", 48, 16), (49, "black", 49, 17), (50, "white", 50, 18), 
(51, "black", 51, 19), (52, "white", 52, 20), (53, "black", 53, 21), (54, "white", 54, 22), 
(55,"black", 55, 23), (56, "black", 56, 24), (57, "white", 57, 25), (58, "black", 58, 26), 
(59, "white", 59, 27), ( 60, "black", 60, 28), (61, "white", 61, 29), ( 62, "black", 62, 30), 
(63,"white", 63, 31); 

SELECT * FROM tblSquares;


INSERT INTO tblBoard VALUES( 0, 0, 0, 0);

SELECT * FROM tblBoard;


#DELETE FROM tblPieces WHERE id = 13;
/*UPDATE tblSquares SET idPiece = NULL WHERE idSquare = 51;
#delete
UPDATE tblPieces SET position = 43 WHERE id = 19;
UPDATE tblSquares SET idPiece = 19 WHERE idSquare = 43;
SELECT * FROM tblPieces;
SELECT * FROM tblSquares;
*/
