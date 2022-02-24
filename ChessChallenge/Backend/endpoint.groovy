@Grapes([
  @Grab('io.ratpack:ratpack-groovy:1.9.0'),
  @Grab('org.slf4j:slf4j-simple:1.7.30')
  //grab mysql conn
])

import groovy.sql.Sql
import static ratpack.groovy.Groovy.ratpack
//def map


ratpack{
    serverConfig {
        port(5056)
    }

    handlers{
        get("tabla"){
           render "hello dawn"
        //metoda1 -> care creeaza patratele (script groovy separat) map = functie()
        //metoda 2 -> creeaza piesele
        //se creeaza p. si se pun in memorie in structura mea de date care o sa fie un map(apoi fac tranzitia catre bd)
        //convertesc map ul in json =>se obt json-ul care se returneaza ca raspuns
        }

        get("tabla/patratica/piese/:id"){
            render "astea sunt piesele"
        }
       
    }
}







