"use strict";
// Get the canvas context
var chessCanvas = document.getElementById("board");
var chessContext = chessCanvas.getContext("2d");
// Define piece images
var imgPawn = new Image();
var imgRook = new Image();
var imgKnight = new Image();
var imgBishop = new Image();
var imgQueen = new Image();
var imgKing = new Image();
var imgPawnW = new Image();
var imgRookW = new Image();
var imgKnightW = new Image();
var imgBishopW = new Image();
var imgQueenW = new Image();
var imgKingW = new Image();

//array of 32 pieces
var pieces = new Array(32);
//
function loadImages() {
    imgPawn.src = "../img/blackPawn.png";
    imgRook.src = "../img/blackRook.png";
    imgKnight.src = "../img/blackKnight.png";
    imgBishop.src = "../img/blackBishop.png";
    imgQueen.src = "../img/blackQueen.png";
    imgKing.src = "../img/blackKing.png";
    imgPawnW.src = "../img/whitePawn.png";
    imgRookW.src = "../img/whiteRook.png";
    imgKnightW.src = "../img/whiteKnight.png"
    imgBishopW.src = "../img/whiteBishop.png";
    imgQueenW.src = "../img/whiteQueen.png";
    imgKingW.src = "../img/whiteKing.png";
}
// Define a class to store the piece properties
function BlackRook() {
    console.log(imgRook.src);
    var image = imgRook;
    var x = 3;
    var y = 3;
    var height = 2;
    var width = 2;
    var deleted = false;
    console.log("inainte");
    chessContext.drawImage(image, x, y);
    console.log("dupa");
}
// Draw the chess board
function drawBoard() {
    chessContext.clearRect(0, 0, 600, 600);
    chessContext.fillStyle = "black";
    chessContext.strokeStyle = "black";
    // Draw the alternating squares
    for (var x = 0; x < 8; x++) {
        for (var y = 0; y < 8; y++) {
            if ((x + y) % 2) {
                chessContext.fillRect(75 * x, 75 * y, 75, 75);
            }
        }
    }
    // Add a border around the entire board
    chessContext.strokeRect(0, 0, 600, 600);
}

// Draw a chess piece
function drawPiece(piece) {
    if (!piece.deleted)
        chessContext.drawImage(p.image,0,0, width, height);
}
loadImages();
drawPiece()
//drawBoard();
BlackRook();

// //CREATE IMAGE OBJECT
// var myImage = new Image();
// myImage.src = "images/sample.jpg";
// context.drawImage(myImage, 0,0, 50, 100);
