package backend
@GrabConfig(systemClassLoader=true)
@Grab('mysql:mysql-connector-java:8.0.28')

import java.sql.*; 
import groovy.sql.Sql 


class methods {    
    static res_piece = []   //dispae pt endpoint
    static res = []         //dispae pt endpoint


    /*static start() {
         def sql = Sql.newInstance('jdbc:mysql://localhost:3306/chessDB', 
         'testuser', 'test123', 'com.mysql.jdbc.Driver')
         res_piece = sql.rows('SELECT * FROM tblPieces',1,3) 
        //println(res_piece[0].colour)
         println(res_piece)
         println("\n")
         def res_square = sql.rows('SELECT * FROM tblSquares')
        // println(res_square)
      }
    static void main(String[] args){
     // def  res = 
     start()
    //println res

    }*/




    static do_sql(String command){          //def do_sql pt endpoint
        def sql = Sql.newInstance('jdbc:mysql://localhost:3306/chessDB', 
         'testuser', 'test123', 'com.mysql.jdbc.Driver')
        res = sql.rows(command, 1,3) 
        sql.close()
        return res

    }

    static void main(String[] args){        //dispare pt endpoint
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
    }

    


    def addun(x, y) { 
    println x+y
    }
   
    def display(){
        start()
      return res_piece[0].colour
    }

}