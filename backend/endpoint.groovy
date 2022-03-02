package backend
import backend.methods

@Grapes([
  @Grab('io.ratpack:ratpack-groovy:1.9.0'),
  @Grab('org.slf4j:slf4j-simple:1.7.30'),
  @Grab(group='io.ratpack', module='ratpack-core', version='1.9.0') 
])

import groovy.sql.Sql
import static ratpack.groovy.Groovy.*         
import static ratpack.jackson.Jackson.jsonNode

def Method = new methods()
//def mapa1 = Method.get_mapa()

//Method.do_the_move()

//println(Method.get_mapa().piecesList[1].id)
//println(Method.get_mapa().squaresList[51])
//println(Method.get_mapa().squaresList[43])
//println(Method.data.piece.position)

//ce primesc de la anca:
def data = Method.data
 Integer ii = data.piece.position / 8   
  Integer jj = data.piece.position % 8
  Integer x = data.new_pos / 8
  Integer y = data.new_pos % 8
  def mat = Method.get_matrix()

//print the matrix of idSquares:
/*
for(int i = 0; i < 8 ; i++) {
        
        for(int j = 0; j < 8; j++) {
          
          print(Method.get_matrix()[i][j] + " ");
          
        }
        println();
}
*/
println(data)
//println(Method.col())
//println(ii)
//println(jj)
//println(x)
//println(y)

println(Method.is_valid_king(ii, jj, x, y, data, mat))
//Method.is_valid_pawn(ii, jj, x, y, data, mat)
//println(Method.is_it_valid(data))

//if(Method.is_it_valid(data) == true){
//  Method.do_the_move(data)
//}





/*


ratpack{
    
    handlers{
        get("tabla"){
            //render "hello dawn"
            def json = new groovy.json.JsonBuilder(Method.get_mapa())
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
        

        //is_it_valid()
    //def bool = is_it_valid() //nu asa!!
    //if (bool) do_the_move
    //else ???
        post("piesa/something") {
          getRequest().getBody().then({ data ->
            String text = data.getText();
            //reverse parse din text json in mapa
            if(is_it_valid(text) == true){
              do_the_move(text)
            }
            println(text)
            render(text)
          })
        }

        get("tabla/patratica/piese/:id"){
            render "astea sunt piesele"
        }
       
      }
}
*/

/*
for(int i = 0; i < 8 ; i++) {
        
        for(int j = 0; j < 8; j++) {
          
          print(Method.get_matrix()[i][j] + " ");
          
        }
        println();
}*/







