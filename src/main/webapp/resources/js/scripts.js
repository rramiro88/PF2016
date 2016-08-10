
 //sacado de http://jsfiddle.net/76yRN/1/
                    jQuery(document).ready(function () {



                        jQuery.fn.swap = function (b) {
                            // method from: http://blog.pengoworks.com/index.cfm/2008/9/24/A-quick-and-dirty-swap-method-for-jQuery
                            b = jQuery(b)[0];
                            var a = this[0];
                            var t = a.parentNode.insertBefore(document.createTextNode(''), a);
                            b.parentNode.insertBefore(a, b);
                            t.parentNode.insertBefore(b, t);
                            t.parentNode.removeChild(t);
                            return this;
                        };

                        
                        
                        $(".dragdrop").draggable({zIndex:2500, appendTo: "body"});

                        $(".dragdrop").droppable({
                            accept: ".dragdrop",
                            activeClass: "drag-activo",
                            hoverClass: "drag-hover",
                            drop: function (event, ui) {
                                
                                
                                var draggable = ui.draggable,
                                        droppable = $(this),
                                        dragPos = draggable.position(),
                                        dropPos = droppable.position();
                                
                                

                                draggable.css({
                                    left: dropPos.left + 'px',
                                    top: dropPos.top + 'px',
                                    zIndex: 999
                                });

                                droppable.css({
                                    left: dragPos.left + 'px',
                                    top: dragPos.top + 'px',
                                    zIndex: 999
                                });
                                draggable.swap(droppable);
                            }
                        });



                    });


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


