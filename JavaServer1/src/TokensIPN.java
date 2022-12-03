/*
 * Proyecto 3
 * Cruz Villalba Edwin Bernardo
 * Grupo 4CM11
 * 
 */

public class TokensIPN {

    // public static void main(String[] args) {
    //     tokens(10,"IPNOS");
    // }


    public static int tokens(int n,String word){
        System.out.println(n);
        
        int cont=0;

        StringBuilder obj1=new StringBuilder();

        for (int i = 0; i < n; i++) {
            char letras[]= new char[word.length()];
            for (int j = 0; j < letras.length; j++) {

                if (j!=letras.length) {
                    letras[j]=(char)(Math.random()*(64-90+1)+90);
                } else {
                    letras[j]=(char)32;
                }
            }
            obj1.append(letras);
        }

        for (int i = obj1.indexOf(word); i >=0; i=obj1.indexOf(word,i+1)) {
            cont++;

        }
        return cont;

    }

    
}
