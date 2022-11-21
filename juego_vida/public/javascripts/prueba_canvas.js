import CellularAutomata from './CellularAutomata.js'

console.log("lienzo")

/**
 * Pendiente:
 * 1000*1000
 * zoom
 * colores
 * scroll
 * timer
 * graficar las densidades (numero de 1s que van generando en base el tiempo)
 * numero de generaciones con su numero de 1s
 * salvar y guardar configuraciones (y lectura)
 * asignacion de reglas
 * media y varianza
 */

// seleccionando el elemento con el DOM
let $canvas=document.getElementById('Lienzo')

// lado en numero de celulas a mostrar
let num_cuatros=1000

// claridad de los pixeles
let zoom_pixel = 1000
// tamaño dentro del lienzo
$canvas.width=zoom_pixel
$canvas.height=zoom_pixel

// nos da las funciones de dibujo
let ctx=$canvas.getContext('2d')

const AC=new CellularAutomata(num_cuatros,zoom_pixel,ctx)
AC.create()
// AC.print()


// empieza el ciclo de vida, cada segundo cambiara
setInterval(()=>{
    AC.next()
},200)