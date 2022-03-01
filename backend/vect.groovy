def ci = 3
def cj = 3
def nr = 0
//def pos = 8*ci +cj
def v1 = []  //stanga sus
def v2 = []  //dreapta sus
def v3 = []  //dreapta jos
def v4 = []  //stanga jos
//v[0]  = pos

for(int i = ci; i>=0; i--){
    for(int j = cj; j>=0; j--){
        if(i == j) {        //diag principala
            v1[nr]=8*i+j
        }      
    }
    nr++
}
nr = 0

for(int i = ci; i>=0; i--){
    for(int j = cj; j<=8-ci+1; j++){
        if(j == ci+1 - i +1) {        //diag secundara
            v2[nr]=8*i+j
        }      
    }
    nr++
}
nr = 0

for(int i = ci; i<=7; i++){
    for(int j = cj; j<=7; j++){
        if(i == j) {                //diag principala
            v3[nr]=8*i+j
        }      
    }
    nr++
}
nr = 0

for(int i = ci; i>=0; i--){
    for(int j = cj; j>=0; j--){
        if(i == j) {
            v4[nr]=8*i+j
        }      
    }
    nr++
}


println ("v1 este:")
for(int k = 0; k<=ci; k++){
println(v1[k])
}
println ("v2 este:")
for(k = 0; k<=ci; k++){
println(v2[k])
}
println ("v3 este:")
for(k = 0; k<8-ci; k++){      //de unde 5
println(v3[k])
}
println ("v4 este:")
for(k = 0; k<=ci; k++){
println(v4[k])
}