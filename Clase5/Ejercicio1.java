
public class Ejercicio1{
 

    public static void main(String[] args){
        System.out.println(args[0]);
        char[] cadenota=new char[4*Integer.parseInt(args[0])];

        int i=0;
        for(int x=0; x<Integer.parseInt(args[0]) ;x++){

            cadenota[i++]=(char)(Math.random()*(65-90+1)+90);
            cadenota[i++]=(char)(Math.random()*(65-90+1)+90);
            cadenota[i++]=(char)(Math.random()*(65-90+1)+90);
            cadenota[i++]=' ';
            
               
        }

            
        // for(i=0; i<4*Integer.parseInt(args[0]);i++){
        //     System.out.println(cadenota[i]);
        // }

    System.out.println("Token:"+ buscador(cadenota));

    }

    public static int buscador(char[] cadenota) {

        int token=0;
        for (int i = 0; i < cadenota.length; i=i+4) {
            if(cadenota[i] == 'I'&&cadenota[i+1]=='P'&&cadenota[i+2]=='N'){
                token++;
    
            }    
        }
        return token;
    }
}
