import java.io.Serializable;
import java.util.ArrayList;

public class PoligonoIrreg implements Serializable{
    public ArrayList<Coordenada> vertices;


    public PoligonoIrreg(int n){
        int min=0;
        int max=100;

        // ((Math.random()*((max-min)+1))+min)
        this.vertices = new ArrayList<Coordenada>();
        for(int i=0;i<n;i++){
            this.vertices.add(new Coordenada((int)((Math.random()*((max-min)+1))+min),(int)((Math.random()*((max-min)+1))+min)));
            // System.out.println(this.vertices.get(i));
        }

    }

    public void addVertexRandom(){
        int min = 0;
        int max = 100;
        this.vertices.add(new Coordenada((int) ((Math.random() * ((max - min) + 1)) + min),
                (int) ((Math.random() * ((max - min) + 1)) + min)));
    }

    public String toString( ) {
        int n=vertices.size();

        String cad="\nPoligono Irregular de "+n+" lados\n";
        for(int i=0;i<n;i++){
            cad=cad+"V"+(i+1)+":"+this.vertices.get(i)+"\n";
        }

        return cad;
    }

}