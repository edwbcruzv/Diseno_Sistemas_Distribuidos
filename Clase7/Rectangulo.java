public class Rectangulo extends Figura{
    private Coordenada superiorIzq, inferiorDer;

    

    public Rectangulo(){

        superiorIzq = new Coordenada(0,0);

        inferiorDer = new Coordenada(0,0);

    }

    

    public Rectangulo(double xSupIzq, double ySupIzq, double xInfDer, double yInfDer){

        superiorIzq = new Coordenada(xSupIzq, ySupIzq);

        inferiorDer = new Coordenada(xInfDer, yInfDer);        

    }

    public Rectangulo(Coordenada c1,Coordenada c2){
        superiorIzq = c1;
        inferiorDer = c2;
    }

    

    //Metodo getter de la coordenada superior izquierda

    public Coordenada superiorIzquierda( ) { return superiorIzq; }

 

    //Metodo getter de la coordenada inferior derecha

    public Coordenada inferiorDerecha( ) { return inferiorDer; }

    public float area(){
        alto = this.superiorIzquierda().ordenada() - this.inferiorDerecha().ordenada();

        ancho = this.inferiorDerecha().abcisa() - this.superiorIzquierda().abcisa();
        return ancho*alto;
    }
    

    //Sobreescritura del método de la superclase objeto para imprimir con System.out.println( )

    @Override

    public String toString( ) {

        return "Esquina superior izquierda: " + superiorIzq + "\tEsquina superior derecha:" + inferiorDer + "\n";

    }


}