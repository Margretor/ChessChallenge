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


    //conv de la poz la i si j in fct()
    //m[i][j] = vector[i*8+j]

    def get_i_j()

    
    def is_valid_pawn(ci, cj, di, dj, data){
      def bool = false
      def squares = get_squares()
      def pieces = get_pieces()
      //ci, cj
      //destini, dj,
      if (ce_primesc_de_la_anca.piece.color == "white") {
        if(pozitie initiala){
          newi = ci - 1   //newi1
          newj = cj       //newj1
          //newi poate fi si ci - 2  = newi2
          if (newi= di && newj =dj )&& && (mat[newi][newj] = -1) {
            bool = true
          }
          else {
          newi = ci - 2   //newi1
          newj = cj       
            if (newi= di && newj =dj ) && (mat[newi][newj] != -1) && (pieces[mat[newi][newj]].color == 'black')  {
              bool = true
            }
          }
          
        }
        
        else if(piesa neagra pe diagonala){
          newi = ci - 1   //
          newj = cj + 1  //mai poate fi si cj - 1 = newj0
           if (newi= di && newj =dj ) && (mat[newi][newj] = -1) && (mat[newi+1][newj] = -1) {
              bool = true
            }
        }
        else{
        newi = ci - 1  //newi1
        newj = cj   }    //newj1

      }
       if (ce_primesc_de_la_anca.piece.color == "black")  {         
        if(pozitie iniriala){
          newi = ci + 1   //poate fi si ci + 2
          newj = cj

        }
        if(piesa alba pe diagonala){
          newi = ci + 1
          newj = cj + 1  //mai poate fi si cj - 1

        }
        newi = ci + 1
        newj = cj

      }
      if((newi == di) /*||newi1 ==di)||...*/ && (newj == dj)){ //|| newi0, newj0
        bool = true
      }
       //if bool => are obstacole()?? 
      //bool -> modif
      //if bool => verific culoarea daca am piesa pe poz dorita

      /*
      if(bool){
        for( int k = 0; k < squares.size(); k++){
          if(squares[k].position == ce_primesc_de_la_anca.new_pos){  //gasesc patratica pe care vr sa mut piesa
            if((squares[k].position == n1) || (squares[k].position == n2)){
              if(squares[n2].idPiece == NULL)
            }
            if((squares[k].position == n3) || (squares[k].position == n4)){

            }
            else{
              bool = false
              break
            }
          }
        }
      }*/

      return bool
    }

    def is_valid_rook(ci, cj, di, dj){

      return bool
    }
    
    def is_valid_knight(ci, cj, di, dj){

      return bool
    }

    def is_valid_bishop(ci, cj, di, dj){

      return bool
    }

    def is valid_queen(ci, cj, di, dj){

      return bool
    }

    def is_valid_king(ci, cj, di, dj){

      return bool
    }
  
    def is_it_valid(){
      def bool = false
      def pieces = get_pieces()
      for(int i = 0; i < pieces.size(); i++){
        if( ce_primesc_de_la_anca.piece.id == pieces[i].id){   //data.id
          Integer i = ce_primesc_de_la_anca.piece.position / 8
          Integer j = ce_primesc_de_la_anca.piece.position % 8
          Integer x = ce_primesc_de_la_anca.new_pos / 8
          Integer y = ce_primesc_de_la_anca.new_pos % 8
          switch (pieces[i].pieceType){
            case "rook": bool = is_valid_rook(i, j, x, y)
            break
            case "knight": bool = is_valid_knight(i, j, x, y)
            break
            case "bishop": bool = is_valid_bishop(i, j, x, y)
            break
            case "queen": bool = is_valid_queen(i, j, x, y)
            break
            case "king": bool = is_valid_king(i, j, x, y)
            break
            case "pawn": bool = is_valid_pawn(i, j, x, y)
            break
          }
          break               
        }
      }     
      return bool
    }
    

    
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


    def do_the_move(){         //turn  //do_the_move(json_din_post == ce primesc de la anca)
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