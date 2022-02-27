def vector = []
for(i = 0; i < 64; i++){
    vector[i] = i
}
println vector




def m = new Integer[8][8]         
assert m.size() == 8
for(i = 0; i < 8; i++){
    for(j = 0; j < 8; j++){
        m[i][j] = vector[i*8+j]
    }
}

println("The array is below:");
  
for(i = 0; i < 8 ; i++) {
   
   for( j = 0; j < 8; j++) {
    
    print(m[i][j] + " ");
    
   }
   println();
  }



