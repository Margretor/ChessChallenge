package backend
@GrabConfig(systemClassLoader=true)
@Grab('mysql:mysql-connector-java:8.0.28')

import java.sql.*; 
import groovy.sql.Sql 
import groovy.json.*


class methods {    
    
        
    /*def ce_primesc_de_la_anca = {"piece":{'+
            '"id": 18,'+
            '"pieceType": "pawn",'+
           '"colour": "black",'+
            '"position": 9,'+
            '"onBoard": 1'+
            '},'+
	          '"new_pos": 26'+
	          '};
    def data = new JsonSlurper().parseText(ce_primesc_de_la_anca)*/

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

    def col(){
      def mat = get_matrix()
      def bool = false
      if(get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[6][3]).colour[0] == 'white'){
        bool = true
      }
      return bool
    }
    

    
    def is_valid_pawn(ci, cj, di, dj, data, mat){ 
      def bool = false
      def newi
      def newj
      println("sunt in pawn")
      //pt alb :
      if (data.piece.colour == "white") {
        println("aici-1")
        if((data.piece.position == 48) || (data.piece.position == 49) || (data.piece.position == 50) || (data.piece.position == 51) || (data.piece.position == 52) || (data.piece.position == 53) || (data.piece.position == 54) || (data.piece.position == 55)) {
          newi = ci - 1   
          newj = cj  
          println("aici0")     
          if (((newi == di) && (newj == dj))  && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)  &&  (mat[newi][newj] == -1)) {
            bool = true
            println("aici1")
          }
          else if((mat[ci-1][cj+1] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci-1][cj+1]).colour[0] == 'black')){
            newi = ci - 1   
            newj = cj + 1  
            if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8) )  { 
              bool = true
              println("aici3")
            }
          }
          else if((mat[ci-1][cj-1] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci-1][cj-1]).colour[0] == 'black')) {
            newi = ci - 1   
            newj = cj - 1 
            if (((newi == di) && (newj == dj)) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8))  {
              bool = true
              println("aici4")
            } 
          }
          else {
            newi = ci - 2   
            newj = cj                 
            if (((newi == di) && (newj == dj)) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)  && (mat[newi][newj] == -1) && (mat[newi+1][newj] == -1)){
              bool = true
              println("aici2")
            }
          }        
        }
        //daca pe diagonala dreapta avem piesa neagra
        else if((mat[ci-1][cj+1] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci-1][cj+1]).colour[0] == 'black')){
          newi = ci - 1   
          newj = cj + 1  
          if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8) )  { 
            bool = true
            println("aici3")
          }
        }
        //daca pe diagonala stanga avem piesa neagra
        else if((mat[ci-1][cj-1] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci-1][cj-1]).colour[0] == 'black')) {
          newi = ci - 1   
          newj = cj - 1 
          if (((newi == di) && (newj == dj)) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8))  {
            bool = true
            println("aici4")
          } 
        }
        else if(mat[ci-1][cj] == -1){
          newi = ci - 1 
          newj = cj     
          if (((newi == di) && (newj == dj)) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8) ) {
            bool = true
            println("aici5")
          }
        }
        else{
          bool = false
          println("aiciff")
        }
        
      }
      //pt negru:
      //if (data.piece.color == "black") {  //else
      else{
        if((data.piece.position == 8) || (data.piece.position == 9) || (data.piece.position == 10) || (data.piece.position == 11) || (data.piece.position == 12) || (data.piece.position == 13) || (data.piece.position == 14) || (data.piece.position == 15)) {
          println("poz init negru")
          newi = ci + 1   
          newj = cj       
          if (((newi == di) && (newj == dj)) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)  &&   (mat[newi][newj] == -1)) {
            bool = true
            ("aici6")
          }
          else if((mat[ci+1][cj-1] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci+1][cj-1]).colour[0] == 'white')){
            newi = ci + 1   
            newj = cj - 1  
            if (((newi == di) && (newj == dj)) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8))  { 
              bool = true
              ("aici8")
            }
          }
          else if((mat[ci+1][cj+1] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci+1][cj+1]).colour[0] == 'white')) {
            newi = ci + 1   
            newj = cj + 1 
            if (((newi == di) && (newj == dj)) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8))  { 
              bool = true
              ("aici9")
            } 
          }
          else {
            println("poz init +2")
            newi = ci + 2   
            newj = cj                 
            if (((newi == di) && (newj == dj)) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8) && (mat[newi][newj] == -1) && (mat[newi-1][newj] == -1)){
              bool = true
              ("aici7")
            }
          }        
        }
        //daca pe diagonala stanga avem piesa alba
        else if((mat[ci+1][cj-1] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci+1][cj-1]).colour[0] == 'white')){
          newi = ci + 1   
          newj = cj - 1  
          if (((newi == di) && (newj == dj)) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8))  { 
            bool = true
            ("aici8")
          }
        }
        //daca pe diagonala dreapta avem piesa alba
        else if((mat[ci+1][cj+1] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci+1][cj+1]).colour[0] == 'white')) {
          newi = ci + 1   
          newj = cj + 1 
          if (((newi == di) && (newj == dj)) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8))  { 
            bool = true
            ("aici9")
          } 
        }

        else if(mat[ci+1][cj] == -1){
          newi = ci + 1 
          newj = cj     
          if (((newi == di) && (newj == dj)) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
            bool = true
            ("aici10")
          }
        }
        else bool = false
        ("aici11")
      }

      return bool
    }

    def is_valid_rook(ci, cj, di, dj, data, mat){
      def bool = false
      def nr = 0
      def j = cj
      def i = ci
      def index = 0
      Integer ii  
      Integer jj 
      def pos = di * 8 + dj
      def v1 = []  //sus
      def v2 = []  //jos
      def v3 = []  //stanga
      def v4 = []  //dreapta

      for( i = ci; i>=0; i--){ 
        if(i>=0) {        
          v1[nr]= 8 * i + j
          nr++
        }
      }
      nr = 0
      j = cj
      for( i = ci; i<8; i++){
        if(i<=7) {        
          v2[nr]= 8 * i + j
          nr++
        }
      }
      nr = 0
      j = cj
      i = ci

      for( j = cj; j>=0; j--){ 
        if(j>=0) {        
          v3[nr]= 8 * i + j
          nr++
        }
      }
      nr = 0
      j = cj
      i = ci

      for( j = cj; j<8; j++){
        if(j<=7) {        
          v4[nr]= 8 * i + j
            nr++
        }
      }
      //pt alb
      if (data.piece.colour == "white") {
        //ptv1

        for(int k = 1; k<v1.size; k++){
            if(pos == v1[k]){
              if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'black')){
                bool = true
              }
              else if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'white')){
                bool = false
              }
              else {
                bool = true
              }
              index = k
              break
              //-- if culoare black
              //--else if white
              // else bool =true
            }
        }

        if(bool == true){
            for(int n = 1; n <index; n++){ //
                ii = v1[n] / 8   
                jj = v1[n] % 8
                if(mat[ii][jj] != -1){
                  bool = false
                }     
            } 
        } 

        //pt v2
        if(bool == false){
            for(int k = 1; k<v2.size; k++){
              if(pos == v2[k]){
                if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'black')){
                  bool = true
                }
                else if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'white')){
                  bool = false
                }
                else {
                  bool = true
                }
                index = k
                break
              }
            }

            if(bool == true){
                for(int n = 1; n <index; n++){
                    ii = v2[n] / 8   
                    jj = v2[n] % 8
                    if(mat[ii][jj] != -1){
                        bool = false
                    }     
                } 
            } 
        }

        //pt v3
        if(bool == false){
            for(int k = 1; k<v3.size; k++){
                if(pos == v3[k]){
                  if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'black')){
                    bool = true
                  }
                  else if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'white')){
                    bool = false
                  }
                  else {
                    bool = true
                  }
                  index = k
                  break
                }
            }

            if(bool == true){
                for(int n = 1; n <index; n++){
                    ii = v3[n] / 8   
                    jj = v3[n] % 8
                    if(mat[ii][jj] != -1){
                        bool = false
                    }     
                } 
            } 
        }

        //pt v4
        if(bool == false){ 
            for(int k = 1; k<v4.size; k++){
              if(pos == v4[k]){
                if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'black')){
                  bool = true
                }
                else if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'white')){
                  bool = false
                }
                else {
                  bool = true
                }
                index = k
                break
                }
            }

            if(bool == true){
                for(int n = 1; n <index; n++){
                    ii = v4[n] / 8   
                    jj = v4[n] % 8
                    if(mat[ii][jj] != -1){
                        bool = false
                    }     
                } 
            } 
        }
      }

      //pt negru
      else{
        //ptv1

        for(int k = 1; k<v1.size; k++){
            if(pos == v1[k]){
              if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'white')){
                bool = true
              }
              else if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'black')){
                bool = false
              }
              else {
                bool = true
              }
              index = k
              break
              //-- if culoare black
              //--else if white
              // else bool =true
            }
        }

        if(bool == true){
            for(int n = 1; n <index; n++){ //
                ii = v1[n] / 8   
                jj = v1[n] % 8
                if(mat[ii][jj] != -1){
                  bool = false
                }     
            } 
        } 

        //pt v2
        if(bool == false){
            for(int k = 1; k<v2.size; k++){
              if(pos == v2[k]){
                if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'white')){
                  bool = true
                }
                else if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'black')){
                  bool = false
                }
                else {
                  bool = true
                }
                index = k
                break
              }
            }

            if(bool == true){
                for(int n = 1; n <index; n++){
                    ii = v2[n] / 8   
                    jj = v2[n] % 8
                    if(mat[ii][jj] != -1){
                        bool = false
                    }     
                } 
            } 
        }

        //pt v3
        if(bool == false){
            for(int k = 1; k<v3.size; k++){
                if(pos == v3[k]){
                  if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'white')){
                    bool = true
                  }
                  else if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'black')){
                    bool = false
                  }
                  else {
                    bool = true
                  }
                  index = k
                  break
                }
            }

            if(bool == true){
                for(int n = 1; n <index; n++){
                    ii = v3[n] / 8   
                    jj = v3[n] % 8
                    if(mat[ii][jj] != -1){
                        bool = false
                    }     
                } 
            } 
        }

        //pt v4
        if(bool == false){ 
            for(int k = 1; k<v4.size; k++){
              if(pos == v4[k]){
                if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'white')){
                  bool = true
                }
                else if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'black')){
                  bool = false
                }
                else {
                  bool = true
                }
                index = k
                break
                }
            }

            if(bool == true){
                for(int n = 1; n <index; n++){
                    ii = v4[n] / 8   
                    jj = v4[n] % 8
                    if(mat[ii][jj] != -1){
                        bool = false
                    }     
                } 
            } 
        }
      }


      

      return bool
    }
    
    def is_valid_knight(ci, cj, di, dj, data, mat){
      def bool = false
      def newi
      def newj
      //pt alb:
      if (data.piece.colour == "white") {
          newi = ci - 2   
          newj = cj + 1
          if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
            if((mat[ci-2][cj+1] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci-2][cj+1]).colour[0] == 'black')){
              bool = true
              println("aici1")
            }
            else if((mat[ci-2][cj+1] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci-2][cj+1]).colour[0] == 'white')){
              bool = false
              println("aici2")
            }
            else bool = true
            println("aici3")
          }
          else {
            newi = ci - 1   
            newj = cj + 2                
            if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
              if((mat[ci-1][cj+2] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci-1][cj+2]).colour[0] == 'black')){
                bool = true
                println("aici4")
              }
              else if((mat[ci-1][cj+2] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci-1][cj+2]).colour[0] == 'white')){
                bool = false
                println("aici5")
              }
              else bool = true
              println("aici6")
            }
            else {
              newi = ci + 1     
              newj = cj + 2                 
              if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                if((mat[ci+1][cj+2] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci+1][cj+2]).colour[0] == 'black')){
                  bool = true
                  println("aici7")
                }
                else if((mat[ci+1][cj+2] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci+1][cj+2]).colour[0] == 'white')){
                  bool = false
                  println("aici8")
                }
                else bool = true
                println("aici9")
              }
              else {
                newi = ci + 2
                newj = cj + 1                
                if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                  println(get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'white')
                  println(mat[newi][newj])
                  if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'black')){
                    bool = true
                    println("aici10")
                  }
                  else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'white')){
                    bool = false
                    println("aici11")
                  }
                  else bool = true
                  println("aici12")
                }
                else {
                  newi = ci - 2  
                  newj = cj - 1                 
                  if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                    
                    if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'black')){
                      bool = true
                      println("aici122")
                    }
                    else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'white')){
                      bool = false
                      println("aici13")
                    }
                    else bool = true
                    println("aici14")
                  }
                  else {
                    newi = ci - 1   
                    newj = cj - 2             
                    if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                      if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'black')){
                        bool = true
                        println("aici15")
                      }
                      else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'white')){
                        bool = false
                        println("aici16")
                      }
                      else bool = true
                      println("aici17")
                    }
                    else {
                      newi = ci + 1
                      newj = cj - 2                 
                      if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                        if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'black')){
                          bool = true
                          println("aici18")
                        }
                        else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'white')){
                          bool = false
                          println("aici19")
                        }
                        else bool = true
                        println("aici20")
                      }
                      else {
                        newi = ci + 2
                        newj = cj - 1                 
                        if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                          if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'black')){
                            bool = true
                            println("aici21")
                          }
                          else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'white')){
                            bool = false
                            println("aici22")
                          }
                          else bool = true
                          println("aici23")
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
      else {
          newi = ci - 2   
          newj = cj + 1
          if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
            if((mat[ci-2][cj+1] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci-2][cj+1]).colour[0] == 'white')){
              bool = true
            }
            else if((mat[ci-2][cj+1] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci-2][cj+1]).colour[0] == 'black')){
              bool = false
            }
            else bool = true
          }
          else {
            newi = ci - 1   
            newj = cj + 2                
            if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
              if((mat[ci-1][cj+2] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci-1][cj+2]).colour[0] == 'white')){
                bool = true
              }
              else if((mat[ci-1][cj+2] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci-1][cj+2]).colour[0] == 'black')){
                bool = false
              }
              else bool = true
            }
            else {
              newi = ci + 1     
              newj = cj + 2                 
              if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                if((mat[ci+1][cj+2] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci+1][cj+2]).colour[0] == 'white')){
                  bool = true
                }
                else if((mat[ci+1][cj+2] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[ci+1][cj+2]).colour[0] == 'black')){
                  bool = false
                }
                else bool = true
              }
              else {
                newi = ci + 2
                newj = cj + 1                
                if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                  if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'white')){
                    bool = true
                  }
                  else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'black')){
                    bool = false
                  }
                  else bool = true
                }
                else {
                  newi = ci - 2  
                  newj = cj - 1                 
                  if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                    if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'white')){
                      bool = true
                    }
                    else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'black')){
                      bool = false
                    }
                    else bool = true
                  }
                  else {
                    newi = ci - 1   
                    newj = cj - 2             
                    if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                      if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'white')){
                        bool = true
                      }
                      else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'black')){
                        bool = false
                      }
                      else bool = true
                    }
                    else {
                      newi = ci + 1
                      newj = cj - 2                 
                      if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                        if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'white')){
                          bool = true
                        }
                        else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'black')){
                          bool = false
                        }
                        else bool = true
                      }
                      else {
                        newi = ci + 2
                        newj = cj - 1                 
                        if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                          if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'white')){
                            bool = true
                          }
                          else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'black')){
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
      def bool = false
      def nr = 0
      def j = cj
      def index = 0
      Integer ii  
      Integer jj 
      def pos = di * 8 + dj
      def v1 = []  //stanga sus
      def v2 = []  //dreapta jos
      def v3 = []  //dreapta sus
      def v4 = []  //stanga jos


      for(int i = ci; i>=0; i--){ 
          if((j>=0) && (i>=0)) {        
              v1[nr]= 8 * i + j
              j--
              nr++
          }
      }
      nr = 0
      j = cj

      for(int i = ci; i<8; i++){
          if((j<=7) && (i<=7)) {        
              v2[nr]= 8 * i + j
              j++
              nr++
          }
      }
      nr = 0
      j = cj

      for(int i = ci; i>=0; i--){ 
          if((j<=7) && (i>=0)) {        
              v3[nr]= 8 * i + j
              j++ 
              nr++
          }
      }
      nr = 0
      j = cj

      for(int i = ci; i<8; i++){
          if((j>=0) && (i<=7)) {        
              v4[nr]= 8 * i + j
              j--
              nr++
          }
      }

      //pt alb
      if (data.piece.colour == "white") {
        //ptv1

        for(int k = 1; k<v1.size; k++){
            if(pos == v1[k]){
              if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'black')){
                bool = true
              }
              else if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'white')){
                bool = false
              }
              else {
                bool = true
              }
              index = k
              break
              //-- if culoare black
              //--else if white
              // else bool =true
            }
        }

        if(bool == true){
            for(int n = 1; n <index; n++){ //
                ii = v1[n] / 8   
                jj = v1[n] % 8
                if(mat[ii][jj] != -1){
                  bool = false
                }     
            } 
        } 

        //pt v2
        if(bool == false){
            for(int k = 1; k<v2.size; k++){
              if(pos == v2[k]){
                if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'black')){
                  bool = true
                }
                else if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'white')){
                  bool = false
                }
                else {
                  bool = true
                }
                index = k
                break
              }
            }

            if(bool == true){
                for(int n = 1; n <index; n++){
                    ii = v2[n] / 8   
                    jj = v2[n] % 8
                    if(mat[ii][jj] != -1){
                        bool = false
                    }     
                } 
            } 
        }

        //pt v3
        if(bool == false){
            for(int k = 1; k<v3.size; k++){
                if(pos == v3[k]){
                  if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'black')){
                    bool = true
                  }
                  else if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'white')){
                    bool = false
                  }
                  else {
                    bool = true
                  }
                  index = k
                  break
                }
            }

            if(bool == true){
                for(int n = 1; n <index; n++){
                    ii = v3[n] / 8   
                    jj = v3[n] % 8
                    if(mat[ii][jj] != -1){
                        bool = false
                    }     
                } 
            } 
        }

        //pt v4
        if(bool == false){ 
            for(int k = 1; k<v4.size; k++){
              if(pos == v4[k]){
                if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'black')){
                  bool = true
                }
                else if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'white')){
                  bool = false
                }
                else {
                  bool = true
                }
                index = k
                break
                }
            }

            if(bool == true){
                for(int n = 1; n <index; n++){
                    ii = v4[n] / 8   
                    jj = v4[n] % 8
                    if(mat[ii][jj] != -1){
                        bool = false
                    }     
                } 
            } 
        }
      }

      //pt negru
      else{
        //ptv1

        for(int k = 1; k<v1.size; k++){
            if(pos == v1[k]){
              if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'white')){
                bool = true
              }
              else if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'black')){
                bool = false
              }
              else {
                bool = true
              }
              index = k
              break
              //-- if culoare black
              //--else if white
              // else bool =true
            }
        }

        if(bool == true){
            for(int n = 1; n <index; n++){ //
                ii = v1[n] / 8   
                jj = v1[n] % 8
                if(mat[ii][jj] != -1){
                  bool = false
                }     
            } 
        } 

        //pt v2
        if(bool == false){
            for(int k = 1; k<v2.size; k++){
              if(pos == v2[k]){
                if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'white')){
                  bool = true
                }
                else if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'black')){
                  bool = false
                }
                else {
                  bool = true
                }
                index = k
                break
              }
            }

            if(bool == true){
                for(int n = 1; n <index; n++){
                    ii = v2[n] / 8   
                    jj = v2[n] % 8
                    if(mat[ii][jj] != -1){
                        bool = false
                    }     
                } 
            } 
        }

        //pt v3
        if(bool == false){
            for(int k = 1; k<v3.size; k++){
                if(pos == v3[k]){
                  if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'white')){
                    bool = true
                  }
                  else if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'black')){
                    bool = false
                  }
                  else {
                    bool = true
                  }
                  index = k
                  break
                }
            }

            if(bool == true){
                for(int n = 1; n <index; n++){
                    ii = v3[n] / 8   
                    jj = v3[n] % 8
                    if(mat[ii][jj] != -1){
                        bool = false
                    }     
                } 
            } 
        }

        //pt v4
        if(bool == false){ 
            for(int k = 1; k<v4.size; k++){
              if(pos == v4[k]){
                if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'white')){
                  bool = true
                }
                else if((mat[di][dj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[di][dj]).colour[0] == 'black')){
                  bool = false
                }
                else {
                  bool = true
                }
                index = k
                break
                }
            }

            if(bool == true){
                for(int n = 1; n <index; n++){
                    ii = v4[n] / 8   
                    jj = v4[n] % 8
                    if(mat[ii][jj] != -1){
                        bool = false
                    }     
                } 
            } 
        }
      }

      return bool
    }

    def is_valid_queen(ci, cj, di, dj, data, mat){
      def bool = false
      if((is_valid_rook(ci, cj, di, dj, data, mat) == true) || (is_valid_bishop(ci, cj, di, dj, data, mat) == true)){
        bool = true
      }     
      return bool
    }

    def is_valid_king(ci, cj, di, dj, data, mat){
      def bool = false
      def newi
      def newj
      //pt alb:
      if (data.piece.colour == "white") {
          newi = ci - 1   //in fata
          newj = cj 
          if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
            if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'black')){
              bool = true
            }
            else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'white')){
              bool = false
            }
            else bool = true
          }
          else {
            newi = ci - 1   //diagonala dreapta in fata
            newj = cj + 1                
            if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
              if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'black')){
                bool = true
              }
              else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'white')){
                bool = false
              }
              else bool = true
            }
            else {
              newi = ci   //dreapta   
              newj = cj + 1                 
              if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'black')){
                  bool = true
                }
                else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'white')){
                  bool = false
                }
                else bool = true
              }
              else {
                newi = ci + 1  //diagonala dreapta in spate
                newj = cj + 1                
                if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                  if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'black')){
                    bool = true
                  }
                  else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'white')){
                    bool = false
                  }
                  else bool = true
                }
                else {
                  newi = ci + 1  //in spate   
                  newj = cj                  
                  if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                    if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'black')){
                      bool = true
                    }
                    else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'white')){
                      bool = false
                    }
                    else bool = true
                  }
                  else {
                    newi = ci   //in stanga   
                    newj = cj - 1                 
                    if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                      if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'black')){
                        bool = true
                      }
                      else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'white')){
                        bool = false
                      }
                      else bool = true
                    }
                    else {
                      newi = ci - 1  //diagonala stanga in fata
                      newj = cj - 1                 
                      if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                        if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'black')){
                          bool = true
                        }
                        else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'white')){
                          bool = false
                        }
                        else bool = true
                      }  
                      else {
                        newi = ci + 1  //diagonala stanga in spate
                        newj = cj - 1                 
                        if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                          if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'black')){
                            bool = true
                          }
                          else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'white')){
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
      else {
          newi = ci - 1   //in spate
          newj = cj 
          if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8))   {
            if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'white')){
                bool = true
              }
              else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'black')){
                bool = false
              }
              else bool = true
          }
          else {
            newi = ci - 1   //diagonala dreapta in spate
            newj = cj + 1                
            if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
              if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'white')){
                bool = true
              }
              else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'black')){
                bool = false
              }
              else bool = true
            }
            else {
              newi = ci   //dreapta   
              newj = cj + 1                 
              if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'white')){
                  bool = true
                }
                else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'black')){
                  bool = false
                }
                else bool = true
              }
              else {
                newi = ci + 1  //diagonala dreapta in fata
                newj = cj + 1                
                if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                  if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'white')){
                    bool = true
                  }
                  else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'black')){
                    bool = false
                  }
                  else bool = true
                }
                else {
                  newi = ci + 1  //in fata   
                  newj = cj                  
                  if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                    if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'white')){
                      bool = true
                    }
                    else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'black')){
                      bool = false
                    }
                    else bool = true
                  }
                  else {
                    newi = ci   //in stanga   
                    newj = cj - 1                 
                    if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                      if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'white')){
                        bool = true
                      }
                      else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'black')){
                        bool = false
                      }
                      else bool = true
                    }
                    else {
                      newi = ci - 1  //diagonala dreapta in spate
                      newj = cj - 1                 
                      if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                        if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'white')){
                          bool = true
                        }
                        else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'black')){
                          bool = false
                        }
                        else bool = true
                      } 
                      else {
                        newi = ci + 1  //diagonala stanga in fata
                        newj = cj - 1                 
                        if ((newi == di) && (newj == dj) && (newi >= 0) && (newi <8) && (newj >= 0) && (newj <8)) {
                          if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'white')){
                            bool = true
                          }
                          else if((mat[newi][newj] != -1) && (get_sql('SELECT * FROM tblPieces WHERE id = ' + mat[newi][newj]).colour[0] == 'black')){
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
      def rand = get_sql('SELECT turn FROM tblBoard').turn[0]
      println("turn: white - false  black - true")
      println(rand)
      for(int i = 0; i < 8 ; i++) {
        
        for(int j = 0; j < 8; j++) {
          
          print(mat[i][j] + " ");
          
        }
        println();
      }
      for(int k = 0; k < pieces.size(); k++){
        if( data.piece.id == pieces[k].id){   
          println("intra in valid")
          Integer i = data.piece.position / 8   
          Integer j = data.piece.position % 8
          Integer x = data.new_pos / 8
          Integer y = data.new_pos % 8
          if(rand){
            if(data.piece.colour != "black"){
              return false
            }
          }
          else{
            if(data.piece.colour != "white"){
              return false
            }
          }
          switch (data.piece.pieceType){
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
        }
      }     
      return bool
    }
    








    def do_the_move(data){         //turn  //do_the_move(json_din_post == ce primesc de la anca)
        def pieces = get_pieces()
        def rand = get_sql('SELECT turn FROM tblBoard').turn[0] 
        if(rand) {
          do_sql('UPDATE tblBoard SET turn = 0')
        }
        else{
          do_sql('UPDATE tblBoard SET turn = 1')
        }
        for(int i = 0; i < pieces.size(); i++){          
          if( data.piece.id == pieces[i].id){
            do_sql('UPDATE tblSquares SET idPiece = NULL WHERE idSquare = ' + data.piece.position)
            def squares = get_squares()
            for(int j = 0; j < squares.size(); j++){          
              if( data.new_pos == squares[j].idSquare){
                if(squares[j].idPiece != null){ 
                  //get_sql('SELECT * FROM tblPieces WHERE id = ' + squares[j].idPiece).color
                  do_sql('DELETE FROM tblPieces WHERE id = '+ squares[j].idPiece)
                  def scor = 0
                  if(rand) {
                    scor = get_sql('SELECT scoreB FROM tblBoard').scoreB[0]
                    scor = scor + 1
                    do_sql('UPDATE tblBoard SET scoreB = ' + scor )  //scor
                  }
                  else{
                    scor = get_sql('SELECT scoreW FROM tblBoard').scoreW[0]
                    scor = scor + 1
                    do_sql('UPDATE tblBoard SET scoreW = ' + scor )
                  }
                  println("white:"+ get_sql('SELECT scoreW FROM tblBoard').scoreW[0] + " black:"+ get_sql('SELECT scoreB FROM tblBoard').scoreB[0])
                  break
                }
              }
            }
            do_sql('UPDATE tblPieces SET position = ' + data.new_pos + ' WHERE id = ' + data.piece.id)
            do_sql('UPDATE tblSquares SET idPiece = ' + data.piece.id + ' WHERE idSquare = ' + data.new_pos)
            break      
          }
        }
        def mat = get_matrix()
        for(int i = 0; i < 8 ; i++) {
        
        for(int j = 0; j < 8; j++) {
          
          print(mat[i][j] + " ");
          
        }
        println();
      }
    }





    //pt reset do_sql(string(piese.sql))
    

}