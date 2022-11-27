
import java.lang.Runnable;
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
    static int MAX_T = 0;
    public static void main(String[] args) {

        MAX_T=Integer.parseInt(args[0]);
        // creates five tasks
        Runnable r1 = new Task("Cliente 1");
        Runnable r2 = new Task("Cliente 2");
        Runnable r3 = new Task("Cliente 3");
        Runnable r4 = new Task("Cliente 4");
        Runnable r5 = new Task("Cliente 5");

        // creates a thread pool with MAX_T no. of
        // threads as the fixed pool size(Step 2)
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
        // System.out.println("MAX_T="+args[0]);
        // passes the Task objects to the pool to execute (Step 3)
        long startTime, finishTime;
        startTime = System.currentTimeMillis();
        pool.execute(r1);
        pool.execute(r2);
        pool.execute(r3);
        pool.execute(r4);
        pool.execute(r5);

        // pool shutdown ( Step 4)
        pool.shutdown();

        finishTime = System.currentTimeMillis();
        System.out.println("MAX_T=" + args[0] + ", Tiempo de ejecucion:" + Long.toString(finishTime - startTime) + " milisegundos");
        
    }
}
