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
      def res1 = do_sql('SELECT * FROM tblSquares')  
      return res1

    }
    
    def get_pieces(){
      def res2 = do_sql('SELECT * FROM tblPieces')
      return res2
    }

    def get_scorew(){
      def res3w = do_sql('SELECT scoreW FROM tblBoard')
      return res3w
    }

    def get_scoreb(){
      def res3b = do_sql('SELECT scoreB FROM tblBoard')
      return res3b
    }

    def get_turn(){
      def res4 = do_sql('SELECT turn FROM tblBoard')
      return res4
    }

   
     def res1 = get_squares() 
     def res2 = get_pieces()
     def res3w = get_scorew() 
     def res3b = get_scoreb()
     String res3 = "${res3w[0].scoreW} - ${res3b[0].scoreB}"
     def res4 = get_turn()[0].turn
   
    //def mapa = ["squaresList":res1, "piecesList": res2, "score":res3, "turn": res4]
    
    def get_mapa(){
      def mapa = ["squaresList":res1, "piecesList": res2, "score":res3, "turn": res4]
      return mapa
    }


  

    def addun(x, y) { 
    println x+y
    }

}