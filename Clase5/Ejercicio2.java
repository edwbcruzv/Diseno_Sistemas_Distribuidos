import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;



// Aun incompleto
public class Ejercicio2 {
    public static void main(String[] args) {
        
        StringBuilder cadenota = new StringBuilder();
        for(int x=0; x<Integer.parseInt(args[0]) ;x++){

            cadenota.append((char)(Math.random()*(65-90+1)+90));
            cadenota.append((char)(Math.random()*(65-90+1)+90));
            cadenota.append((char)(Math.random()*(65-90+1)+90));
            cadenota.append(" ");
        }
        // System.out.println(cadenota);
        System.out.println("Token:"+ buscador(cadenota));

    }

    public static int buscador(StringBuilder cadenota) {

        int token=0;
        // System.out.println(cadenota.indexOf("IPN"));

        for (int i = 0; i < cadenota.length(); i=i+4) {
            // System.out.println(cadenota.substring(i,i+4));
            if(cadenota.substring(i,i+3).toString()=="IPN"){
            
                token++;
            }    
        }
        return token;
    }


}
