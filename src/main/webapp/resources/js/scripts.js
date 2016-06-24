



function verPosicionActual(objetoHTML) {
    
    var c = objetoHTML.parentNode.childNodes;
        
    c[1].value=objetoHTML.style.left + "a" + objetoHTML.style.top;
      
    return false;
    
}

function cargarPosicionesActuales(){
    
    
    var listaDeJugadores = document.getElementById("listaJugadores");
    
       
    for(i=1; i<22;i+=2){
        listaDeJugadores.childNodes[i].childNodes[0].style.left=listaDeJugadores.childNodes[i].childNodes[1].value.split("a")[0];
        listaDeJugadores.childNodes[i].childNodes[0].style.top=listaDeJugadores.childNodes[i].childNodes[1].value.split("a")[1];
    }
    
    
}


