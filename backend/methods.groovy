package backend
@GrabConfig(systemClassLoader=true)
@Grab('mysql:mysql-connector-java:8.0.28')

import java.sql.*; 
import groovy.sql.Sql 


class methods {    
    //static res_piece = []   //dispae pt endpoint
    //static 
    //def res = []         //dispae pt endpoint


    //static
    def do_sql(String command){          //def do_sql pt endpoint
        def sql = Sql.newInstance('jdbc:mysql://localhost:3306/chessDB', 
         'testuser', 'test123', 'com.mysql.jdbc.Driver')
        def res = sql.rows(command, 1,3) 
        sql.close()
        return res

    }

    /*static void main(String[] args){        //dispare pt endpoint
        def res1 = do_sql('SELECT * FROM tblSquares')       //ramane in methods pt endpoint
        def res2 = do_sql('SELECT * FROM tblPieces')        //ramane in methods pt endpoint
        def res3w = do_sql('SELECT scoreW FROM tblBoard WHERE movementNr = (SELECT MAX(movementNr) FROM tblBoard)')   //ramane in methods pt endpoint
        def res3b = do_sql('SELECT scoreB FROM tblBoard WHERE movementNr = (SELECT MAX(movementNr) FROM tblBoard)')   //ramane in methods pt endpoint
        def res4 = do_sql('SELECT turn FROM tblBoard WHERE movementNr = (SELECT MAX(movementNr) FROM tblBoard)')      //ramane in methods pt endpoint
        println res1
        println res2
        println res3w
        println res3b
        println res4
        def result = [res1, res2, res3w, res3b, res4]
        println result
    }*/

    def get_squares(){
      def res1 = do_sql('SELECT * FROM tblSquares')  
      return res1

    }
    
    def get_pieces(){
      def res2 = do_sql('SELECT * FROM tblPieces')
      return res2
    }

    def get_scorew(){
      def res3w = do_sql('SELECT scoreW FROM tblBoard WHERE movementNr = (SELECT MAX(movementNr) FROM tblBoard)')
      return res3w
    }

    def get_scoreb(){
      def res3b = do_sql('SELECT scoreB FROM tblBoard WHERE movementNr = (SELECT MAX(movementNr) FROM tblBoard)')
      return res3b
    }

    def get_turn(){
      def res4 = do_sql('SELECT turn FROM tblBoard WHERE movementNr = (SELECT MAX(movementNr) FROM tblBoard)')
      return res4
    }

  //construiesc mapa din cele 5 resuri

    def addun(x, y) { 
    println x+y
    }
   
   // def display(){
    //    start()
    //  return res_piece[0].colour
   // }

}