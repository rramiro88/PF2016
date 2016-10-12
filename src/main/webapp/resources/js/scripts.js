

jQuery(document).ready(function () {



});

function cargaContextoCanvas(idCanvas) {
    var elemento = document.getElementById(idCanvas);
    if (elemento && elemento.getContext) {
        var contexto = elemento.getContext('2d');
        if (contexto) {
            return contexto;
        }
    }
    return false;
}




function limpiarCanvas() {

    canvas.width = canvas.width;

}





function dibujarJugador(x, y, numero) {

//centro de la cancha: 160 -- 160 250 circulo central
//50 90 DI, 160 90 DC, 270 90 DD,
//160 180 MP
//80 220 MI, MD 240 220, MC 160 290
//40 400 LI, 110 420 CT1, 210 420 CT2, 280 400 LD
//160, 470 ARQ
    var ctx = cargaContextoCanvas('canvas');

    ctx.beginPath();
    ctx.shadowOffsetX = -10; //desplazamiento horizontal sombra.
    ctx.shadowOffsetY = -8; //desplazamiento vertical sombra
    ctx.shadowColor = "black"; //color de sombra
    ctx.shadowBlur = 6; //dispersion de sombra.
    ctx.arc(x, y, 20, 0, 2 * Math.PI);
    ctx.fillStyle = '#6495ED';
    ctx.fill();

    ctx.beginPath();
    
    ctx.arc(x, y, 20, 0, 2 * Math.PI);
    ctx.strokeStyle = "black";
    ctx.lineWidth = 2;
    ctx.stroke();


    ctx.fillStyle = "black";
    ctx.font = "20px Verdana";

    if (numero > 9) {
        ctx.fillText(numero, x - 10, y + 4);
    } else {
        ctx.fillText(numero, x - 5, y + 4);
    }



}




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

    c[1].value = objetoHTML.style.left + "a" + objetoHTML.style.top;

    return false;

}

/**
 * 
 * Esta funcion carga las posiciones guardadas en el campo hidden
 * a la posicion real de las imagenes de los jugadores, 
 * asi se distribuyen en el campo como lo indica la tactica.
 * 
 */

function cargarPosicionesActuales() {


    var listaDeJugadores = document.getElementById("listaJugadores");


    for (i = 1; i < 22; i += 2) {
        listaDeJugadores.childNodes[i].childNodes[0].style.left = listaDeJugadores.childNodes[i].childNodes[1].value.split("a")[0];
        listaDeJugadores.childNodes[i].childNodes[0].style.top = listaDeJugadores.childNodes[i].childNodes[1].value.split("a")[1];
    }


}


