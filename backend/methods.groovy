package backend
@GrabConfig(systemClassLoader=true)
@Grab('mysql:mysql-connector-java:8.0.28')

import java.sql.*; 
import groovy.sql.Sql 
import groovy.json.*


class methods {    
    
    def get_sql(String command){          
        def sql = Sql.newInstance('jdbc:mysql://localhost:3306/chessDB', 
         'testuser', 'test123', 'com.mysql.jdbc.Driver')
        def res = sql.rows(command) 
        sql.close()
        return res
    }

    def do_sql(String command){          
        def sql = Sql.newInstance('jdbc:mysql://localhost:3306/chessDB', 
         'testuser', 'test123', 'com.mysql.jdbc.Driver')
        def res = sql.execute(command) 
        sql.close()
    }

    def get_squares(){
      def squares = get_sql('SELECT * FROM tblSquares')  
      return squares

    }
    
    def get_pieces(){
      def pieces = get_sql('SELECT * FROM tblPieces')
      return pieces
    }

    def get_scorew(){
      def scorew = get_sql('SELECT scoreW FROM tblBoard')
      return scorew
    }

    def get_scoreb(){
      def scoreb = get_sql('SELECT scoreB FROM tblBoard')
      return scoreb
    }

    def get_score(){
      String score = "white : ${get_scorew()[0].scoreW} - black : ${get_scoreb()[0].scoreB}"
      return score
    }

    def get_turn(){
      def turn = get_sql('SELECT turn FROM tblBoard')
      return turn
    }
    
     def get_mapa(){
      def mapa = ["squaresList" : get_squares(), "piecesList" : get_pieces(), "score" : get_score(), "turn" : get_turn()[0].turn]
      return mapa
    }

    def get_matrix(){
      def vector = []
      for(int k = 0; k < 64; k++){
        if(get_squares()[k].idPiece == null){
          vector[k] = -1
        }
        else
          vector[k] = get_squares()[k].idPiece
      }
      
      //return vector
      
      def m = new Integer[8][8]         
      assert m.size() == 8
      for(int i = 0; i < 8; i++){
          for(int j = 0; j < 8; j++){
                m[i][j] = vector[i*8+j]
          }
      }

      return m
    }
    
   /* is_valid_pawn(){

        
    }
  
    def is_it_valid(){
      for(int i = 0; i < maxIdPiece; i++){
        if( ce_primesc_de_la_anca.piesa.id == res2[i].id){
          switch (res2[i].pieceType){
          case "rook":
          case "knight":
          case "bishop":
          case "queen":
          case "king":
          case "pawn":
          }               
        }
      }     
      return bool
    }
     */

    
    def ce_primesc_de_la_anca = '{"piece":{'+ 
                '"id": 19,'+
                '"pieceType": "pawn",'+
                '"colour": "white",'+
                '"position": 51,'+
                '"onBoard": true'+
            '},'+
    '"new_pos": 43'+
    '}'
    def data = new JsonSlurper().parseText(ce_primesc_de_la_anca)

    //UPDATE tblPieces SET position = data.new_pos WHERE id = data.piece.id;
    //UPDATE tblSquares SET idPiece = NULL WHERE idSquare = data.piece.position;
    //UPDATE tblSquares SET idPiece = data.piece.id WHERE idSquare = data.new_pos;



    //is_it_valid()
    //def bool = is_it_valid() //nu asa!!
    //if (bool) do_the_move
    //else ???


    def do_the_move(){         
        def pieces = get_pieces()    
        for(int i = 0; i < pieces.size(); i++){          
          if( data.piece.id == pieces[i].id){
            do_sql('UPDATE tblSquares SET idPiece = NULL WHERE idSquare = ' + data.piece.position)
            def squares = get_squares()
            for(int j = 0; j < squares.size(); j++){          
              if( data.new_pos == squares[j].idSquare){
                if(squares[j].idPiece != null){ 
                  //get_sql('SELECT * FROM tblPieces WHERE id = ' + squares[j].idPiece).color
                  do_sql('DELETE FROM tblPieces WHERE id = '+ squares[j].idPiece)
                  break
                }
              }
            }
            do_sql('UPDATE tblPieces SET position = ' + data.new_pos + ' WHERE id = ' + data.piece.id)
            do_sql('UPDATE tblSquares SET idPiece = ' + data.piece.id + ' WHERE idSquare = ' + data.new_pos)
            break      
          }
        }
    }





    //pt reset do_sql(string(piese.sql))








    //def addun(x, y) { 
    //println x+y
    //}
    

}