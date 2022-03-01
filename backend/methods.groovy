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




    
    def is_valid_pawn(ci, cj, di, dj, data, mat){ 
      def bool = false
      
      //def squares = get_squares()
      //def pieces = get_pieces()
      //pt alb :
      if (data.piece.color == "white") {
        if((data.position == 48) || (data.position == 49) || (data.position == 50) || (data.position == 51) || (data.position == 52) || (data.position == 53) || (data.position == 54) || (data.position == 55)) {
          newi = ci - 1   
          newj = cj       
          if (((newi == di) && (newj == dj))  && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)  &&  (mat[newi][newj] == -1)) {
            bool = true
          }
          else {
            newi = ci - 2   
            newj = cj                 
            if (((newi == di) && (newj == dj)) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)  && (mat[newi][newj] == -1) && (mat[newi+1][newj] == -1)){
              bool = true
            }
          }        
        }
        //daca pe diagonala dreapta avem piesa neagra
        else if((mat[ci-1][cj+1] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci-1][cj+1]).color == 'black')){
          newi = ci - 1   
          newj = cj + 1  
          if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8) )  { 
            bool = true
          }
        }
        //daca pe diagonala stanga avem piesa neagra
        else if((mat[ci-1][cj-1] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci-1][cj-1]).color == 'black')) {
          newi = ci - 1   
          newj = cj - 1 
          if (((newi == di) && (newj == dj)) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8))  {
            bool = true
          } 
        }

        else{
          newi = ci - 1 
          newj = cj     
          if (((newi == di) && (newj == dj)) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)  &&  (mat[newi][newj] = -1) ) {
            bool = true
          }
        }
      }
      //pt negru:
      if (data.piece.color == "black") {  //else
        if((data.position == 8) || (data.position == 9) || (data.position == 10) || (data.position == 11) || (data.position == 12) || (data.position == 13) || (data.position == 14) || (data.position == 15)) {
          newi = ci + 1   
          newj = cj       
          if (((newi == di) && (newj == dj)) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)  &&   (mat[newi][newj] == -1)) {
            bool = true
          }
          else {
            newi = ci + 2   
            newj = cj                 
            if (((newi == di) && (newj == dj)) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8) && (mat[newi][newj] == -1) && (mat[newi+1][newj] == -1)){
              bool = true
            }
          }        
        }
        //daca pe diagonala stanga avem piesa alba
        else if((mat[ci+1][cj-1] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci+1][cj-1]).color == 'White')){
          newi = ci + 1   
          newj = cj - 1  
          if (((newi == di) && (newj == dj)) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8))  { 
            bool = true
          }
        }
        //daca pe diagonala dreapta avem piesa alba
        else if((mat[ci+1][cj+1] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci+1][cj+1]).color == 'white')) {
          newi = ci + 1   
          newj = cj + 1 
          if (((newi == di) && (newj == dj)) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8))  { 
            bool = true
          } 
        }

        else{
          newi = ci + 1 
          newj = cj     
          if (((newi == di) && (newj == dj)) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)  &&  (mat[newi][newj] = -1)) {
            bool = true
          }
        }
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

    def is_valid_rook(ci, cj, di, dj, data, mat){

      return bool
    }
    
    def is_valid_knight(ci, cj, di, dj, data, mat){
      def bool = false
      //pt alb:
      if (data.piece.color == "white") {
          newi = ci - 2   
          newj = cj + 1
          if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
            if((mat[ci-2][cj+1] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci-2][cj+1]).color == 'black')){
              bool = true
            }
            else if((mat[ci-2][cj+1] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci-2][cj+1]).color == 'white')){
              bool = false
            }
            else bool = true
          }
          else {
            newi = ci - 1   
            newj = cj + 2                
            if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
              if((mat[ci-1][cj+2] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci-1][cj+2]).color == 'black')){
                bool = true
              }
              else if((mat[ci-1][cj+2] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci-1][cj+2]).color == 'white')){
                bool = false
              }
              else bool = true
            }
            else {
              newi = ci + 1     
              newj = cj + 2                 
              if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                if((mat[ci+1][cj+2] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci+1][cj+2]).color == 'black')){
                  bool = true
                }
                else if((mat[ci+1][cj+2] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci+1][cj+2]).color == 'white')){
                  bool = false
                }
                else bool = true
              }
              else {
                newi = ci + 2
                newj = cj + 1                
                if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                  if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'black')){
                    bool = true
                  }
                  else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'white')){
                    bool = false
                  }
                  else bool = true
                }
                else {
                  newi = ci - 2  
                  newj = cj - 1                 
                  if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                    if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'black')){
                      bool = true
                    }
                    else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'white')){
                      bool = false
                    }
                    else bool = true
                  }
                  else {
                    newi = ci - 1   
                    newj = cj - 2             
                    if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                      if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'black')){
                        bool = true
                      }
                      else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'white')){
                        bool = false
                      }
                      else bool = true
                    }
                    else {
                      newi = ci + 1
                      newj = cj - 2                 
                      if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                        if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'black')){
                          bool = true
                        }
                        else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'white')){
                          bool = false
                        }
                        else bool = true
                      }
                      else {
                        newi = ci + 2
                        newj = cj - 1                 
                        if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                          if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'black')){
                            bool = true
                          }
                          else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'white')){
                            bool = false
                          }
                          else bool = true
                        }                   
                      }                    
                    } 
                  } 
                } 
              }  
            }    
          }     
      }

      //pt negru
      if (data.piece.color == "black") {
          newi = ci - 2   
          newj = cj + 1
          if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
            if((mat[ci-2][cj+1] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci-2][cj+1]).color == 'white')){
              bool = true
            }
            else if((mat[ci-2][cj+1] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci-2][cj+1]).color == 'black')){
              bool = false
            }
            else bool = true
          }
          else {
            newi = ci - 1   
            newj = cj + 2                
            if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
              if((mat[ci-1][cj+2] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci-1][cj+2]).color == 'white')){
                bool = true
              }
              else if((mat[ci-1][cj+2] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci-1][cj+2]).color == 'black')){
                bool = false
              }
              else bool = true
            }
            else {
              newi = ci + 1     
              newj = cj + 2                 
              if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                if((mat[ci+1][cj+2] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci+1][cj+2]).color == 'white')){
                  bool = true
                }
                else if((mat[ci+1][cj+2] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci+1][cj+2]).color == 'black')){
                  bool = false
                }
                else bool = true
              }
              else {
                newi = ci + 2
                newj = cj + 1                
                if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                  if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'white')){
                    bool = true
                  }
                  else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'black')){
                    bool = false
                  }
                  else bool = true
                }
                else {
                  newi = ci - 2  
                  newj = cj - 1                 
                  if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                    if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'white')){
                      bool = true
                    }
                    else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'black')){
                      bool = false
                    }
                    else bool = true
                  }
                  else {
                    newi = ci - 1   
                    newj = cj - 2             
                    if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                      if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'white')){
                        bool = true
                      }
                      else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'black')){
                        bool = false
                      }
                      else bool = true
                    }
                    else {
                      newi = ci + 1
                      newj = cj - 2                 
                      if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                        if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'white')){
                          bool = true
                        }
                        else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'black')){
                          bool = false
                        }
                        else bool = true
                      }
                      else {
                        newi = ci + 2
                        newj = cj - 1                 
                        if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                          if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'white')){
                            bool = true
                          }
                          else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'black')){
                            bool = false
                          }
                          else bool = true
                        }                   
                      }                    
                    } 
                  } 
                } 
              }  
            }    
          }     
      }
      return bool
    }

    def is_valid_bishop(ci, cj, di, dj, data, mat){

      return bool
    }

    def is valid_queen(ci, cj, di, dj, data, mat){
      def bool = false
      if((is_valid_rook(ci, cj, di, dj, data, mat) == true) || (is_valid_bishop(ci, cj, di, dj, data, mat) == true)){
        bool = true
      }     
      return bool
    }

    def is_valid_king(ci, cj, di, dj, data, mat){
      def bool = false
      //pt alb:
      if (data.piece.color == "white") {
          newi = ci - 1   //in fata
          newj = cj 
          if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
            if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'black')){
              bool = true
            }
            else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'white')){
              bool = false
            }
            else bool = true
          }
          else {
            newi = ci - 1   //diagonala dreapta in fata
            newj = cj + 1                
            if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
              if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'black')){
                bool = true
              }
              else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'white')){
                bool = false
              }
              else bool = true
            }
            else {
              newi = ci   //dreapta   
              newj = cj + 1                 
              if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'black')){
                  bool = true
                }
                else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'white')){
                  bool = false
                }
                else bool = true
              }
              else {
                newi = ci + 1  //diagonala dreapta in spate
                newj = cj + 1                
                if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                  if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'black')){
                    bool = true
                  }
                  else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'white')){
                    bool = false
                  }
                  else bool = true
                }
                else {
                  newi = ci + 1  //in spate   
                  newj = cj                  
                  if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                    if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'black')){
                      bool = true
                    }
                    else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'white')){
                      bool = false
                    }
                    else bool = true
                  }
                  else {
                    newi = ci   //in stanga   
                    newj = cj - 1                 
                    if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                      if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'black')){
                        bool = true
                      }
                      else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'white')){
                        bool = false
                      }
                      else bool = true
                    }
                    else {
                      newi = ci - 1  //diagonala stanga in fata
                      newj = cj - 1                 
                      if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                        if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'black')){
                          bool = true
                        }
                        else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'white')){
                          bool = false
                        }
                        else bool = true
                      }  
                      else {
                        newi = ci + 1  //diagonala stanga in spate
                        newj = cj - 1                 
                        if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                          if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'black')){
                            bool = true
                          }
                          else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'white')){
                            bool = false
                          }
                          else bool = true
                        }                   
                      }                  
                    } 
                  } 
                } 
              }  
            }    
          }     
      }


      //pt negru:
      if (data.piece.color == "black") {
          newi = ci - 1   //in spate
          newj = cj 
          if ((newi == di) && (newj == dj))   {
            if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'white')){
                bool = true
              }
              else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'black')){
                bool = false
              }
              else bool = true
          }
          else {
            newi = ci - 1   //diagonala dreapta in spate
            newj = cj + 1                
            if ((newi == di) && (newj == dj)) {
              if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'white')){
                bool = true
              }
              else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'black')){
                bool = false
              }
              else bool = true
            }
            else {
              newi = ci   //dreapta   
              newj = cj + 1                 
              if ((newi == di) && (newj == dj)) {
                if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'white')){
                  bool = true
                }
                else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'black')){
                  bool = false
                }
                else bool = true
              }
              else {
                newi = ci + 1  //diagonala dreapta in fata
                newj = cj + 1                
                if ((newi == di) && (newj == dj)) {
                  if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'white')){
                    bool = true
                  }
                  else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'black')){
                    bool = false
                  }
                  else bool = true
                }
                else {
                  newi = ci + 1  //in fata   
                  newj = cj                  
                  if ((newi == di) && (newj == dj)) {
                    if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'white')){
                      bool = true
                    }
                    else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'black')){
                      bool = false
                    }
                    else bool = true
                  }
                  else {
                    newi = ci   //in stanga   
                    newj = cj - 1                 
                    if ((newi == di) && (newj == dj)) {
                      if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'white')){
                        bool = true
                      }
                      else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'black')){
                        bool = false
                      }
                      else bool = true
                    }
                    else {
                      newi = ci - 1  //diagonala dreapta in spate
                      newj = cj - 1                 
                      if ((newi == di) && (newj == dj)) {
                        if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'white')){
                          bool = true
                        }
                        else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'black')){
                          bool = false
                        }
                        else bool = true
                      } 
                      else {
                        newi = ci + 1  //diagonala stanga in spate
                        newj = cj - 1                 
                        if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                          if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'white')){
                            bool = true
                          }
                          else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).color == 'black')){
                            bool = false
                          }
                          else bool = true
                        }                   
                      }                   
                    } 
                  } 
                } 
              }  
            }    
          }     
      }

      return bool
    }
  
    def is_it_valid(data){
      def mat = get_matrix()
      def bool = false
      def pieces = get_pieces()
      for(int i = 0; i < pieces.size(); i++){
        if( data.piece.id == pieces[i].id){   //data = ce primesc de la anca
        //x, y sa apartina 0, 7
          Integer i = data.piece.position / 8   
          Integer j = data.piece.position % 8
          Integer x = data.new_pos / 8
          Integer y = data.new_pos % 8
          switch (pieces[i].pieceType){
            case "rook": bool = is_valid_rook(i, j, x, y, data, mat)
            break
            case "knight": bool = is_valid_knight(i, j, x, y, data, mat)
            break
            case "bishop": bool = is_valid_bishop(i, j, x, y, data, mat)
            break
            case "queen": bool = is_valid_queen(i, j, x, y, data, mat)
            break
            case "king": bool = is_valid_king(i, j, x, y, data, mat)
            break
            case "pawn": bool = is_valid_pawn(i, j, x, y, data, mat)
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




    //is_it_valid()
    //def bool = is_it_valid() //nu asa!!
    //if (bool) do_the_move
    //else ???


    def do_the_move(data){         //turn  //do_the_move(json_din_post == ce primesc de la anca)
        def pieces = get_pieces()    
        for(int i = 0; i < pieces.size(); i++){          
          if( data.piece.id == pieces[i].id){
            do_sql('UPDATE tblSquares SET idPiece = NULL WHERE idSquare = ' + data.piece.position)
            def squares = get_squares()
            for(int j = 0; j < squares.size(); j++){          
              if( data.new_pos == squares[j].idSquare){
                if(squares[j].idPiece != null){ 
                  //get_sql('SELECT * FROM tblPieces WHERE id = ' + squares[j].idPiece).color
                  do_sql('DELETE FROM tblPieces WHERE id = '+ squares[j].idPiece)  //scor
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