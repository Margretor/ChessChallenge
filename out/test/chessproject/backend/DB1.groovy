//grab mysql connecter -> sa ii specific ca foloseste driverul ala
//driver = bucata de cod pe care o import ce imi asigura comunicarea cu baza de date
//telnet = utilitar ce se conecteaza la un URL pe un port -> deschide o conexiune
import groovy.sql.Sql

try{
    def dbURL = 'jdbc:mysql://localhost:3306/boardDB'
    def dbUserName = 'testuser'
    def dbPassword = 'test123'
    def dbDriver = 'com.mysql.jdbc.Driver'
    log.info('Good')
    def db = Sql.newInstance(dbURL,dbUserName,dbPassword,dbDriver)
}catch(Exception e){
    log.info('DB Error')
    log.info(e.getMessage())
}finally{

}

//user nou in mysql 
//  ->dreptul sa se conecteze de oriunde