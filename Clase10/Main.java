import java.util.ArrayList;
import java.util.Iterator;

public class Main{



    public static void main(String[] args){
        ArrayList<String> list_curp;
        int n=Integer.parseInt(args[0]);


        CURP genCURP=new CURP();

        list_curp=new ArrayList<String>();
        for(int i=0;i<n;i++){

            list_curp.add(genCURP.getCURP());
            System.out.println(list_curp.get(i));
        }                   

        // System.out.println("CURP = " + getCURP());


        Iterator<String> itr=list_curp.iterator();
        
        
        // System.out.println();
        // System.out.println("Se eliminaran las "+args[1].charAt(0));
        // while(itr.hasNext()) {
        // String i = itr.next();
        //     // System.out.println(i.substring(10,11));
        // if((i.substring(10,11)).charAt(0)==args[1].charAt(0)) {
        //     itr.remove();
        //     // System.out.println("se borro");
        //     }
        // }

        // for(int i=0;i<list_curp.size();i++){

        //     // list_curp.add(genCURP.getCURP());
        //     System.out.println(list_curp.get(i));
        // } 

        while(itr.hasNext()) {
        String i = itr.next();
        if((i.substring(0,4))==temp_curp.substring(0,4))){}
            // metodo de acomodamiento
            }
        }

    }



}