


/**
 * 
 * La funcion guarda en un capo hidden la posicion actual de la 
 * foto/imagen del jugador en la tactica. Por ejemplo, queda
 * guardado asi: 100pxa100px, donde la letra "a" se usa para 
 * dividir la propiedad left de la propiedad top (css).
 * 
 * @param {type} objetoHTML es el objeto imagen del jugador
 * @returns {Boolean} no se utiliza el retorno
 */
function verPosicionActual(objetoHTML) {
    
    var c = objetoHTML.parentNode.childNodes;
        
    c[1].value=objetoHTML.style.left + "a" + objetoHTML.style.top;
      
    return false;
    
}

/**
 * 
 * Esta funcion carga las posiciones guardadas en el campo hidden
 * a la posicion real de las imagenes de los jugadores, 
 * asi se distribuyen en el campo como lo indica la tactica.
 * 
 */

function cargarPosicionesActuales(){
    
    
    var listaDeJugadores = document.getElementById("listaJugadores");
    
       
    for(i=1; i<22;i+=2){
        listaDeJugadores.childNodes[i].childNodes[0].style.left=listaDeJugadores.childNodes[i].childNodes[1].value.split("a")[0];
        listaDeJugadores.childNodes[i].childNodes[0].style.top=listaDeJugadores.childNodes[i].childNodes[1].value.split("a")[1];
    }
    
    
}


