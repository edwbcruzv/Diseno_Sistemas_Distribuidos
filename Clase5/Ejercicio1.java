

public class Ejercicio1{
 

    public static void main(String[] args){
        System.out.println(args[0]);
        char[] cad=new char[4*Integer.parseInt(args[0])];

        int i=0;
        int token=0;
        for(int x=0; x<Integer.parseInt(args[0]) ;x++){

            cad[i++]=(char)(Math.random()*(65-90+1)+90);
            cad[i++]=(char)(Math.random()*(65-90+1)+90);
            cad[i++]=(char)(Math.random()*(65-90+1)+90);
            cad[i++]=' ';
            if(cad[i-4] == 'I'&&cad[i-3]=='P'&&cad[i-2]=='N'){
                token++;

            }
               
        }

            //IMPRIMIRCADENA
            for(i=0; i<4*Integer.parseInt(args[0]);i++){
                // System.out.println(cad[i]);


            }


    System.out.println("Token:"+ token);

    }
}



  /*  public char[] f1(){
        char[] cad=new char[4];
        cad[0]=(char)(Math.random()*(65-90+1)+90);
        cad[1]=(char)(Math.random()*(65-90+1)+90);
        cad[2]=(char)(Math.random()*(65-90+1)+90);
        cad[3]=' ';
        return cad;
    }*/
