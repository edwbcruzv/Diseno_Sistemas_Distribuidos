public class Ejercicio15 {
    
    public static void main(String[] args) {
        MyThread h = new MyThread();

        h.modifica(Integer.parseInt(args[0]));
        
        Thread h1 = new Thread(h,"incrementar");
        Thread h2 = new Thread(h,"decrementar");
        

        h1.start();

        
        h1.join();
        
        h2.start();

        System.out.println("var_compartida="+h.var_compartida);

        
    }
}
