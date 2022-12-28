import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class Aggregator {
    private WebClient webClient;

    public Aggregator() {
        this.webClient = new WebClient();
    }

    public List<String> sendTasksToWorkers(List<String> workersAddresses, List<String> tasks) {
        CompletableFuture<String>[] futures = new CompletableFuture[workersAddresses.size()+1];

        for (int i = 0; i < workersAddresses.size(); i++) {
            String workerAddress = workersAddresses.get(i);
            String task = tasks.get(i);

            byte[] requestPayload = task.getBytes();
            futures[i] = webClient.sendTask(workerAddress, requestPayload);
            System.out.println("future[" + i + "] (" + workerAddress + ") realizara tarea["+(i+1)+"]:" + task);
            
        }
        boolean bandera = true;
        while (bandera) {
            for (int j = 0; j < 2; j++) {
                // System.out.println("futures[" + j + "].isDone() = " + futures[j].isDone());
                if (true == futures[j].isDone()){
                    String workerAddress = workersAddresses.get(j);
                    String task = tasks.get(2);
                    System.out.println("future[" + j + "] ("+ workerAddress+") termino antes, realizara tarea["+3+"]:"+task);
                    byte[] requestPayload = task.getBytes();
                    futures[2] = webClient.sendTask(workerAddress, requestPayload);
                    bandera = false;
                }

            }
        }
        
        List<String> results = new ArrayList();
        for (int i = 0; i < tasks.size(); i++) {
            results.add(futures[i].join());
        }

        return results;
    }
}
