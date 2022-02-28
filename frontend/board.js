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


var txt = '{"squaresList":[{"idSquare":0,"colourSquare":"white","positionSquare":0,"idPiece":0},{"idSquare":1,"colourSquare":"black","positionSquare":1,"idPiece":1},{"idSquare":2,"colourSquare":"white","positionSquare":2,"idPiece":2},{"idSquare":3,"colourSquare":"black","positionSquare":3,"idPiece":3},{"idSquare":4,"colourSquare":"white","positionSquare":4,"idPiece":4},{"idSquare":5,"colourSquare":"black","positionSquare":5,"idPiece":5},{"idSquare":6,"colourSquare":"white","positionSquare":6,"idPiece":6},{"idSquare":7,"colourSquare":"black","positionSquare":7,"idPiece":7},{"idSquare":8,"colourSquare":"black","positionSquare":8,"idPiece":8},{"idSquare":9,"colourSquare":"white","positionSquare":9,"idPiece":9},{"idSquare":10,"colourSquare":"black","positionSquare":10,"idPiece":10},{"idSquare":11,"colourSquare":"white","positionSquare":11,"idPiece":11},{"idSquare":12,"colourSquare":"black","positionSquare":12,"idPiece":12},{"idSquare":13,"colourSquare":"white","positionSquare":13,"idPiece":13},{"idSquare":14,"colourSquare":"black","positionSquare":14,"idPiece":14},{"idSquare":15,"colourSquare":"white","positionSquare":15,"idPiece":15},{"idSquare":16,"colourSquare":"white","positionSquare":16,"idPiece":null},{"idSquare":17,"colourSquare":"black","positionSquare":17,"idPiece":null},{"idSquare":18,"colourSquare":"white","positionSquare":18,"idPiece":null},{"idSquare":19,"colourSquare":"black","positionSquare":19,"idPiece":null},{"idSquare":20,"colourSquare":"white","positionSquare":20,"idPiece":null},{"idSquare":21,"colourSquare":"black","positionSquare":21,"idPiece":null},{"idSquare":22,"colourSquare":"white","positionSquare":22,"idPiece":null},{"idSquare":23,"colourSquare":"black","positionSquare":23,"idPiece":null},{"idSquare":24,"colourSquare":"black","positionSquare":24,"idPiece":null},{"idSquare":25,"colourSquare":"white","positionSquare":25,"idPiece":null},{"idSquare":26,"colourSquare":"black","positionSquare":26,"idPiece":null},{"idSquare":27,"colourSquare":"white","positionSquare":27,"idPiece":null},{"idSquare":28,"colourSquare":"black","positionSquare":28,"idPiece":null},{"idSquare":29,"colourSquare":"white","positionSquare":29,"idPiece":null},{"idSquare":30,"colourSquare":"black","positionSquare":30,"idPiece":null},{"idSquare":31,"colourSquare":"white","positionSquare":31,"idPiece":null},{"idSquare":32,"colourSquare":"white","positionSquare":32,"idPiece":null},{"idSquare":33,"colourSquare":"black","positionSquare":33,"idPiece":null},{"idSquare":34,"colourSquare":"white","positionSquare":34,"idPiece":null},{"idSquare":35,"colourSquare":"black","positionSquare":35,"idPiece":null},{"idSquare":36,"colourSquare":"white","positionSquare":36,"idPiece":null},{"idSquare":37,"colourSquare":"black","positionSquare":37,"idPiece":null},{"idSquare":38,"colourSquare":"white","positionSquare":38,"idPiece":null},{"idSquare":39,"colourSquare":"black","positionSquare":39,"idPiece":null},{"idSquare":40,"colourSquare":"black","positionSquare":40,"idPiece":null},{"idSquare":41,"colourSquare":"white","positionSquare":41,"idPiece":null},{"idSquare":42,"colourSquare":"black","positionSquare":42,"idPiece":null},{"idSquare":43,"colourSquare":"white","positionSquare":43,"idPiece":null},{"idSquare":44,"colourSquare":"black","positionSquare":44,"idPiece":null},{"idSquare":45,"colourSquare":"white","positionSquare":45,"idPiece":null},{"idSquare":46,"colourSquare":"black","positionSquare":46,"idPiece":null},{"idSquare":47,"colourSquare":"white","positionSquare":47,"idPiece":null},{"idSquare":48,"colourSquare":"white","positionSquare":48,"idPiece":16},{"idSquare":49,"colourSquare":"black","positionSquare":49,"idPiece":17},{"idSquare":50,"colourSquare":"white","positionSquare":50,"idPiece":18},{"idSquare":51,"colourSquare":"black","positionSquare":51,"idPiece":19},{"idSquare":52,"colourSquare":"white","positionSquare":52,"idPiece":20},{"idSquare":53,"colourSquare":"black","positionSquare":53,"idPiece":21},{"idSquare":54,"colourSquare":"white","positionSquare":54,"idPiece":22},{"idSquare":55,"colourSquare":"black","positionSquare":55,"idPiece":23},{"idSquare":56,"colourSquare":"black","positionSquare":56,"idPiece":24},{"idSquare":57,"colourSquare":"white","positionSquare":57,"idPiece":25},{"idSquare":58,"colourSquare":"black","positionSquare":58,"idPiece":26},{"idSquare":59,"colourSquare":"white","positionSquare":59,"idPiece":27},{"idSquare":60,"colourSquare":"black","positionSquare":60,"idPiece":28},{"idSquare":61,"colourSquare":"white","positionSquare":61,"idPiece":29},{"idSquare":62,"colourSquare":"black","positionSquare":62,"idPiece":30},{"idSquare":63,"colourSquare":"white","positionSquare":63,"idPiece":31}],"piecesList":[{"id":0,"pieceType":"rook","colour":"black","position":0,"onBoard":true},{"id":1,"pieceType":"knight","colour":"black","position":1,"onBoard":true},{"id":2,"pieceType":"bishop","colour":"black","position":2,"onBoard":true},{"id":3,"pieceType":"queen","colour":"black","position":3,"onBoard":true},{"id":4,"pieceType":"king","colour":"black","position":4,"onBoard":true},{"id":5,"pieceType":"bishop","colour":"black","position":5,"onBoard":true},{"id":6,"pieceType":"knight","colour":"black","position":6,"onBoard":true},{"id":7,"pieceType":"rook","colour":"black","position":7,"onBoard":true},{"id":8,"pieceType":"pawn","colour":"black","position":8,"onBoard":true},{"id":9,"pieceType":"pawn","colour":"black","position":9,"onBoard":true},{"id":10,"pieceType":"pawn","colour":"black","position":10,"onBoard":true},{"id":11,"pieceType":"pawn","colour":"black","position":11,"onBoard":true},{"id":12,"pieceType":"pawn","colour":"black","position":12,"onBoard":true},{"id":13,"pieceType":"pawn","colour":"black","position":13,"onBoard":true},{"id":14,"pieceType":"pawn","colour":"black","position":14,"onBoard":true},{"id":15,"pieceType":"pawn","colour":"black","position":15,"onBoard":true},{"id":16,"pieceType":"pawn","colour":"white","position":48,"onBoard":true},{"id":17,"pieceType":"pawn","colour":"white","position":49,"onBoard":true},{"id":18,"pieceType":"pawn","colour":"white","position":50,"onBoard":true},{"id":19,"pieceType":"pawn","colour":"white","position":51,"onBoard":true},{"id":20,"pieceType":"pawn","colour":"white","position":52,"onBoard":true},{"id":21,"pieceType":"pawn","colour":"white","position":53,"onBoard":true},{"id":22,"pieceType":"pawn","colour":"white","position":54,"onBoard":true},{"id":23,"pieceType":"pawn","colour":"white","position":55,"onBoard":true},{"id":24,"pieceType":"rook","colour":"white","position":56,"onBoard":true},{"id":25,"pieceType":"knight","colour":"white","position":57,"onBoard":true},{"id":26,"pieceType":"bishop","colour":"white","position":58,"onBoard":true},{"id":27,"pieceType":"queen","colour":"white","position":59,"onBoard":true},{"id":28,"pieceType":"king","colour":"white","position":60,"onBoard":true},{"id":29,"pieceType":"bishop","colour":"white","position":61,"onBoard":true},{"id":30,"pieceType":"knight","colour":"white","position":62,"onBoard":true},{"id":31,"pieceType":"rook","colour":"white","position":63,"onBoard":true}],"score":"0 - 0","turn":false}';
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
        if (piece.onBoard == 1) {
            console.log(piece.pieceType);
            switch (piece.pieceType) {
                case 'rook':
                    if (piece.colour == 'black') {
                        var img = imgRook;
                        console.log(imgRook);
                    } else {
                        var img = imgRookW;
                        console.log(imgRookW);
                    }
                    break;
                case 'knight':
                    if (piece.colour == 'black') {
                        var img = imgKnight;
                        console.log(imgKnight);
                    } else {
                        var img = imgKnightW;
                        console.log(imgKnightW);
                    }
                    break;
                case 'queen':
                    if (piece.colour == 'black') {
                        var img = imgQueen;
                        console.log(imgQueen);
                    } else {
                        var img = imgQueenW;
                        console.log(imgQueenW);
                    }
                    break;
                case 'pawn':
                    if (piece.colour == 'black') {
                        var img = imgPawn;
                        console.log(imgPawn);
                    } else {
                        var img = imgPawnW;
                        console.log(imgPawnW);
                    }
                    break;
                case 'bishop':
                    if (piece.colour == 'black') {
                        var img = imgBishop;
                        console.log(imgBishop);
                    } else {
                        var img = imgBishopW;
                        console.log(imgBishopW);
                    }
                    break;
                case 'king':
                    if (piece.colour == 'black') {
                        var img = imgKing;
                        console.log(imgKing);
                    } else {
                        var img = imgKingW;
                        console.log(imgKingW);
                    }
                    break;
            }
            console.log(img);
            drawPiece(img);
        }
    }
    );

}












loadImages();
drawBoard();


// //CREATE IMAGE OBJECT
// var myImage = new Image();
// myImage.src = "images/sample.jpg";
// context.drawImage(myImage, 0,0, 50, 100);
