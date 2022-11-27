import java.util.Arrays;
import java.util.List;

public class App {
    private static final String WORKER_ADDRESS_1 = "http://localhost:5001/searchtoken";
    private static final String WORKER_ADDRESS_2 = "http://localhost:5002/searchtoken";

    public static void main(String[] args) {
        Aggregator aggregator = new Aggregator();
        String task1 = "17576000,IPN";
        String task2 = "1757600,SAL";
        String task3 = "700000,MAS";

        List<String> results = aggregator.sendTasksToWorkers(Arrays.asList(WORKER_ADDRESS_1, WORKER_ADDRESS_2),
                Arrays.asList(task1, task2,task3));

        int i=1;
        for (String result : results) {
            System.out.printf("task[%d] %s\n",i++,result);
        }
    }
}
