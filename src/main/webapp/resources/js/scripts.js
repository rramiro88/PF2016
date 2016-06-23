



function verPosicionActual(objetoHTML) {
    
    var elemento = $(objetoHTML);
    var posicion = elemento.position();
    
    
    var c = objetoHTML.parentNode.childNodes;
    
    c[1].value=posicion.left + "x" + posicion.top;
    
      
    return false;
    

}

function cargarPosicionesActuales(){
    
    
    var listaDeJugadores = document.getElementById("listaJugadores");
    
    
    alert("Ejecucion del metodo cargar");
    
    
    
}


