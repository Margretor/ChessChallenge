def ci = 3
def cj = 3
def di = 0
def dj = 0
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





//ptv1

for(int k = 1; k<v1.size; k++){
    if(pos == v1[k]){
       //-- if culoare black
       //--else if white
      // else bool =true
        bool = true
        break
    }
}

if(bool == true){
    for(int n = 1; n <v1.size-1; n++){
        ii = v1[n] / 8   
        jj = v1[n] % 8
        if(mat[ii][jj] != -1){
           bool = false
        }     
    } 
} 

//pt v2
if(bool = false){
    for(int k = 1; k<v2.size; k++){
    if(pos == v2[k]){
       -- if culoare black
       --else if white
       else bool =true
        bool = true
        break
        }
    }

    if(bool == true){
        for(int n = 1; n <v2.size-1; n++){
            ii = v2[n] / 8   
            jj = v2[n] % 8
            if(mat[ii][jj] != -1){
                bool = false
            }     
        } 
    } 
}

//pt v3
if(bool = false){
    for(int k = 1; k<v3.size; k++){
        if(pos == v3[k]){
        -- if culoare black
        --else if white
        else bool =true
            bool = true
            break
            }
    }

    if(bool == true){
        for(int n = 1; n <v2.size-1; n++){
            ii = v3[n] / 8   
            jj = v3[n] % 8
            if(mat[ii][jj] != -1){
                bool = false
            }     
        } 
    } 
}

//pt v4
if(bool = false){
    for(int k = 1; k<v4.size; k++){
    if(pos == v4[k]){
       -- if culoare black
       --else if white
       else bool =true
        bool = true
        break
        }
    }

    if(bool == true){
        for(int n = 1; n <v2.size-1; n++){
            ii = v4[n] / 8   
            jj = v4[n] % 8
            if(mat[ii][jj] != -1){
                bool = false
            }     
        } 
    } 
}




















println(bool)

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


