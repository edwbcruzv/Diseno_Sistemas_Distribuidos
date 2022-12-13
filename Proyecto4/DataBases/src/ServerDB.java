import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerDB {
    private static final String DB_ADDRESS_1 = "http://localhost:5001";
    private static final String DB_ADDRESS_2 = "http://localhost:5002";
    private static final String DB_ADDRESS_3 = "http://localhost:5003";
    private Aggregator aggregator = new Aggregator();
    
    public static void main(String[] args) {
        // Integer.parseInt(args[0])
        ServerDB db = new ServerDB();
        
        ExecutorService exec1 = Executors.newSingleThreadExecutor();
        exec1.submit(() -> {
            System.out.println("generarando por minuto");
        });

        ExecutorService exec2 = Executors.newSingleThreadExecutor();
        exec2.submit(() -> {
            System.out.println("Menu");
        });
    }

    public ServerDB(){

        List<String> results = this.aggregator.statusDBs(Arrays.asList(
                DB_ADDRESS_1,
                DB_ADDRESS_2,
                DB_ADDRESS_3));

        for (String result : results) {
            System.out.println(result);
        }
    }

    public static void name() {
        
    }


}
