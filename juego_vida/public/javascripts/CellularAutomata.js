class CellularAutomata{

    constructor(size,zoom,ctx){
        // tamaño del cuatro de la matriz
        this.size=size
        // contexto del canvas donde se mostrara
        this.ctx=ctx
        // matriz del automata
        this.cells=[]

        this.long_pixel=Math.trunc(zoom/size)
    }
    create(){
        for (let x = 0; x < this.size; x++) {
            const row = []
            for (let y = 0; y < this.size; y++) {
                // alive sera verdadero o false 
                const alive = Math.random() < 0.1
                row.push(alive)
                
            }
            this.cells.push(row) 
        }
    }
    // funcion que llamaremos cada cierto tiempo para darle vida al automata
    next(){
        this.print()
        this.evaluate()
    }
    // aqui aplicaremos las reglas del juego de la vida
    evaluate(){
        //creando una matriz auxiliar
        let cellsAux=new Array(this.size).fill('').map(()=>{
            return new Array(this.size).fill(false)
        })

        //aplicando las reglas a cada celula del automata
        for (let x = 0; x < this.size; x++) {
            for (let y = 0; y < this.size; y++) {

                // Tomando cuenta de los vecinos vivo
                let livingNeigbor=0
                
                /**
                 * celula y sus vecindades
                 * +---+---+---+
                 * | 1 | 2 | 3 |
                 * +---+---+---+
                 * | 4 |   | 5 |
                 * +---+---+---+
                 * | 6 | 7 | 8 |
                 * +---+---+---+
                 * 
                 */
                

                // con el siguiente caso aseguramos estabilidad dentro de la prontera
                // (frontera) (ṕosicion de la celda)
                        // (x>0 && y>0)
                // 1
                if ((x>0 && y>0) && (this.cells[x-1][y-1])) {
                    livingNeigbor++
                }
                // 2
                if ((y>0) && (this.cells[x][y-1])) {
                    livingNeigbor++
                }
                // 3
                if ((x<this.size-1 && y>0) && (this.cells[x+1][y-1])) {
                    livingNeigbor++
                }
                // 4
                if ((x>0) && (this.cells[x-1][y])) {
                    livingNeigbor++
                }
                // 5
                if ((x<this.size-1) && (this.cells[x+1][y])) {
                    livingNeigbor++
                }
                // 6
                if ((x>0 && y<this.size-1) && (this.cells[x-1][y+1])) {
                    livingNeigbor++
                }
                // 7
                if ((y<this.size-1) && (this.cells[x][y+1])) {
                    livingNeigbor++
                }
                // 8
                if ((x<this.size-1 && y<this.size-1) && (this.cells[x+1][y+1])) {
                    livingNeigbor++
                }
                

                if (this.cells[x][y]) {
                    // Si la celula esta viva  
                    if (livingNeigbor===2 || livingNeigbor===3 ) {
                        // se mantiene viva
                        cellsAux[x][y]=true
                    } else {
                        // muere
                        cellsAux[x][y]=false
                    }  
                } else {
                    // si la celula esta muerta
                    if (livingNeigbor===3) {
                        //revive
                        cellsAux[x][y]=true
                    } else {
                        //seguira muerta
                        cellsAux[x][y]=false
                    } 
                }

                



            }
        }

        // se hace el cambio de estado del automata
        this.cells=cellsAux
    }

    print(){
        // limpiamos el canvas
        this.ctx.clearRect(0,0,this.size,this.size)

        for (let x = 0; x < this.size; x++) {
            for (let y = 0; y < this.size; y++) {
                // coloreando celdas
                if(this.cells[x][y]){
                    //vivo
                    this.ctx.fillStyle="black"
                }else{
                    //muerto
                    this.ctx.fillStyle="white"
                }
                // pintando cuadro de nxn pixeles
                this.ctx.fillRect(x*this.long_pixel,y*this.long_pixel,this.long_pixel,this.long_pixel)

                // this.ctx.strokeStyle="black"
                // this.ctx.lineWidth=0.1
                // this.ctx.strokeRect(x*this.long_pixel,y*this.long_pixel,this.long_pixel,this.long_pixel)
            }
        }

    }
}

export default CellularAutomata
