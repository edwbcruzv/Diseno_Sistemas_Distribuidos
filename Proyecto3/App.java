/*
 * Proyecto 3
 * Cruz Villalba Edwin Bernardo
 * Grupo 4CM11
 * 
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    private static final String WORKER_ADDRESS_1 = "http://localhost:5001/searchtoken";
    private static final String WORKER_ADDRESS_2 = "http://localhost:5002/searchtoken";
    private static final String WORKER_ADDRESS_3 = "http://localhost:5003/searchtoken";

    public static void main(String[] args) {
        Aggregator aggregator = new Aggregator();

        List<String> results = aggregator.sendTasksToWorkers(Arrays.asList(WORKER_ADDRESS_1,WORKER_ADDRESS_2,WORKER_ADDRESS_3),
                Tareas());

        int i=1;
        for (String result : results) {
            System.out.printf("Tarea[%d]=>%s\n",i++,result);
        }
    }

    public static ArrayList<String> Tareas(){
        ArrayList<String> arrl=new ArrayList<String>();

        int j=0;
        for (int i = 65; i <= 90; i++) {
            ;
            arrl.add(String.valueOf((int)Math.floor(Math.random() * (1757600 - 17576000 + 1) + 17576000))+","+(char)i+(char)i+(char)i);

            System.out.println("Tarea["+(j+1)+"]="+arrl.get(j++));

        }

        return arrl;
    }
}
