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


var txt = '{"squaresList":[{"idSquare":0,"colourSquare":"white","positionSquare":0,"idPiece":0},{"idSquare":1,"colourSquare":"black","positionSquare":1,"idPiece":1}],"piecesList":[{"id":0,"pieceType":"rook","colour":"black","position":0,"onBoard":1},{"id":1,"pieceType":"knight","colour":"black","position":1,"onBoard":1}],"scor":2,"turn":0}';
const obj = JSON.parse(txt);
var pieces = obj.piecesList;
console.log(obj);
console.log(pieces);

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



// Draw a chess piece
function drawPiece(piece) {
        chessContext.drawImage(piece,0,0);
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

window.onload=function (){
pieces.forEach(piece => {
    console.log(piece);
    var img;
    if (piece.onBoard==1){
        console.log(piece.pieceType);
        switch (piece.pieceType) {
            case 'rook':
                if (piece.colour == 'black' ) {
                  var img = imgRook;
                  console.log(imgRook);
                }
                else {
                   var img = imgRookW;
                    console.log(imgRookW);
                }
                break;
            case 'knight':
                if (piece.colour == 'black' ) {
                    var img = imgKnight;
                    console.log(imgKnight);
                }
                else {
                    var img = imgKnightW;
                    console.log(imgKnightW);
                }
                break;
            case 'queen':
                if (piece.colour == 'black' ) {
                    var img = imgQueen;
                    console.log(imgQueen);
                }
                else {
                    var img = imgQueenW;
                    console.log(imgQueenW);
                }
                break;
            case 'pawn':
                if (piece.colour == 'black' ) {
                    var img = imgPawn;
                    console.log(imgPawn);
                }
                else {
                    var img = imgPawnW;
                    console.log(imgPawnW);
                }
                break;
            case 'bishop':
                if (piece.colour == 'black' ) {
                    var img = imgBishop;
                    console.log(imgBishop);
                }
                else {
                    var img = imgBishopW;
                    console.log(imgBishopW);
                }
                break;
            case 'king':
                if (piece.colour == 'black' ) {
                    var img = imgKing;
                    console.log(imgKing);
                }
                else {
                    var img = imgKingW;
                    console.log(imgKingW);
                }
                break;
        }
    console.log(img);
    drawPiece(img);
}
    );
}










loadImages();
drawBoard();


// //CREATE IMAGE OBJECT
// var myImage = new Image();
// myImage.src = "images/sample.jpg";
// context.drawImage(myImage, 0,0, 50, 100);
