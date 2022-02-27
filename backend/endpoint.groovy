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
//println(mapa1)
Method.do_the_move()



//print the matrix of idSquares:
/*def mat = Method.get_matrix()
for(int i = 0; i < 8 ; i++) {
        
        for(int j = 0; j < 8; j++) {
          
          print(mat[i][j] + " ");
          
        }
        println();
}
*/


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


