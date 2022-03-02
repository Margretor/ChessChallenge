def ci = 4
def cj = 5
def nr = 0
def j = cj
def v1 = []  //stanga sus
def v2 = []  //dreapta jos
def v3 = []  //dreapta sus
def v4 = []  //stanga jos
//v[0]  = pos

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


println ("v1 este:")
for(int k = 0; k<v1.size; k++){
println(v1[k])
}
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


