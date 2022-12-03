import java.util.Arrays;
import java.util.List;

public class App {
    private static final String WORKER_ADDRESS_1 = "http://34.71.29.149/searchtoken";

    public static void main(String[] args) {
        Aggregator aggregator = new Aggregator();
        String task1 = "1757600,IPN";
        // String task2 = "1757600,SAL";
        // String task3 = "700000,MAS";
        // String task4 = "7025423,AAA";

        List<String> results = aggregator.sendTasksToWorkers(Arrays.asList(WORKER_ADDRESS_1),
                Arrays.asList(task1));

        int i=1;
        for (String result : results) {
            System.out.printf("task[%d] %s\n",i++,result);
        }
    }
}
