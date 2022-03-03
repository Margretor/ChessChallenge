// "use strict";
// // Get the canvas context
// var chessCanvas = document.getElementById("board");
// var chessContext = chessCanvas.getContext("2d");
// // Define piece images
// var imgPawn = new Image();
// var imgRook = new Image();
// var imgKnight = new Image();
// var imgBishop = new Image();
// var imgQueen = new Image();
// var imgKing = new Image();
// var imgPawnW = new Image();
// var imgRookW = new Image();
// var imgKnightW = new Image();
// var imgBishopW = new Image();
// var imgQueenW = new Image();
// var imgKingW = new Image();
//
// // getJSON(localhost/tabla);
// //     function(err, data) {
// //         if (err !== null) {
// //             alert('Something went wrong: ' + err);
// //         } else {
// //             alert('Your query count: ' + data.query.count);
// //         }
// //     });
//
// var txt = '{"squaresList":[{"idSquare":0,"colourSquare":"white","positionSquare":0,"idPiece":0},{"idSquare":1,"colourSquare":"black","positionSquare":1,"idPiece":1},{"idSquare":2,"colourSquare":"white","positionSquare":2,"idPiece":2},{"idSquare":3,"colourSquare":"black","positionSquare":3,"idPiece":3},{"idSquare":4,"colourSquare":"white","positionSquare":4,"idPiece":4},{"idSquare":5,"colourSquare":"black","positionSquare":5,"idPiece":5},{"idSquare":6,"colourSquare":"white","positionSquare":6,"idPiece":6},{"idSquare":7,"colourSquare":"black","positionSquare":7,"idPiece":7},{"idSquare":8,"colourSquare":"black","positionSquare":8,"idPiece":8},{"idSquare":9,"colourSquare":"white","positionSquare":9,"idPiece":9},{"idSquare":10,"colourSquare":"black","positionSquare":10,"idPiece":10},{"idSquare":11,"colourSquare":"white","positionSquare":11,"idPiece":11},{"idSquare":12,"colourSquare":"black","positionSquare":12,"idPiece":12},{"idSquare":13,"colourSquare":"white","positionSquare":13,"idPiece":13},{"idSquare":14,"colourSquare":"black","positionSquare":14,"idPiece":14},{"idSquare":15,"colourSquare":"white","positionSquare":15,"idPiece":15},{"idSquare":16,"colourSquare":"white","positionSquare":16,"idPiece":null},{"idSquare":17,"colourSquare":"black","positionSquare":17,"idPiece":null},{"idSquare":18,"colourSquare":"white","positionSquare":18,"idPiece":null},{"idSquare":19,"colourSquare":"black","positionSquare":19,"idPiece":null},{"idSquare":20,"colourSquare":"white","positionSquare":20,"idPiece":null},{"idSquare":21,"colourSquare":"black","positionSquare":21,"idPiece":null},{"idSquare":22,"colourSquare":"white","positionSquare":22,"idPiece":null},{"idSquare":23,"colourSquare":"black","positionSquare":23,"idPiece":null},{"idSquare":24,"colourSquare":"black","positionSquare":24,"idPiece":null},{"idSquare":25,"colourSquare":"white","positionSquare":25,"idPiece":null},{"idSquare":26,"colourSquare":"black","positionSquare":26,"idPiece":null},{"idSquare":27,"colourSquare":"white","positionSquare":27,"idPiece":null},{"idSquare":28,"colourSquare":"black","positionSquare":28,"idPiece":null},{"idSquare":29,"colourSquare":"white","positionSquare":29,"idPiece":null},{"idSquare":30,"colourSquare":"black","positionSquare":30,"idPiece":null},{"idSquare":31,"colourSquare":"white","positionSquare":31,"idPiece":null},{"idSquare":32,"colourSquare":"white","positionSquare":32,"idPiece":null},{"idSquare":33,"colourSquare":"black","positionSquare":33,"idPiece":null},{"idSquare":34,"colourSquare":"white","positionSquare":34,"idPiece":null},{"idSquare":35,"colourSquare":"black","positionSquare":35,"idPiece":null},{"idSquare":36,"colourSquare":"white","positionSquare":36,"idPiece":null},{"idSquare":37,"colourSquare":"black","positionSquare":37,"idPiece":null},{"idSquare":38,"colourSquare":"white","positionSquare":38,"idPiece":null},{"idSquare":39,"colourSquare":"black","positionSquare":39,"idPiece":null},{"idSquare":40,"colourSquare":"black","positionSquare":40,"idPiece":null},{"idSquare":41,"colourSquare":"white","positionSquare":41,"idPiece":null},{"idSquare":42,"colourSquare":"black","positionSquare":42,"idPiece":null},{"idSquare":43,"colourSquare":"white","positionSquare":43,"idPiece":null},{"idSquare":44,"colourSquare":"black","positionSquare":44,"idPiece":null},{"idSquare":45,"colourSquare":"white","positionSquare":45,"idPiece":null},{"idSquare":46,"colourSquare":"black","positionSquare":46,"idPiece":null},{"idSquare":47,"colourSquare":"white","positionSquare":47,"idPiece":null},{"idSquare":48,"colourSquare":"white","positionSquare":48,"idPiece":16},{"idSquare":49,"colourSquare":"black","positionSquare":49,"idPiece":17},{"idSquare":50,"colourSquare":"white","positionSquare":50,"idPiece":18},{"idSquare":51,"colourSquare":"black","positionSquare":51,"idPiece":19},{"idSquare":52,"colourSquare":"white","positionSquare":52,"idPiece":20},{"idSquare":53,"colourSquare":"black","positionSquare":53,"idPiece":21},{"idSquare":54,"colourSquare":"white","positionSquare":54,"idPiece":22},{"idSquare":55,"colourSquare":"black","positionSquare":55,"idPiece":23},{"idSquare":56,"colourSquare":"black","positionSquare":56,"idPiece":24},{"idSquare":57,"colourSquare":"white","positionSquare":57,"idPiece":25},{"idSquare":58,"colourSquare":"black","positionSquare":58,"idPiece":26},{"idSquare":59,"colourSquare":"white","positionSquare":59,"idPiece":27},{"idSquare":60,"colourSquare":"black","positionSquare":60,"idPiece":28},{"idSquare":61,"colourSquare":"white","positionSquare":61,"idPiece":29},{"idSquare":62,"colourSquare":"black","positionSquare":62,"idPiece":30},{"idSquare":63,"colourSquare":"white","positionSquare":63,"idPiece":31}],"piecesList":[{"id":0,"pieceType":"rook","colour":"black","position":0,"onBoard":true},{"id":1,"pieceType":"knight","colour":"black","position":1,"onBoard":true},{"id":2,"pieceType":"bishop","colour":"black","position":2,"onBoard":true},{"id":3,"pieceType":"queen","colour":"black","position":3,"onBoard":true},{"id":4,"pieceType":"king","colour":"black","position":4,"onBoard":true},{"id":5,"pieceType":"bishop","colour":"black","position":5,"onBoard":true},{"id":6,"pieceType":"knight","colour":"black","position":6,"onBoard":true},{"id":7,"pieceType":"rook","colour":"black","position":7,"onBoard":true},{"id":8,"pieceType":"pawn","colour":"black","position":8,"onBoard":true},{"id":9,"pieceType":"pawn","colour":"black","position":9,"onBoard":true},{"id":10,"pieceType":"pawn","colour":"black","position":10,"onBoard":true},{"id":11,"pieceType":"pawn","colour":"black","position":11,"onBoard":true},{"id":12,"pieceType":"pawn","colour":"black","position":12,"onBoard":true},{"id":13,"pieceType":"pawn","colour":"black","position":13,"onBoard":true},{"id":14,"pieceType":"pawn","colour":"black","position":14,"onBoard":true},{"id":15,"pieceType":"pawn","colour":"black","position":15,"onBoard":true},{"id":16,"pieceType":"pawn","colour":"white","position":48,"onBoard":true},{"id":17,"pieceType":"pawn","colour":"white","position":49,"onBoard":true},{"id":18,"pieceType":"pawn","colour":"white","position":50,"onBoard":true},{"id":19,"pieceType":"pawn","colour":"white","position":51,"onBoard":true},{"id":20,"pieceType":"pawn","colour":"white","position":52,"onBoard":true},{"id":21,"pieceType":"pawn","colour":"white","position":53,"onBoard":true},{"id":22,"pieceType":"pawn","colour":"white","position":54,"onBoard":true},{"id":23,"pieceType":"pawn","colour":"white","position":55,"onBoard":true},{"id":24,"pieceType":"rook","colour":"white","position":56,"onBoard":true},{"id":25,"pieceType":"knight","colour":"white","position":57,"onBoard":true},{"id":26,"pieceType":"bishop","colour":"white","position":58,"onBoard":true},{"id":27,"pieceType":"queen","colour":"white","position":59,"onBoard":true},{"id":28,"pieceType":"king","colour":"white","position":60,"onBoard":true},{"id":29,"pieceType":"bishop","colour":"white","position":61,"onBoard":true},{"id":30,"pieceType":"knight","colour":"white","position":62,"onBoard":true},{"id":31,"pieceType":"rook","colour":"white","position":63,"onBoard":true}],"score":"0 - 0","turn":false}';
// const obj = JSON.parse(txt);
// var pieces = obj.piecesList;
// console.log(obj);
// console.log(pieces);
//
// function loadImages() {
//     imgPawn.src = "../img/blackPawn.png";
//     imgRook.src = "../img/blackRook.png";
//     imgKnight.src = "../img/blackKnight.png";
//     imgBishop.src = "../img/blackBishop.png";
//     imgQueen.src = "../img/blackQueen.png";
//     imgKing.src = "../img/blackKing.png";
//     imgPawnW.src = "../img/whitePawn.png";
//     imgRookW.src = "../img/whiteRook.png";
//     imgKnightW.src = "../img/whiteKnight.png"
//     imgBishopW.src = "../img/whiteBishop.png";
//     imgQueenW.src = "../img/whiteQueen.png";
//     imgKingW.src = "../img/whiteKing.png";
// }
//
//
//
// // Draw a chess piece
// function drawPiece(img,piece) {
//             var y = piece.position/8;
//             var x = piece.position % 8;
//             var width = 30;
//             var height = 60;
//             var posx = 60 + 73 * x;
//             var posy = 65  + 73 * y;
//
//             chessContext.drawImage(img,posx,posy,width,height);
// }
//
//
//
// // Draw the chess board
// function drawBoard() {
//     chessContext.clearRect(0, 0, 600, 600);
//     chessContext.fillStyle = "brown";
//     chessContext.strokeStyle = "brown";
//     // Draw the alternating squares
//     for (var x = 0; x < 8; x++) {
//         for (var y = 0; y < 8; y++) {
//             if ((x + y) % 2) {
//                 chessContext.fillRect(75 * x, 75 * y, 75, 75);
//             }
//         }
//     }
//     // Add a border around the entire board
//     chessContext.strokeRect(0, 0, 600, 600);
// }
//
// var renderBoard = ()=> {
//     pieces.forEach(piece => {
//             console.log(piece);
//             var img;
//             if (piece.onBoard == 1) {
//                 console.log(piece.pieceType);
//                 switch (piece.pieceType) {
//                     case 'rook':
//                         if (piece.colour == 'black') {
//                             var img = imgRook;
//                             console.log(imgRook);
//                         } else {
//                             var img = imgRookW;
//                             console.log(imgRookW);
//                         }
//                         break;
//                     case 'knight':
//                         if (piece.colour == 'black') {
//                             var img = imgKnight;
//                             console.log(imgKnight);
//                         } else {
//                             var img = imgKnightW;
//                             console.log(imgKnightW);
//                         }
//                         break;
//                     case 'queen':
//                         if (piece.colour == 'black') {
//                             var img = imgQueen;
//                             console.log(imgQueen);
//                         } else {
//                             var img = imgQueenW;
//                             console.log(imgQueenW);
//                         }
//                         break;
//                     case 'pawn':
//                         if (piece.colour == 'black') {
//                             var img = imgPawn;
//                             console.log(imgPawn);
//                         } else {
//                             var img = imgPawnW;
//                             console.log(imgPawnW);
//                         }
//                         break;
//                     case 'bishop':
//                         if (piece.colour == 'black') {
//                             var img = imgBishop;
//                             console.log(imgBishop);
//                         } else {
//                             var img = imgBishopW;
//                             console.log(imgBishopW);
//                         }
//                         break;
//                     case 'king':
//                         if (piece.colour == 'black') {
//                             var img = imgKing;
//                             console.log(imgKing);
//                         } else {
//                             var img = imgKingW;
//                             console.log(imgKingW);
//                         }
//                         break;
//                 }
//                 console.log(img);
//                 drawPiece(img,piece);
//             }
//         }
//     );
// }

//
//
//
//
//
//
//
//
//
//
//
//
//
// loadImages();
// drawBoard();
// setTimeout(()=>{
//     //chessContext.drawImage(imgRook,100,100,60,60)
//     renderBoard();
// },500);
//
//
//
// // //CREATE IMAGE OBJECT
// // var myImage = new Image();
// // myImage.src = "images/sample.jpg";
// // context.drawImage(myImage, 0,0, 50, 100);

let m = [];
let s = 100;
let colors = ['#333333', '#dddddd'];
let colors_pieces = ['#dd0000', '#0000bb'];
var txt = '{"squaresList":[{"idSquare":0,"colourSquare":"white","positionSquare":0,"idPiece":0},{"idSquare":1,"colourSquare":"black","positionSquare":1,"idPiece":1},{"idSquare":2,"colourSquare":"white","positionSquare":2,"idPiece":2},{"idSquare":3,"colourSquare":"black","positionSquare":3,"idPiece":3},{"idSquare":4,"colourSquare":"white","positionSquare":4,"idPiece":4},{"idSquare":5,"colourSquare":"black","positionSquare":5,"idPiece":5},{"idSquare":6,"colourSquare":"white","positionSquare":6,"idPiece":6},{"idSquare":7,"colourSquare":"black","positionSquare":7,"idPiece":7},{"idSquare":8,"colourSquare":"black","positionSquare":8,"idPiece":8},{"idSquare":9,"colourSquare":"white","positionSquare":9,"idPiece":9},{"idSquare":10,"colourSquare":"black","positionSquare":10,"idPiece":10},{"idSquare":11,"colourSquare":"white","positionSquare":11,"idPiece":11},{"idSquare":12,"colourSquare":"black","positionSquare":12,"idPiece":12},{"idSquare":13,"colourSquare":"white","positionSquare":13,"idPiece":13},{"idSquare":14,"colourSquare":"black","positionSquare":14,"idPiece":14},{"idSquare":15,"colourSquare":"white","positionSquare":15,"idPiece":15},{"idSquare":16,"colourSquare":"white","positionSquare":16,"idPiece":null},{"idSquare":17,"colourSquare":"black","positionSquare":17,"idPiece":null},{"idSquare":18,"colourSquare":"white","positionSquare":18,"idPiece":null},{"idSquare":19,"colourSquare":"black","positionSquare":19,"idPiece":null},{"idSquare":20,"colourSquare":"white","positionSquare":20,"idPiece":null},{"idSquare":21,"colourSquare":"black","positionSquare":21,"idPiece":null},{"idSquare":22,"colourSquare":"white","positionSquare":22,"idPiece":null},{"idSquare":23,"colourSquare":"black","positionSquare":23,"idPiece":null},{"idSquare":24,"colourSquare":"black","positionSquare":24,"idPiece":null},{"idSquare":25,"colourSquare":"white","positionSquare":25,"idPiece":null},{"idSquare":26,"colourSquare":"black","positionSquare":26,"idPiece":null},{"idSquare":27,"colourSquare":"white","positionSquare":27,"idPiece":null},{"idSquare":28,"colourSquare":"black","positionSquare":28,"idPiece":null},{"idSquare":29,"colourSquare":"white","positionSquare":29,"idPiece":null},{"idSquare":30,"colourSquare":"black","positionSquare":30,"idPiece":null},{"idSquare":31,"colourSquare":"white","positionSquare":31,"idPiece":null},{"idSquare":32,"colourSquare":"white","positionSquare":32,"idPiece":null},{"idSquare":33,"colourSquare":"black","positionSquare":33,"idPiece":null},{"idSquare":34,"colourSquare":"white","positionSquare":34,"idPiece":null},{"idSquare":35,"colourSquare":"black","positionSquare":35,"idPiece":null},{"idSquare":36,"colourSquare":"white","positionSquare":36,"idPiece":null},{"idSquare":37,"colourSquare":"black","positionSquare":37,"idPiece":null},{"idSquare":38,"colourSquare":"white","positionSquare":38,"idPiece":null},{"idSquare":39,"colourSquare":"black","positionSquare":39,"idPiece":null},{"idSquare":40,"colourSquare":"black","positionSquare":40,"idPiece":null},{"idSquare":41,"colourSquare":"white","positionSquare":41,"idPiece":null},{"idSquare":42,"colourSquare":"black","positionSquare":42,"idPiece":null},{"idSquare":43,"colourSquare":"white","positionSquare":43,"idPiece":null},{"idSquare":44,"colourSquare":"black","positionSquare":44,"idPiece":null},{"idSquare":45,"colourSquare":"white","positionSquare":45,"idPiece":null},{"idSquare":46,"colourSquare":"black","positionSquare":46,"idPiece":null},{"idSquare":47,"colourSquare":"white","positionSquare":47,"idPiece":null},{"idSquare":48,"colourSquare":"white","positionSquare":48,"idPiece":16},{"idSquare":49,"colourSquare":"black","positionSquare":49,"idPiece":17},{"idSquare":50,"colourSquare":"white","positionSquare":50,"idPiece":18},{"idSquare":51,"colourSquare":"black","positionSquare":51,"idPiece":19},{"idSquare":52,"colourSquare":"white","positionSquare":52,"idPiece":20},{"idSquare":53,"colourSquare":"black","positionSquare":53,"idPiece":21},{"idSquare":54,"colourSquare":"white","positionSquare":54,"idPiece":22},{"idSquare":55,"colourSquare":"black","positionSquare":55,"idPiece":23},{"idSquare":56,"colourSquare":"black","positionSquare":56,"idPiece":24},{"idSquare":57,"colourSquare":"white","positionSquare":57,"idPiece":25},{"idSquare":58,"colourSquare":"black","positionSquare":58,"idPiece":26},{"idSquare":59,"colourSquare":"white","positionSquare":59,"idPiece":27},{"idSquare":60,"colourSquare":"black","positionSquare":60,"idPiece":28},{"idSquare":61,"colourSquare":"white","positionSquare":61,"idPiece":29},{"idSquare":62,"colourSquare":"black","positionSquare":62,"idPiece":30},{"idSquare":63,"colourSquare":"white","positionSquare":63,"idPiece":31}],"piecesList":[{"id":0,"pieceType":"rook","colour":"black","position":0,"onBoard":true},{"id":1,"pieceType":"knight","colour":"black","position":1,"onBoard":true},{"id":2,"pieceType":"bishop","colour":"black","position":2,"onBoard":true},{"id":3,"pieceType":"queen","colour":"black","position":3,"onBoard":true},{"id":4,"pieceType":"king","colour":"black","position":4,"onBoard":true},{"id":5,"pieceType":"bishop","colour":"black","position":5,"onBoard":true},{"id":6,"pieceType":"knight","colour":"black","position":6,"onBoard":true},{"id":7,"pieceType":"rook","colour":"black","position":7,"onBoard":true},{"id":8,"pieceType":"pawn","colour":"black","position":8,"onBoard":true},{"id":9,"pieceType":"pawn","colour":"black","position":9,"onBoard":true},{"id":10,"pieceType":"pawn","colour":"black","position":10,"onBoard":true},{"id":11,"pieceType":"pawn","colour":"black","position":11,"onBoard":true},{"id":12,"pieceType":"pawn","colour":"black","position":12,"onBoard":true},{"id":13,"pieceType":"pawn","colour":"black","position":13,"onBoard":true},{"id":14,"pieceType":"pawn","colour":"black","position":14,"onBoard":true},{"id":15,"pieceType":"pawn","colour":"black","position":15,"onBoard":true},{"id":16,"pieceType":"pawn","colour":"white","position":48,"onBoard":true},{"id":17,"pieceType":"pawn","colour":"white","position":49,"onBoard":true},{"id":18,"pieceType":"pawn","colour":"white","position":50,"onBoard":true},{"id":19,"pieceType":"pawn","colour":"white","position":51,"onBoard":true},{"id":20,"pieceType":"pawn","colour":"white","position":52,"onBoard":true},{"id":21,"pieceType":"pawn","colour":"white","position":53,"onBoard":true},{"id":22,"pieceType":"pawn","colour":"white","position":54,"onBoard":true},{"id":23,"pieceType":"pawn","colour":"white","position":55,"onBoard":true},{"id":24,"pieceType":"rook","colour":"white","position":56,"onBoard":true},{"id":25,"pieceType":"knight","colour":"white","position":57,"onBoard":true},{"id":26,"pieceType":"bishop","colour":"white","position":58,"onBoard":true},{"id":27,"pieceType":"queen","colour":"white","position":59,"onBoard":true},{"id":28,"pieceType":"king","colour":"white","position":60,"onBoard":true},{"id":29,"pieceType":"bishop","colour":"white","position":61,"onBoard":true},{"id":30,"pieceType":"knight","colour":"white","position":62,"onBoard":true},{"id":31,"pieceType":"rook","colour":"white","position":63,"onBoard":true}],"score":"0 - 0","turn":false}';
const obj = JSON.parse(txt);
var pieces = obj.piecesList;
//console.log(obj);
console.log(pieces);


//     var imgPawn = new Image();
//     var imgRook = new Image();
//     var imgKnight = new Image();
//     var imgBishop = new Image();
//     var imgQueen = new Image();
//     var imgKing = new Image();
//     var imgPawnW = new Image();
//     var imgRookW = new Image();
//     var imgKnightW = new Image();
//     var imgBishopW = new Image();
//     var imgQueenW = new Image();
//     var imgKingW = new Image();


//     function loadImages() {
//         imgPawn.src = "../img/blackPawn.png";
//         imgRook.src = "../img/blackRook.png";
//         imgKnight.src = "../img/blackKnight.png";
//         imgBishop.src = "../img/blackBishop.png";
//         imgQueen.src = "../img/blackQueen.png";
//         imgKing.src = "../img/blackKing.png";
//         imgPawnW.src = "../img/whitePawn.png";
//         imgRookW.src = "../img/whiteRook.png";
//         imgKnightW.src = "../img/whiteKnight.png"
//         imgBishopW.src = "../img/whiteBishop.png";
//         imgQueenW.src = "../img/whiteQueen.png";
//         imgKingW.src = "../img/whiteKing.png";
//     }

var black_pawn = "../img/blackPawn.png";
var black_rook = "../img/blackRook.png";
var black_knight = "../img/blackKnight.png";
var black_bishop = "../img/blackBishop.png";
var black_queen = "../img/blackQueen.png";
var black_king = "../img/blackKing.png";
var white_pawn = "../img/whitePawn.png";
var white_rook = "../img/whiteRook.png";
var white_knight = "../img/whiteKnight.png"
var white_bishop = "../img/whiteBishop.png";
var white_queen = "../img/whiteQueen.png";
var white_king = "../img/whiteKing.png";



let draw_square = (x, y, color) => {
    let e = document.createElement("div");
    e.id = 'sq-' + x + '-' + y;
    e.style.width = s + 'px';
    e.style.height = s + 'px';
    e.style.background = color;

    e.style.position = 'absolute';
    e.style.left = (x * s) + "px";
    e.style.top = (y * s) + "px";
    document.body.appendChild(e);

    return e;
}

let draw_piece = (x, y, color, pieceType) => {
    let e = document.createElement("img");
    e.id = 'piece' +x + '-' + y;
    e.style.width = (s / 2) + 'px';
    e.style.height = (s / 2) + 'px';
    e.style.background = 'gray';

    switch(pieceType){
        case 'pawn':
            if(color == 'black')
                e.src = black_pawn;
            else
                e.src = white_pawn;
            break;
        case 'rook':
            if(color == 'black')
                e.src = black_rook;
            else
                e.src = white_rook;
            break;
        case 'knight':
            if(color == 'black')
                e.src = black_knight;
            else
                e.src = white_knight;
            break;
        case 'bishop':
            if(color == 'black')
                e.src = black_bishop;
            else
                e.src = white_bishop;
            break;
        case 'queen':
            if(color == 'black')
                e.src = black_queen;
            else
                e.src = white_queen;
            break;
        case 'king':
            if(color == 'black')
                e.src = black_king;
            else
                e.src = white_king;
            break;
    }

    e.style.position = 'absolute';
    e.style.left = (x * s + s / 4) + "px";
    e.style.top = (y * s + s / 4) + "px";

    e.addEventListener('mousedown', start_dd);
    e.addEventListener('mouseup', stop_dd);

    document.body.appendChild(e);

    return e;
}

let dd_pending = false;
let dd_el = null;
let dd_offset_x;
let dd_offset_y;

let start_dd = (e) =>
{
    //console.log('start', e);
    let tag = e.path[0];
    if (tag.id && tag.id.indexOf('piece-') !== -1)
    {
        dd_pending = true;
        dd_el = e.path[0];

        dd_el.style.zIndex = '999';

        dd_offset_x = e.x - dd_el.offsetLeft;
        dd_offset_y = e.y - dd_el.offsetTop;

        //console.log('valori'+ dd_el.offsetLeft, dd_el.offsetTop, e.x, e.y, dd_offset_x, dd_offset_y );
    }
}

let stop_dd = (e) => {
    console.log('stop', e);

    console.log(dd_el.style.left);

    dd_el.style.left = Math.floor( (e.pageX - dd_offset_x + s/4 ) / s ) * s + s/4 + "px";
    dd_el.style.top = Math.floor( (e.pageY - dd_offset_y + s/4 ) / s ) * s + s/4 + "px";
    data = JSON.stringify({a: 1, b: 'Textual content'})//model de la delia
    if (post_data('localhost/tabla/piesa/mutare',data)){
        post_data('localhost/tabla/piesa',data);
    }
    dd_pending = false;
    dd_el.style.zIndex = '100';
}

window.addEventListener('mouseup', stop_dd);

var during_dd = (e) => {
    if (!dd_pending) return ;

    let x = e.pageX;
    let y = e.pageY;

    console.log(x, y);

    dd_el.style.left = (x - dd_offset_x) + 'px';
    dd_el.style.top = (y - dd_offset_y) + 'px';
}

window.onload = function() {
    this.addEventListener('mousemove', during_dd);
}

for (let i = 0; i < 8; i++) {
    m[i] = []
    for (let j = 0; j < 8; j++) {
        m[i][j] = draw_square(i, j, colors[(i + j) % 2]);
    }
}

function renderBoard(piece) {
    pieces.forEach(piece => {
            console.log(piece);
            var e;
            if (piece.onBoard == 1) {
                var position = piece.position;
                var y = Math.floor(position / 8);
                var x = position % 8;
                var pos = y*8 + x;
                console.log(y);
                console.log("pozitia este"+pos);
                draw_piece(x, y, piece.colour, piece.pieceType);
            }
        }
    );
}

const message = 'Hello world' // Try edit me

function post_data(url,data) {
    //const url = 'https://jsonplaceholder.typicode.com/posts'

    const request={
        headers:{
            "content-type":"application/json"
        },
        body:data,
        method:"POST"
    };

    //fetch(url, request).then(data=>{renderBoard()})  //aici in loc de return data.json ar trebui apelata functia care sa faca ceva cu jsonul
    //    .then(res=>{console.log(res)})
    //    .catch(error=>{console.log(error)})
}

console.log(post_data())  // va da odata undefined pt ca inca nu e gata requestul si dupa va arata si rezultatul

function get_data(){
    const url = 'http://localhost/tabla';
    // fetch(url).then(data=>{renderBoard(data)})  //aici in loc de return data.json ar trebui apelata functia care sa faca ceva cu jsonul
    //     .then(res=>{console.log(res)})
    //     .catch(error=>{console.log(error)})
}

console.log(get_data());
//get_data();
// va da odata undefined pt ca inca nu e gata requestul si dupa va arata si rezultatul

// const message = 'Hello world' // Try edit me
//
// function post_data() {//dupa asta, apelez get data
//     const txt = 'https://jsonplaceholder.typicode.com/posts'
//
//     const request= {
//         headers:{
//             "content-type":"application/json"
//         },
//         //body:JSON.stringify('{"piece":{'+' "id":10,'+'"colour":"black","positionSquare":51,'+'"onBoard":10,'x''},'+"new_pos":18'+'}')
//         method:"POST"
//     };
//
//     fetch(obj, request).then(data=>{return renderBoard()})  //aici in loc de return data.json ar trebui apelata functia care sa faca ceva cu jsonul
//         .then(res=>{console.log(res)})
//         .catch(error=>{console.log(error)})
// }
//
//
//
// console.log(post_data())  // va da odata undefined pt ca inca nu e gata requestul si dupa va arata si rezultatul
//
// function get_data(){//apelat prima data si dupa operatii
//     const url = 'https://jsonplaceholder.typicode.com/posts'
//     fetch(url).then(data=>{return data.json()})  //aici in loc de return data.json ar trebui apelata functia care sa faca ceva cu jsonul
//         .then(res=>{console.log(res)})
//         .catch(error=>{console.log(error)})
// }
//
// console.log(get_data()) // va da odata undefined pt ca inca nu e gata requestul si dupa va arata si rezultatul

// public static Image getImageFromArray(int[] pixels, int width, int height) {
//     BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//     WritableRaster raster = (WritableRaster) image.getData();
//     raster.setPixels(0,0,width,height,pixels);
//     return image;
// }