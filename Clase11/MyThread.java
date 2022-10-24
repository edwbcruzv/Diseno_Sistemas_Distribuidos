public class MyThread implements Runnable{

    public int var_compartida=0;
    private int num;

    public void run() {

        String nombre=Thread.currentThread().getName();

        for (int i = 0; i < this.num; i++) {  

            if (nombre=="incrementar") {
                this.var_compartida++;
            } else {
                this.var_compartida--;
            }
            
        }
        // System.out.println("Hilo:"+nombre+",var="+this.var_compartida);
        
    }

    public void modifica(int n){
        this.num=n;
    }
    
}
