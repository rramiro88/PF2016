



function verPosicionActual(objetoHTML) {
    
    var elemento = $(objetoHTML);
    var posicion = elemento.position();
    
    var a = document.getElementById("j_idt18:j_idt21");
    a.value = posicion.left + "x" + posicion.top;
    
    
    
    alert("left: " + posicion.left + ", top: " + posicion.top);
    return false;
    
    
    
    

}


