public class PoligonoIrreg{
    private Coordenada[] vertices;


    public PoligonoIrreg(int n){

        for(int i=0;i<n;i++){
            vertices[i]=new Coordenada(0,0);
        }

    }

    public boolean anadeVertice(Coordenada c){
        vertices.append(c);

    }

    public String toString( ) {

        String cad="[";
        int n=vertices.length();
        for(int i=0;i<n;i++){
            cad=cad;
        }

        return cad+"]";
    }

}