public class Ejercicio3 {
    public static void main(String[] args){
        
        System.out.printf("Valor es:%.10f\n",Math.PI);
        int array[]=new int[15];

        for(int i=0;i<15;i++){
            array[i]=i+1;
            System.out.println(array[i]);
        }
        System.out.println();
        for(int i=14;i>=0;i--){
            if(i%2==0){
                System.out.println(array[i]);
            }
        }

    }
}