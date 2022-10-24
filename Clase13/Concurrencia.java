public class Concurrencia{

    public String Letra;
    public Integer Veces;

    public Concurrencia(String letra, Integer veces){
        this.Letra=letra;
        this.Veces=veces;
    }

    public String toString() {
        return this.Letra+"="+this.Veces;
    }
    
}
