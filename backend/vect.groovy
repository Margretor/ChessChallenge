def ci = 4
def cj = 5
def di = 3
def dj = 3
def nr = 0
def j = cj
def bool = false
Integer ii  
Integer jj 
def v1 = []  //stanga sus
def v2 = []  //dreapta jos
def v3 = []  //dreapta sus
def v4 = []  //stanga jos
def pos = di * 8 + dj


for(int i = ci; i>=0; i--){ 
    if((j>=0) && (i>=0)) {        
        v1[nr]= 8 * i + j
        j--
        nr++
    }
}
nr = 0
j = cj
for(int i = ci; i<8; i++){
    if((j<=7) && (i<=7)) {        
        v2[nr]= 8 * i + j
        j++
        nr++
    }
}
nr = 0
j = cj

for(int i = ci; i>=0; i--){ 
    if((j<=7) && (i>=0)) {        
        v3[nr]= 8 * i + j
        j++ 
        nr++
    }
}
nr = 0
j = cj

for(int i = ci; i<8; i++){
    if((j>=0) && (i<=7)) {        
        v4[nr]= 8 * i + j
        j--
        nr++
    }
}
println(v1[1]%8)

for(int k = 0; k<v1.size; k++){
    if(pos == v1[k]){
        bool = true
    }
    if(bool == true){
        for(int n = 1; n <2; n++){
            ii = v1[n] / 8   
            jj = v2[n] % 8
            //if(mat[ii][jj] == -1){
             //   bool = true
            //}
            
            
        }
        

    } 
}
println(ii)
        println(ii)

/*
println(bool)

println ("v1 este:")
for(int k = 0; k<v1.size; k++){
println(v1[k])
}*/
/*
println ("v2 este:")
for(k = 0; k<v2.size; k++){
println(v2[k])
}
println ("v3 este:")
for(k = 0; k<v3.size; k++){      
    println(v3[k])
}

println ("v4 este:")
for(k = 0; k<v4.size; k++){
    println(v4[k])
}
*/

