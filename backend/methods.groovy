package backend
@GrabConfig(systemClassLoader=true)
@Grab('mysql:mysql-connector-java:8.0.28')

import java.sql.*; 
import groovy.sql.Sql 


class methods {    
    
    def do_sql(String command){          
        def sql = Sql.newInstance('jdbc:mysql://localhost:3306/chessDB', 
         'testuser', 'test123', 'com.mysql.jdbc.Driver')
        def res = sql.rows(command) 
        sql.close()
        return res

    }

    def get_squares(){
      def squares = do_sql('SELECT * FROM tblSquares')  
      return squares

    }
    
    def get_pieces(){
      def pieces = do_sql('SELECT * FROM tblPieces')
      return pieces
    }

    def get_scorew(){
      def scorew = do_sql('SELECT scoreW FROM tblBoard')
      return scorew
    }

    def get_scoreb(){
      def scoreb = do_sql('SELECT scoreB FROM tblBoard')
      return scoreb
    }

    def get_score(){
      String score = "white : ${get_scorew()[0].scoreW} - black : ${get_scoreb()[0].scoreB}"
      return score
    }

    def get_turn(){
      def turn = do_sql('SELECT turn FROM tblBoard')
      return turn
    }

     //def resSquares = get_squares() 
     //def resPieces = get_pieces()
     //def resScorew = get_scorew() 
     //def resScoreb = get_scoreb()
     //String res3 = "white : ${res3w[0].scoreW} - black : ${res3b[0].scoreB}"
     //def res4 = get_turn()[0].turn
    
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

    def bool = is_it_valid() //nu asa!! */

    def bool = true
    
    def do_the_move(){
      def maxIdPieces = do_sql('SELECT MAX(id) FROM tblPieces')
      def key = "MAX(id)"
      def max = maxIdPieces[key][0]
      if (bool){
        //println max
        
        for(int i = 0; i <= max; i++){
          println i
          //if( ce_primesc_de_la_anca.id == get_pieces()[i].id){
            //return get_pieces()[i]
            //update    in bd          
            //get_matrix()
          //}
        }
      }

      //else ??
 
    }











    //def addun(x, y) { 
    //println x+y
    //}
    

}