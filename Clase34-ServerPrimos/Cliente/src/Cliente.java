/*
 * Proyecto 3
 * Cruz Villalba Edwin Bernardo
 * Grupo 4CM11
 * 
 */
// import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cliente {
    private static final String WORKER_ADDRESS_1 = "http://localhost:5001/isprime";

    public static void main(String[] args) {
        Aggregator aggregator = new Aggregator();
        String task1 = "?numero=7351733&nucleos=4";
        String task2 = "?numero=73512&nucleos=2";
        String task3 = "?numero=7&nucleos=3";

        List<String> results = aggregator.sendTasksToWorkers(Arrays.asList(WORKER_ADDRESS_1),
                Arrays.asList(task1, task2, task3));

        int i=1;
        for (String result : results) {
            System.out.printf("Tarea[%d]=>%s\n",i++,result);
        }
    }
    
}
