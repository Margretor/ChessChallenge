package backend
import backend.methods

@Grapes([
  @Grab('io.ratpack:ratpack-groovy:1.9.0'),
  @Grab('org.slf4j:slf4j-simple:1.7.30')
])

import groovy.sql.Sql
import static ratpack.groovy.Groovy.ratpack


def Method = new methods()
def mapa1 = Method.get_mapa()
def vector = Method.matrixx()

println(vector)

//Method.addun(5,6)
//println(Method.get_turn())
//println(Method.res3)


/*
ratpack{
    
    handlers{
        get("tabla"){
            //render "hello dawn"
            def json = new groovy.json.JsonBuilder(mapa1)
            render groovy.json.JsonOutput.prettyPrint(json.toString())
        }
        
        get("tabla/patratica/piese/:id"){
            render "astea sunt piesele"
        }
       
    }
}




*/

