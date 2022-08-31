public class PoligonoIrreg{
    private Coordenada[] vertices;


    public PoligonoIrreg(int n){
        int min=0;
        int max=100;

        // ((Math.random()*((max-min)+1))+min)
        this.vertices=new Coordenada[n];
        for(int i=0;i<n;i++){
            this.vertices[i]=new Coordenada((int)((Math.random()*((max-min)+1))+min),(int)((Math.random()*((max-min)+1))+min));
            System.out.println(this.vertices[i]);
        }

    }

    // public boolean anadeVertice(Coordenada c){
    //     vertices.append(c);

    // }

    public String toString( ) {
        int n=vertices.length;

        String cad="\nPoligono Irregular de "+n+" lados\n";
        for(int i=0;i<n;i++){
            cad=cad+"V"+(i+1)+":"+this.vertices[i]+"\n";
        }

        return cad;
    }

}