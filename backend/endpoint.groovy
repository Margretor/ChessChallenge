package backend
import backend.methods

@Grapes([
  @Grab('io.ratpack:ratpack-groovy:1.9.0'),
  @Grab('org.slf4j:slf4j-simple:1.7.30'),
  @Grab(group='io.ratpack', module='ratpack-core', version='1.9.0') 
])

import groovy.sql.Sql
import static ratpack.groovy.Groovy.*
//import ratpack.jackson.JacksonModule          
import static ratpack.jackson.Jackson.jsonNode

def Method = new methods()
def mapa1 = Method.get_mapa()
//println(mapa1.piecesList[1].id)
Method.do_the_move()
//println(Method.get_mapa().squaresList[51])
//println(Method.get_mapa().squaresList[43])
//println(Method.data.piece.position)
//println(Method.get_mapa())





//print the matrix of idSquares:
/*def mat = Method.get_matrix()
for(int i = 0; i < 8 ; i++) {
        
        for(int j = 0; j < 8; j++) {
          
          print(mat[i][j] + " ");
          
        }
        println();
}*/



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

        post("tabla/piesa") {                             
          def postBody = parse jsonNode()
          //println(postBody.getClass())
          postBody.then{
            String message -> println(message)
          }
          render "Hello world! ${postBody.toString()}"
        }    
        
        get("tabla/patratica/piese/:id"){
            render "astea sunt piesele"
        }
       
      }
}


*/



