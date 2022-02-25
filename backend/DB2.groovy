import java.sql.*; //???  mysql-connector-java-5.1.38-bin
import groovy.sql.Sql 

class Example {
   static void main(String[] args) {
      // Creating a connection to the database
      //The Sql.newInstance method is used to establish a connection to the database.
      def sql = Sql.newInstance('jdbc:mysql://localhost:3306/boardDB', 
         'testuser', 'test123', 'com.mysql.jdbc.Driver')
			
      // Executing the query SELECT VERSION which gets the version of the database
      // Also using the eachROW method to fetch the result from the database
   
      sql.eachRow('SELECT VERSION()'){ row ->
         println row[0]
      }
		
      sql.close()  
   } 
} 