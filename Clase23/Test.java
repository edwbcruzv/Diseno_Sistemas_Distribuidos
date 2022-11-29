
import java.lang.Runnable;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * 1. Crear una tarea (objeto ejecutable) para ejecutar
 * 2. Crear grupo de ejecutores usando ejecutores
 * 3. Pasar tareas al grupo de ejecutores
 * 4. Apague el grupo de ejecutores
 */
public class Test {
    // Maximum number of threads in thread pool
    static int MAX_T = 3;
    public static void main(String[] args) {

        int tareas=Integer.parseInt(args[0]);
        // creates five tasks

        ArrayList<Runnable> list_runn=new ArrayList<Runnable>();

        for (int i = 0; i < tareas; i++) {
            Runnable r = new Task("Cliente "+(i+1));
            list_runn.add(r);
        }

        // creates a thread pool with MAX_T no. of
        // threads as the fixed pool size(Step 2)
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
        // System.out.println("MAX_T="+args[0]);
        // passes the Task objects to the pool to execute (Step 3)
        long startTime, finishTime;
        startTime = System.currentTimeMillis();

        for (Runnable runnable : list_runn) {
            pool.execute(runnable);
            
        }
        // pool shutdown ( Step 4)
        pool.shutdown();

        finishTime = System.currentTimeMillis();
        System.out.println("Tareas=" + args[0] + ", Tiempo de ejecucion:" + Long.toString(finishTime - startTime) + " milisegundos");
        
    }
}
