package backend
import backend.methods

@Grapes([
  @Grab('io.ratpack:ratpack-groovy:1.9.0'),
  @Grab('org.slf4j:slf4j-simple:1.7.30')
])

import groovy.sql.Sql
import static ratpack.groovy.Groovy.ratpack

def Method = new methods()
//def map
Method.addun(5,6)
println(Method.get_turn())

/*def json = new groovy.json.JsonBuilder()

json rootKey: someMap

println "json output: "
println groovy.json.JsonOutput.prettyPrint(json.toString())*/







/*

ratpack{
    
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
*/





